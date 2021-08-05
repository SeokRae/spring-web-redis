# Redis with Spring Boot

## Intro

- 프로젝트에 **인증 토큰 관리**를 위해 적용해보기 위한 사전 샘플 테스트
	- Junit으로 Repository Layer 테스트
	- HTTP request 테스트

- Redis

## HashOperations 인터페이스 테스트

## Issue

- Redis에 LocalDateTime 타입으로 데이터를 입력 후 조회 시 직렬화 문제
	- [solution](https://stackoverflow.com/questions/53267203/spring-data-redis-issue-while-storing-date)
