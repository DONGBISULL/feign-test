package com.example.feigntest.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor(staticName = "of") // 생성자 private 로 생성자 생성
public class DemoRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        if (template.method() == HttpMethod.GET.name()) {
            log.info("[GET] [ DemoRequestInterceptor] queries " + template.queries());
            log.info("[GET] [ DemoRequestInterceptor] headers " + template.headers());
            return;
        }

        String encodeRequestBody = StringUtils.toEncodedString(template.body(), StandardCharsets.UTF_8);
        log.info("[POST] [ DemoRequestInterceptor] requestbody " + encodeRequestBody);

        // 추가적으로 필요 로직 추가
        String convertRequestBody = encodeRequestBody;
        template.body(convertRequestBody);

    }
}
