package com.example.feigntest.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL) //  JSON 직렬화  -> JSON으로 변환할 때 특정 조건에 따라 어떤 속성을 포함할지 결정
//Include.NON_NULL 클래스의 객체가 JSON으로 변환될 때, 값이 null인 속성은 포함되지 않도록 하는 옵션
public class BaseRequestInfo { // Wrapping 해서 받을 요청
    private String name;
    private Long age;
}
