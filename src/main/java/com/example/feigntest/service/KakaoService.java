package com.example.feigntest.service;

import com.example.feigntest.common.dto.KakaoLoginResponse;
import com.example.feigntest.feign.client.KakaoLoginClient;
import com.example.feigntest.feign.client.KakaoUserInfoClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class KakaoService {

    private final ObjectMapper objectMapper;

    private final KakaoLoginClient kakaoLoginClient;
    private final KakaoUserInfoClient kakaoUserInfoClient;

    public KakaoService(KakaoLoginClient kakaoLoginClient, KakaoUserInfoClient kakaoUserInfoClient) {
        this.kakaoLoginClient = kakaoLoginClient;
        this.kakaoUserInfoClient = kakaoUserInfoClient;
        this.objectMapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    @Value("${kakao.client-id}")
    String clientId;

    @Value("${kakao.login.redirect-uri}")
    String redirectUri;

    @Value("${kakao.login.grant-type}")
    String grantType;

    /* access token 조회 위한 처리 */
    public Optional<KakaoLoginResponse> getToken(String code) {
        log.info("=================== login getToken =================== " + code);
        ResponseEntity<Object> response = kakaoLoginClient.getToken(
                clientId,
                grantType,
                redirectUri,
                code,
                null

        );
        if (response.getStatusCode().is2xxSuccessful()) {
            Object body = response.getBody();
            KakaoLoginResponse kakaoTokenInfo = objectMapper.convertValue(body, KakaoLoginResponse.class);
            return Optional.of(kakaoTokenInfo);
        } else {
            return Optional.empty();
        }
    }

    /* access token 활용하여 사용자 정보 조회 */
    public Map<String, Object> getUserInfo(String accessToken) {
        log.info("=================== login getUserInfo =================== " + accessToken);
        ResponseEntity<Object> response = kakaoUserInfoClient.getUserInfo("Bearer " + accessToken);
        if (response.getStatusCode().is2xxSuccessful()) {
            Object body = response.getBody();
            return (Map<String, Object>) body;
        } else {
            return null;
        }
    }

    /* logout 처리 */
    public boolean logout(String accessToken) {
        log.info("=================== logout accessToken =================== " + accessToken);
        ResponseEntity<String> response = kakaoLoginClient.logout("Bearer " + accessToken, "application/x-www-form-urlencoded");
        return response.getStatusCode().is2xxSuccessful();
    }

}
