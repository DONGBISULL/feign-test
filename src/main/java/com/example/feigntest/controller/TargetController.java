package com.example.feigntest.controller;

import com.example.feigntest.common.dto.BaseResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/target_server")
@RequiredArgsConstructor
public class TargetController {
    @GetMapping("/get")
    public BaseResponseInfo get(@RequestHeader("CustomHeaderName") String header,
                                @RequestParam("name") String name,
                                @RequestParam("age") Long age) {
        return BaseResponseInfo.builder()
                .header(header)
                .name(name)
                .age(age)
                .build();
    }
}
