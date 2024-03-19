package com.example.feigntest.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "kakao-login-client",
        url = "${feign.url.kakao.login.prefix}"
)
public interface KakaoLoginClient {
    @PostMapping("/oauth/token")
    ResponseEntity<Object> getToken(@RequestParam("client_id") String clientId,
                                    @RequestParam("grant_type") String grantType,
                                    @RequestParam("redirect_uri") String redirectUri,
                                    @RequestParam("code") String authorizationCode,
                                    @RequestParam("client_secret") String clientSecret);

    //    액세스 토큰 방식
    @PostMapping(value = "/v1/user/logout")
    ResponseEntity<String> logout(@RequestHeader("Authorization") String authorizationHeader , @RequestHeader("Content-Type") String contentType);

}
