# HTTP 헤더1 - 일반 헤더

## HTTP 헤더
### 용도
- HTTP 전송에 필요한 모든 부가정보
    - ex) 메시지 바디의 내용, 바디 크기, 압축, 인증, 요청 클라이언트, 서버 정보, 캐시 관리 정보 등
- 표준 헤더가 너무 많음
- 필요 시 임의의 헤더 추가 가능

### 분류 - RFC2616(과거)
- 헤더 분류
    - General 헤더: 메시지 전체에 적용되는 정보
        - ex) Connection: close
    - Request 헤더: 요청 정보
        - ex) User-Agent: Mozilla/5.0 (Macintosh; ..)
    - Response 헤더: 응답 정보
        - ex) Server: Apache
    - Entity 헤더: 엔티티 바디 정보
        - ex) Content-Type: text/html, Content-Length: 3423


## HTTP 바디
### message body - RFC2616(과거)
- 메시지 본문은 엔티티 본문을 전달하는데 사용
- 엔티티 본문은 요청이나 응답에서 전달할 실제 데이터
- 엔티티 헤더는 엔티티 본문의 데이터를 해석할 수 있는 정보 제공
    - 데이터 유형(html, json), 데이터 길이, 압축 정보 등


## RFC723x로의 변화
- 엔티티 -> 표현(Representation)
- Representation = Representation Metadata + Representation Data

### message body - RFC7230(최신)
- 메시지 본문을 통해 표현 데이터 전달
- 메시지 본문 = 페이로드(payload)
- 표현은 요청이나 응답에서 전달할 실제 데이터
- 표현 헤더는 표현 데이터를 해석할 수 있는 정보 제공
    - 데이터 유형, 데이터 길이, 압축 정보 등


## 표현
표현 헤더는 전송, 응답 둘 다 사용함
### Content-Type
표현 데이터의 형식을 설명함
- 미디어 타입, 문자 인코딩
- 예시
    - text/html; charset=utf-8
    - application/json
    - image/png

### Content-Encoding
표현 데이터의 인코딩 방식
- 표현 데이터를 압축하기 위해 사용
- 데이터를 전달하는 곳에서 압축 후 인코딩 헤더 추가
- 데이터를 읽는 쪽에서 인코딩 헤더의 정보로 압축을 해제함
- 예시
    - gzip
    - deflate
    - identity

### Content-Language
표현 언어의 자연 언어를 나타냄
- 예시
    - ko
    - en
    - en-US

### Content-Length
표현 데이터의 길이
- 바이트 단위
- 실제 표현이라기보다는 payload 개념
- Transfer-Encoding(전송 코딩) 사용 시 Content-Length 사용 불가


## 협상(Content Nagotiation)
클라이언트가 선호하는 표현을 요청함(요청 시에만 사용)
- Accept: 클라이언트가 선호하는 미디어타입 전달
- Accept-Charset: 클라이언트가 선호하는 문자 인코딩
- Accept-Encoding: 클라이언트가 선호하는 압축 인코딩
= Accept-Language: 클라이언트가 선호하는 자연 언어


### 협상 우선순위
- Quality Values(q)
    - Quality Values(q) 값 사용
    - 0~1(클수록 우선순위 높음)
    - 생략 시 1
    - ex) Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7
- 구체적인 것이 우선시 됨
    - ex) Accept: text/*, text/plain, text/plain;format=flowed, */* 에서 text/plain;format=flowed 가 가장 우선시


## 전송방식
- 단순 전송
    - 요청 시 한번에 응답을 줌
    - Content-Length를 지정
- 압축 전송
    - 컨텐츠를 압축해서 전송하되, Encoding 정보를 같이 줌
- 분할 전송
    - Transfer-Encoding: chunked(덩어리로 쪼개서 보낸다는 뜻)
    - Content-Length를 보내면 안 됨(각 덩어리에 바이트 정보가 다 있음)
