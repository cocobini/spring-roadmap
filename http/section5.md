# HTTP 메서드 활용


## 클라이언트 -> 서버 데이터 전송

### 데이터 전송 방법
- 쿼리 파라미터로 데이터 전송
    - GET 방식
    - 주로 정렬 필터(검색어)
- 메시지 바디로 데이터 전송
    - POST, PUT, PATCH 방식
    - 회원 가입, 상품 주문, 리소스 등록, 리소스 변경

### 4가지 케이스
- 정적 데이터 조회
    - 이미지, 정적 텍스트
    - 단순히 리소스 경로만으로 조회(GET)
- 동적 데이터 조회
    - 주로 검색, 게시판 목록에서 정렬 필터(검색어, 조회 조건을 줄여줌)
    - 쿼리 파라미터 사용
- HTML Form을 통한 데이터 전
    - 회원가입, 주문, 데이터 변경 등
    - 폼태그를 사용해 HTTP 요청을 생성함
    - 파일 전송 시에는 <b>multipart/form-data</b> 사용
    - Form 전송은 GET, POST 메서드만 지원함
- HTTP API를 통한 데이터 전송
    - 회원가입, 주문, 데이터 변경 등
    - 서버 -> 서버, 앱 클라이언트, 웹 클라이언트(Ajax)
    - 요청을 직접 만들어서 전송(보통 라이브러리로 생성)
    - 모든 메서드 방식을 사용할 수 있음
    - Content-Type은 application/json을 주로 사용함


## HTTP API 설계
리소스를 식별하도록 해야 함
### 예시 - 회원 관리 시스템
- 회원 목록 /members -> GET
- 회원 등록 /members -> POST
    - 클라이언트는 등록될 리소스의 URI를 모름
    - 서버가 새로 등록된 리소스 URI를 생성해줌
    - 서버가 관리하는 리소스 디렉토리를 컬렉션이라고 함(이 예시에서는 /members가 컬렉션)
- 회원 조회 /members/{id} -> GET
- 회원 수정 /members/{id} -> PATCH, PUT, POST
- 회원 삭제 /members/{id} -> DELETE

### 예시 - 파일 관리 시스템
- 파일 목록 /files -> GET
- 파일 조회 /files/{filename} -> GET
- 파일 등록 /files/{filename} -> PUT
    - 클라이언트가 리소스 URI을 알고 있어야 함(클라이언트가 직접 URI를 지정)
    - 클라이언트가 관리하는 리소스 저장소를 스토어라고 함(이 예시에서는 /files가 스토어)
- 파일 삭제 /files/{filename} -> DELETE
- 파일 대량 등록 /files -> POST

### HTML Form 사용
- GET, POST 메서드만 사용해야 함
- 제약을 해결하기 위해 동사로 된 리소스 경로 사용(컨트롤 URI)
    - Tip: 회원 등록 폼/회원 등록의 경우 URL을 맞추면 문제 시 처리를 하기 용이함

### 참고자료
- 문서(document)
    - 단일개념(파일 하나, 객체 인스턴스, DB row)
    - ex) /members/100, files/star.jpg
- 컬렉션(collection)
    - 서버가 관리하는 리소스 디렉터리
    - 서버가 리소스의 URI를 생성하고 관리
    - ex) /members
- 스토어(store)
    - 클라이언트가 관리하는 자원 저장소
    - 클라이언트가 리소스의 URI를 알고 관리
    - ex) /files
- 컨트롤러(controller), 컨트롤 URI
    - 문서, 컬렉션, 스토어로 해결하기 어려운 추가 프로세스 실행
    - 동사를 직접 사용
    - ex) /members/{id}/delete
- https://restfulapi.net/resource-naming