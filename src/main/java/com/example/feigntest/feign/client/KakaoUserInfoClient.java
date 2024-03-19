package com.example.feigntest.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "kakao-user-client",
        url = "${feign.url.kakao.user.prefix}"
)

public interface KakaoUserInfoClient {
    @PostMapping("/v2/user/me")
    ResponseEntity<Object> getUserInfo(@RequestHeader("Authorization") String authorizationHeader);
}


