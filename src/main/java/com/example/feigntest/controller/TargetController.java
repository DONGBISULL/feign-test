package com.example.feigntest.controller;

import com.example.feigntest.common.dto.BaseRequestInfo;
import com.example.feigntest.common.dto.BaseResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/target_server")
@RequiredArgsConstructor
public class TargetController {
    @GetMapping("/get")
    public BaseResponseInfo demoGet(@RequestHeader("CustomHeaderName") String header,
                                @RequestParam("name") String name,
                                @RequestParam("age") Long age) {
        return BaseResponseInfo.builder()
                .header(header)
                .name(name)
                .age(age)
                .build();
    }

    /*
    powershell 에서 요청 시 post 이렇게 해야함 !
    Invoke-WebRequest -Method POST "http://localhost:8080/post"
    * */
    @PostMapping("/post")
    public BaseResponseInfo demoPost(@RequestHeader("CustomHeaderName") String header,
                                 @RequestBody BaseRequestInfo body) {
        return BaseResponseInfo.builder()
                .header(header)
                .name(body.getName())
                .age(body.getAge())
                .build();
    }

    @GetMapping("/error")
    public ResponseEntity<BaseResponseInfo> demoError( ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
