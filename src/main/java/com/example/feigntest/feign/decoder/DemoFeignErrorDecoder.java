package com.example.feigntest.feign.decoder;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Slf4j
public class DemoFeignErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        HttpStatus httpStatus = HttpStatus.resolve(response.status());
        if (httpStatus == HttpStatus.NOT_FOUND) {
            log.debug("[DemoFeignErrorDecorder] http Status = " + httpStatus);
            throw new RuntimeException(String.format("[RuntimeException] http status is %s", httpStatus.name()));
        }
        return errorDecoder.decode(methodKey, response);
    }
}
