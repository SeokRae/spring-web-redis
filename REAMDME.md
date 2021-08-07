# Redis with Spring Boot

## Intro

- 프로젝트에 **인증 토큰 관리**를 위해 적용해보기 위한 사전 샘플 테스트
	- HTTP request 테스트

- Redis

## HashOperations 인터페이스 테스트를 위한 프로젝트

- hashOperations 모듈
  - hashOperations 만 사용 및 테스트 용

## Issue

- Redis에 LocalDateTime 타입으로 데이터를 입력 후 조회 시 직렬화 문제
	- [solution](https://stackoverflow.com/questions/53267203/spring-data-redis-issue-while-storing-date)


## Reference 

- [JSON Web Tokens (JWT) are Dangerous for User Sessions—Here’s a Solution](https://redislabs.com/blog/json-web-tokens-jwt-are-dangerous-for-user-sessions/)
