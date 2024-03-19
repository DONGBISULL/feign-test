package com.example.feigntest.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KakaoLoginResponse {

    String tokenType; // bearer

    String accessToken; // 사용자 액세스 토큰 값

    String idToken; // ID 토큰 값

    Integer expiresIn; // 액세스 토큰과 ID 토큰의 만료 시간(초)

    String refreshToken; //사용자 리프레시 토큰 값

    Integer refreshTokenExpiresIn; // 리프레시 토큰 만료 시간(초)

    String scope; // 인증된 사용자의 정보 조회 권한 범위
}
