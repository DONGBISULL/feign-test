# Feign Client

외부 컴포넌트와 통신이 발생함
=> 이 통신에 사용되는 여러 클라이언트 존재

ex) RestTemplate , Feign

## Connection / Read Timeout

외부 서버와 통신 시 connection / read Timeout 설정 가능함

- 각 컴포넌트 별로 다른 시간 설정을 세팅해줄 수 있음
- application.yml 사용하여 설정 가능

## Feign Interceptor

외부로 요청 나가기전 공통 처리 로직 필요할 경우 Interceptor 를 재정의 하여 처리 가능

만약 요청에서  "Authorization", "Bearer " 이런 식으로 헤더에 토큰을 추가하는 등
선행하는 처리가 필요할 때 토큰을 다루는 인터셉터를 생성해서 만들면 될 듯

- 그외의 방법 헤더 configuration 등록하기 / METHOD 에 헤더 직접 매핑해주기
- 문제는 인터셉터의 순서를 보장하지는 못하므로 이 부분의 주의하자

=> 실제 사용
요청에 따라 로깅 처리를 위해 인터셉터 사용
기본 헤더 설정

## Feign Custom logger

내가 필요한 값 요청과 요청 시간에 대한 실제 운영 로그를 남기기 위한 커스텀 로그 설정

## Feign ErrorDecorder

요청에 대해서 정상 응답이 아닌 경우 핸들링 처리

ErrorDecorder 에서 설정한 이셉션으로 랩핑 처리하여 에러 던져줌

각 client 요청에 따라 에러 코드가 다를 수 있으므로 클라이언트에 따라 다르게 ErrorDecorder 설정하는 게 좋다



```shell
   curl 'http://localhost:8080/get'
```

```shell
   curl 'http://localhost:8080/error'
```

```shell
     Invoke-WebRequest -Method POST "http://localhost:8080/post"
```

