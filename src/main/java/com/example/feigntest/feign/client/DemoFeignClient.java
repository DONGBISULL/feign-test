package com.example.feigntest.feign.client;

import com.example.feigntest.common.dto.BaseRequestInfo;
import com.example.feigntest.common.dto.BaseResponseInfo;
import com.example.feigntest.feign.config.DemoFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "demo-client",// 이름 중복 시 에러 발생
        url = "${feign.url.demo.prefix}",
        configuration = {
                DemoFeignConfig.class
        }
)
//@HeaderMap Map<String, Object> headers 요청마다 header 동적으로 다를 경우
public interface DemoFeignClient {
    @GetMapping("/get")
    ResponseEntity<BaseResponseInfo> callGet(@RequestHeader("CustomHeaderName") String header,
                                             @RequestParam("name") String name,
                                             @RequestParam("age") Long age
    );

    @PostMapping("/post")
    ResponseEntity<BaseResponseInfo> callPost(@RequestHeader("CustomHeaderName") String header,
                                              @RequestBody BaseRequestInfo baseRequestInfo);

    @GetMapping("/error")
    ResponseEntity<BaseResponseInfo> callErrorDecorder( );


    @GetMapping("/posts")
    ResponseEntity<Object> callGetPosts();

    @GetMapping("/A/${sender.id}")
    ResponseEntity<Object> callBySenderId();
}
