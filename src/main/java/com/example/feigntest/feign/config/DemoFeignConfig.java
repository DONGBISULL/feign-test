package com.example.feigntest.feign.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
@Slf4j
public class DemoFeignConfig {

    @Value("${send.auth.key}")
    private String sendAuthKey;
    @Bean
    public RequestInterceptor requestInterceptor() {
        // 여기서 헤더에 값 추가
        return template -> {
            /* 필요한 헤더 설정 추가 */
            template.header("Content-Type", "application/json; charset=utf-8");
            template.header("Authorization", "Bearer " + sendAuthKey);
            template.header("test-header", "test-header-value");
            log.info("Request Headers: {}", template.headers());
        };
    }
}
