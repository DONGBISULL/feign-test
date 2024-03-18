package com.example.feigntest.service;

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
        System.out.println("Name : " + responseInfo.getBody().getName());
        System.out.println("Age : " + responseInfo.getBody().getAge());
        System.out.println("Header : " + responseInfo.getBody().getHeader());

//        ResponseEntity<Object> response = demoFeignClient.callGet();
//        ResponseEntity<Object> response = demoFeignClient.callBySenderId();
//        log.info(response.toString());
//        System.out.println(response.getBody());
        return "get";
    }
}
