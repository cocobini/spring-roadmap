# 섹션2. URI와 웹 브라우저 요청 흐름


## URI(Uniform Resource Identifier)
### URI의 의미
- Uniform: 리소스를 식별하는 통일된 방식
- Resource: 자원, URI로 식별할 수 있는 것
- Identifier: 다른 항목과 구분하기 위한 정보

### URI = URL(Locator) + URN(Name)
- URL
    - 리소스의 위치를 지정(변경 가능)
    - 예시: https://blog.naver.com/yojopojo?Redirect=Write&categoryNo=54
    - 보통 URI = URL 처럼 사용함
- URN
    - 리소스에 이름을 부여(위치는 변하지만 이름은 변하지 않음)
    - 예시: urn:blog:yojopojo:study
    - URN만으로 실제 리소스를 찾을 수 있는 방법이 보편화되지 않음


## URL의 문법
### scheme://[userinfo@]host[:port][/path][?query][#fragment]

### scheme
- 주로 프로토콜이 사용됨
    - 프로토콜: 어떤 방식으로 자원에 접근할 것인가에 대한 규칙(http, https, ftp 등)
- http는 80, https는 443 포트를 주로 사용

### userinfo
- 거의 사용하지 않음
- 사용자 정보를 포함해서 인증함

### host
- 도메인, IP 주소를 사용할 수 있음

### port
- 접속 포트
- 일반적으로 생략함(생략 시 http는 80, https는 443)

### path
- 리소스 경로로, 계층적 구조로 되어있음
- 예시
    - /home/file1.jpg
    - /members
    - /members/100

### query
- key&value 형태
- ?로 시작, &로 추가 가능
    - 예시: name=apple&color=red
- query parameter, query string 등으로 불림

### fragment
- 많이 사용하지는 않음
- html 내부 북마크 등에 사용
- 서버에 전송하는 정보 아님


## 웹 브라우저 요청 흐름
1. 요청 생성 시 DNS 조회, PORT 정보 확인
2. HTTP 요청 메시지 생성
    1. 웹 브라우저가 HTTP 메시지 생성
    2. SOCKET 라이브러리를 통해 전달함
        - A: TCP/IP 연결(IP, PORT)
        - B: 데이터 전달
    3. TCP/IP 패킷 생성, HTTP 메시지 포함
3. HTTP 요청
4. 서버가 HTTP 응답 메시지를 생성
5. 응답 수신, 화면 렌더링