- 범위 전송
    - 범위를 지정하여 요청


## 일반 정보
- Form: 유저 에이전트의 이메일 정보
- Referer: 이전 웹 페이지 주소
    - 유입 경로 분석 시 많이 사용함
    - 요청 시 사용
    - 참고: referrer의 오타
- User-Agent: 유저 에이전트(클라이언트) 애플리케이션 정보
    - 웹 브라우저 정보 등
    - 어떤 브라우저에서 장애가 발생하는지 파악 가능함
    - 요청 시 사용
- Server: 요청을 처리하는 오리진 서버의 소프트웨어 정보
    - 응답 시 사용
- Date: 메시지 생성일
    - 응답 시 사용


## 특별한 정보
- Host: 요청한 호스트 정보(도메인)
    - 요청 시 사용
    - 필수값!
    - 하나의 서버가 여러 도메인을 처리해야 할 때 구분에 사용
- Location: 페이지 리디렉션
    - 웹 브라우저는 3xx 응답의 결과에 Location 헤더가 있으면 Location 위치로 자동 이동함(리다이렉트)
- Allow: 허용 가능한 HTTP 메서드
    - URL 경로는 있는데 GET은 제공하고 POST는 제고을 안 하는 등의 케이스
- Retry-After: 유저 에이전트가 다음 요청을 하기까지 기다려야 하는 시간
    - 서비스 이용 불가(503)일 때 언제까지 불가능한지 알려줄 수 있음


## 인증
- Authorization: 클라이언트 인증 정보를 서버에 전달
- WWW-Authenticate: 리소스 접근 시 필요한 인증 방법 정의
    - 401 응답과 함께 사용
    - ex) WWW-Authenticate: Newauth realm="apps", type=1, title="Login to \"apps\"", Basic realm="simple"


## 쿠키
- Set-Cookie: 서버에서 클라이언트로 쿠키 전달(응답)
- Cookie: 클라이언트가 서버에서 받은 쿠키를 저장하고, HTTP 요청 시 서버로 전달
- 무상태 프로토콜의 단점을 보완할 수 있음
- 쿠키는 모든 요청에 자동으로 쿠키정보를 포함
- 사용처
    - 사용자 로그인 세션 관리
    - 광고 정보 트래킹
- 쿠키 정보는 항상 서버에 전송됨
    - 네트워크 트래픽 추가 유발
    - 최소한의 정보만 사용함(세션 ID, 인증 토큰)
    - 서버에 전송하지 않고, 웹 브라우저 내부에 데이터를 저장하고 싶으면 웹 스토리지(localStorage, sessionStorage) 참고
- 주의할 점
    - 보안에 민감한 데이터 저장 금지(주민등록번호, 신용카드 번호 등)

### 쿠키의 생명주기
- Expires
    - 만료일이 되면 쿠키 삭제
    - GMT 기준으로 작성
- max-age
    - 0이나 음수를 지정하면 쿠키 삭제
- 세션 쿠키: 만료 날짜를 생략하면 브라우저 종료 시까지만 유지
- 영속 쿠키: 만료 날짜를 입력하면 해당 날짜까지 유지

### 쿠키의 도메인
- 명시하는 경우 기준 도메인 + 서브 도메인에서 접근 가능
- 생략하는 경우 현재 문서 기준의 도메인만 접근 가능

### 쿠키의 경로
- 이 경로를 포함한 하위 경로 페이지만 쿠키에 접근할 수 있음
- 일반적으로 path=/ 루트로 지정

### 쿠키의 보안
- Secure
    - 쿠키는 http, https를 구분하지 않고 전송하나, Secure를 적용하면 https인 경우에만 전송함
- HttpOnly
    - XSS 공격 방지
    - 자바스크립트에서 접근 불가
    - HTTP 전송에만 사용
- Samesite
    - XSRF 공격 방지
    - 요청 도메인과 쿠키에 설정된 도메인이 같은 경웅만 쿠키 전송