package com.example.feigntest.config;

import com.example.feigntest.feign.logger.FeignCustomLogger;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration 어노테이션 붙인 설정의 경우
 * Feign을 사용한 모든 요청에 대해서 설정이 적용됨
 */
@Configuration
public class FeignConfig {

    @Bean
    public Logger feignLooger() {
        return new FeignCustomLogger();
    }
}
