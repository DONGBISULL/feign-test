package com.example.feigntest.controller;

import com.example.feigntest.common.dto.KakaoLoginResponse;
import com.example.feigntest.common.dto.MemberDto;
import com.example.feigntest.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final KakaoService kakaoService;

    @GetMapping("/kakao-login")
    public String kakaologin(@RequestParam String code, HttpSession session) throws IOException {
        Optional<KakaoLoginResponse> kakaoLoginResponse = kakaoService.getToken(code);
        if (kakaoLoginResponse.isPresent()) {
            KakaoLoginResponse response = kakaoLoginResponse.get();
            Map<String, Object> userInfo = kakaoService.getUserInfo(response.getAccessToken());
            MemberDto memberDto = MemberDto.builder()
                    .kakaoLoginResponse(response)
                    .kakaoUserInfo(userInfo)
                    .build();
            // 세션에 로그인 정보 저장
            session.setAttribute("loggedInUser", memberDto);
            return "redirect:/"; // index 페이지로 리다이렉트
        } else {
            return "redirect:/error"; // 오류 페이지로 리다이렉트
        }
    }

    @GetMapping("/kakao-logout")
    public String kakaoLogout(@RequestParam String accessToken, HttpSession session) throws IOException {
      // result 를 다룰 수 있는 dto 만드는게 실무에서 사용 시 방법
        if(kakaoService.logout(accessToken)){
            session.invalidate();
        }
        return "redirect:/";
    }


}
