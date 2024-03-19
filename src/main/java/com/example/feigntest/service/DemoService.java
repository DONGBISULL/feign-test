package com.example.feigntest.service;

import com.example.feigntest.common.dto.BaseRequestInfo;
import com.example.feigntest.common.dto.BaseResponseInfo;
import com.example.feigntest.feign.client.DemoFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DemoService {

    private final DemoFeignClient demoFeignClient;

    public String get() {
        ResponseEntity<BaseResponseInfo> responseInfo = demoFeignClient.callGet("CustomHeaderName", "CustomName", 1L);
        log.info("Name : " + responseInfo.getBody().getName());
        log.info("Age : " + responseInfo.getBody().getAge());
        log.info("Header : " + responseInfo.getBody().getHeader());
//        ResponseEntity<Object> response = demoFeignClient.callGet();
//        ResponseEntity<Object> response = demoFeignClient.callBySenderId();
//        log.info(response.toString());
//        System.out.println(response.getBody());
        return "get";
    }

    public String post() {
        BaseRequestInfo baseRequestInfo = BaseRequestInfo.builder()
                .name("customName")
                .age(2L)
                .build();
        ResponseEntity<BaseResponseInfo> responseInfo = demoFeignClient.callPost("CustomHeaderName", baseRequestInfo);
        log.info("Name : " + responseInfo.getBody().getName());
        log.info("Age : " + responseInfo.getBody().getAge());
        log.info("Header : " + responseInfo.getBody().getHeader());
        return "post";
    }

    public String errorDecorder() {
        ResponseEntity<BaseResponseInfo> responseInfo = demoFeignClient.callErrorDecorder();
//        log.info("Name : " + responseInfo.getBody().getName());
//        log.info("Age : " + responseInfo.getBody().getAge());
//        log.info("Header : " + responseInfo.getBody().getHeader());
        return "error";
    }
}
