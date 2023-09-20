## 개발 환경

| 분야                   | stack                         |
| ---------------------- | ----------------------------- |
| 언어                   | kotlin                        |
| 프레임워크             | springBoot 2.7.6 <br />JUnit5 |
| DB                     | Redis                         |
| 빌드 툴                | Gradle                        |
| Persistence 프레임워크 | Spring Data Jpa               |
| API 테스트 툴          | Postman                       |
| IDE                    | IntelliJ                      |



## 멀티모듈 구성

1. api (컨트롤러 및 Core Business 연결 역할)

2. core (핵심 비즈니스 로직 및 Infra 연결)

3. Infra (외부 연결 DB 또는 API)



## 요구사항 및 해결 방법

1. 블로그 검색 (o)
   - 카카오 블로그 검색 API 장애 시, Naver 블로그 검색 API 조회
   - 대용량 트래픽에 API 서버 폭주 대비 Redis를 통한 글로벌 캐싱 (Jpa CrudRepository 활용)
2. 인기 검색어 목록 (o)
   * Redis Sorted Set을 통해 동시성 이슈 해결 (RestTemplate 활용)



## jar 다운로드 URL

https://drive.google.com/file/d/1kSiUNvx-haAtY7SAL0diYN4sybIIx6Au/view?usp=sharing

