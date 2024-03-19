package com.example.feigntest.feign.logger;

import feign.Logger;
import feign.Request;
import feign.Response;
import feign.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static feign.Util.*;

@RequiredArgsConstructor
@Slf4j
public class FeignCustomLogger extends Logger {

    private static final int DEFAULT_SLOW_API_TIME = 3_000;
    private static final String SLOW_API_NOTICE = "Slow API";

    @Override
    protected void log(String configKey, String format, Object... args) {
        //        로그를 남길 경우 사용하는 형식을 지정
        log.info(String.format(methodTag(configKey) + format, args));
    }

    //    Request 만 핸들링 가능
    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) {
        //        super.logRequest(configKey, logLevel, request);
        log.info(request.toString());
    }

    //    response 와 reqeust 등 모두 핸들링할 경우 사용
    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime) throws IOException {
//        return super.logAndRebufferResponse(configKey, logLevel, response, elapsedTime);

//        프로토콜 버전
        String protocolVersion = resolveProtocolVersion(response.protocolVersion());

        String reason = response.reason() != null && logLevel.compareTo(Level.NONE) > 0 ? "  " + response.reason() : "";

        int status = response.status();
        log(configKey, "<--- %s %s%s (%sms)", protocolVersion, status, reason, elapsedTime);
//        reqeust 와 헤더까지 로그 작성
        if (logLevel.ordinal() >= Level.HEADERS.ordinal()) {

            for (String field : response.headers().keySet()) {
                if (shouldLogResponseHeader(field)) {
                    for (String value : valuesOrEmpty(response.headers(), field)) {
                        log(configKey, "%s: %s", field, value);
                    }
                }
            }

            int bodyLength = 0;
            if (response.body() != null && !(status == 204 || status == 205)) {
                // HTTP 204 No Content "...response MUST NOT include a message-body"
                // HTTP 205 Reset Content "...response MUST NOT include an entity"
                if (logLevel.ordinal() >= Level.FULL.ordinal()) {
                    log(configKey, ""); // CRLF
                }
                byte[] bodyData = Util.toByteArray(response.body().asInputStream());
                bodyLength = bodyData.length;
                if (logLevel.ordinal() >= Level.FULL.ordinal() && bodyLength > 0) {
                    log(configKey, "%s", decodeOrDefault(bodyData, UTF_8, "Binary data"));
                }

                if (elapsedTime > DEFAULT_SLOW_API_TIME){
                    log(configKey, "[%s] elapsedTime : %s " , SLOW_API_NOTICE , elapsedTime);
                }

                log(configKey, "<--- END HTTP (%s-byte body)", bodyLength);
                return response.toBuilder().body(bodyData).build();
            } else {
                log(configKey, "<--- END HTTP (%s-byte body)", bodyLength);
            }
        }
        return response;
    }

}
