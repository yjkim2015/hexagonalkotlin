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



## [API 명세]

##### [요청] 

| 항목 | 값                            | 설명                   |
| ---- | ----------------------------- | ---------------------- |
| URL  | `GET` /api/v1/blog/popularity | 인기블로그 검색어 10개 |



**[응답 예시]**

```
[
  {
    "query": "김밥",
    "score": 1
  }
]
```



##### [요청]

| 항목 | 값                        | 설명        |
| ---- | ------------------------- | ----------- |
| URL  | `GET` /api/v1/blog/search | 블로그 검색 |

#### [항목]

| 이름  | 타입   | 필수 | 설명                |
| ----- | ------ | ---- | ------------------- |
| query | String | o    | 검색어              |
| sort  | String | ○    | 정렬방법 (accuracy) |
| page  | int    | o    | 현재 페이지         |
| size  | int    | o    | 보여줄 갯수         |



**[응답 예시]**

```
{
  "total": 5567313,
  "start": 1,
  "display": 10,
  "items": [
    {
      "title": "대구 서문시장 맛집 신서문<b>김밥</b> 아침식사 가능한곳 / 주차",
      "link": "https://blog.naver.com/daegu_daall/223213594984",
      "description": "오늘은 대구 서문시장 맛집 신서문<b>김밥</b>에 대해서포스팅 할게요! 저는 이번에 처음 방문을 했지만, 제... 서문시장 안으로 쭉 들어가면 신서문<b>김밥</b>이 보이는데요! 조리는 밖에서 하고 안쪽에 먹고 갈수 있는곳이... ",
      "bloggername": "N잡러 김로이",
      "bloggerlink": "blog.naver.com/daegu_daall",
      "postdate": "20230917"
    },
    {
      "title": "이천 분식집 선비꼬마<b>김밥</b> 남녀노소 모두 ㅇㅋ",
      "link": "https://blog.naver.com/bbinu79/223214097714",
      "description": "아들하고 둘이 밥 먹을 일이 많은 요즘 집 근처 이천 분식집 맛있는 곳이 생겨서 종종 방문합니다~ 이천 선비꼬마<b>김밥</b> 이라고 이천 안흥동 맛집으로 동네 주민들도 종종 찾는 곳이예요~ 꼬마<b>김밥</b>이 유명한... ",
      "bloggername": "나만 아는 블로그 ㅋ",
      "bloggerlink": "blog.naver.com/bbinu79",
      "postdate": "20230917"
    },
    {
      "title": "<b>김밥</b>집 창업 20대 후반 여성의 성공기",
      "link": "https://blog.naver.com/yh187/223178372963",
      "description": "저는 그 누구나 쉽게 시작할 수 있다는 '진순자<b>김밥</b>을 창업한 젊은 여성입니다. 이 <b>김밥</b>집창업 성공담과 더불어 제 이야기를 시작해볼게요. 두근두근, 함께 시작해 볼까요? 저는 어릴 때부터 요리를 좋아했어요.... ",
      "bloggername": "Chicret for 10 o'clock",
      "bloggerlink": "blog.naver.com/yh187",
      "postdate": "20230808"
    },
    {
      "title": "속초 가볼만한곳 중앙시장 맛집 최대섭대박<b>김밥</b>",
      "link": "https://blog.naver.com/moongjji2/223216672762",
      "description": "<b>김밥</b> 속초본점 속초 가볼만한곳 속초 중앙시장 맛집 2박 3일 속초 여행을 떠났던 날, 속초 가볼만한곳으로 유명한 속초 중앙시장에 다녀왔어요. 예전에 친구네 커플과 속초에 갔을 때 알려줬던 속초 <b>김밥</b>... ",
      "bloggername": "소소한 행복을 그리는 뭉찌의 리뷰 스토리",
      "bloggerlink": "blog.naver.com/moongjji2",
      "postdate": "20230920"
    },
    {
      "title": "제주 세화해수욕장 식당 먹거리 도도톳<b>김밥</b> 세화점",
      "link": "https://blog.naver.com/th3klove/223207772493",
      "description": "그리고 배가 고파 먹게 된 도도톳<b>김밥</b> 세화점 내돈내산 후기입니다. 도도톳은 세화점과 신정점 두 곳인데 전 세화점을 다녀왔어요. 도도톳<b>김밥</b> 세화점 영업시간: 09:00~19:00( 매주 수요일 휴무) 15:00~16:00... ",
      "bloggername": "떠나고 느끼고 즐기고",
      "bloggerlink": "blog.naver.com/th3klove",
      "postdate": "20230910"
    },
    {
      "title": "여수 바다<b>김밥</b> 본점 예약 방법 이순신광장 먹거리 여행",
      "link": "https://blog.naver.com/gebera12/223212103537",
      "description": "21(월) 먹방 여행을 위해 여수 이순신광장 들러 여러 가지 사 먹었는데 그중에서 여수 바다<b>김밥</b>은... 여수 바다<b>김밥</b> 중앙점에 가까운 주차장은 진남관공영주차장, 그다음 이순신광장공영주차장(지하)... ",
      "bloggername": "아델레 트래블",
      "bloggerlink": "blog.naver.com/gebera12",
      "postdate": "20230915"
    },
    {
      "title": "알토란 충무<b>김밥</b> 무김치 &amp; 오징어무침 &amp; 어묵무침 만능무침양념장",
      "link": "https://blog.naver.com/makemage27/223207758257",
      "description": "천상현 셰프 만능무침양념장으로 만드는 3가지 요리 알토란 무김치 &amp; 충무<b>김밥</b>오징어무침 &amp; 어묵무침 충무<b>김밥</b> 너무 좋아하는데 곁들여 먹는 무침반찬 만드는 게 어려울 것 같아 만들 생각을 못했는데요.... ",
      "bloggername": "마눌꽁치's 요리 Cock!",
      "bloggerlink": "blog.naver.com/makemage27",
      "postdate": "20230910"
    },
    {
      "title": "제주 오는정<b>김밥</b> 육지에서도 이곳을 알고있다?",
      "link": "https://blog.naver.com/viviensoo/223207702382",
      "description": "웨이팅 양대산맥 인정한다 제주 오는정<b>김밥</b> 솔직히 웨이팅하면서 먹고 싶지 않아서 2년 넘게 방문하지... 오는정<b>김밥</b> 주차할 공간을 더이상 찾지 않아도 됩니다 예약제로 운영되기에 가게 앞에 잠깐 정차하고... ",
      "bloggername": "제주의 오아시스",
      "bloggerlink": "blog.naver.com/viviensoo",
      "postdate": "20230913"
    },
    {
      "title": "비건한끼 <b>김밥</b>과 볶음밥 다이노브이 식물성 캔햄 오리지널",
      "link": "https://blog.naver.com/princessrain/223212682894",
      "description": "이 제품을 이용해 볶음밥과 <b>김밥</b>을 만들어 먹어보았어요! 소개해드릴 제품은 퓨처엑스의 다이노 브이... <b>김밥</b> <b>김밥</b> 재료  식물성 햄, 버섯, 파프리카, 오이, 당근, 오이고추, 깻잎, 간장, 마늘, 김, 현미밥 <b>김밥</b>... ",
      "bloggername": "비건한끼 하실래요?",
      "bloggerlink": "blog.naver.com/princessrain",
      "postdate": "20230915"
    },
    {
      "title": "제주 서귀포 오는정<b>김밥</b>과 바다를본돼지 당일 전화 예약 주차",
      "link": "https://blog.naver.com/ssanga85/223200023888",
      "description": "얼마 전 지인들이랑 계획 없이 제주도로 떠나 제주 서귀포 오는정<b>김밥</b>을 영접했어요. 이어서 마찬가지로 소문이 자자하다는 흑돼지 집까지 공략했던 후기를 적어볼게요. ★★★★★ 오는정<b>김밥</b> 주소 : 제주... ",
      "bloggername": "S2 덩이의긍정적우주별☆",
      "bloggerlink": "blog.naver.com/ssanga85",
      "postdate": "20230902"
    }
  ]
}
```



##### [HTTP 응답코드]

| 응답코드 | 설명                  |
| -------- | --------------------- |
| `200`    | **정상 응답**         |
| `400`    | 잘못된 요청           |
| `404`    | 리소스를 찾을 수 없음 |
| `500`    | 시스템 에러           |



##### [에러코드 및 메시지]

| 에러 코드                        | 에러 메시지               |
| -------------------------------- | ------------------------- |
| HttpStatus.INTERNAL_SERVER_ERROR | Internal Server Error     |
| HttpStatus.BAD_REQUEST           | 유효한 정렬 값이 아닙니다 |
| HttpStatus.BAD_REQUEST           | Page 값은 1부터 입니다    |
| HttpStatus.BAD_REQUEST           | Size 값은 1부터 입니다    |





## jar 다운로드 URL

https://drive.google.com/file/d/1kSiUNvx-haAtY7SAL0diYN4sybIIx6Au/view?usp=sharing



## 실행 후 Swagger 주소

http://localhost:8080/swagger-ui/
