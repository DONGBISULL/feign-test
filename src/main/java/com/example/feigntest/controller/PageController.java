package com.example.feigntest.controller;

import com.example.feigntest.common.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class PageController {
    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/")
    public ModelAndView index(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index"); // index.html 페이지로 이동
        MemberDto memberDto = (MemberDto) session.getAttribute("loggedInUser");
        modelAndView.addObject("member", memberDto != null ? memberDto : null); // 멤버 DTO를 index 페이지로 전달
        return modelAndView;
    }

    @GetMapping("/error")
    public ModelAndView errorPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        return modelAndView;
    }

}


