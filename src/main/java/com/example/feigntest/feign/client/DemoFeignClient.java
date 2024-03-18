package com.example.feigntest.feign.client;

import com.example.feigntest.common.dto.BaseResponseInfo;
import com.example.feigntest.feign.config.DemoFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(
        name = "demo-client",// 이름 중복 시 에러 발생
        url = "${feign.url.demo.prefix}",
        configuration = {
                DemoFeignConfig.class
        }
)
//@Headers("x-requester-id: {requester}")
//@HeaderMap Map<String, Object> headers 요청마다 header 동적으로 다를 경우
public interface DemoFeignClient {
    @GetMapping("/get")
    ResponseEntity<BaseResponseInfo> callGet(@RequestHeader("CustomHeaderName") String header,
                                             @RequestParam("name") String name,
                                             @RequestParam("age") Long age
    );

    @GetMapping("/posts")
    ResponseEntity<Object> callGetPosts();

    @GetMapping("/A/${sender.id}")
    ResponseEntity<Object> callBySenderId();
}
