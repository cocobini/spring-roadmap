# 섹션1. 인터넷 네트워크


## IP
### IP: 인터넷 프로토콜 역할
- 지정한 IP 주소(IP Address)에 데이터 전달
- 패킷(Packet)이라는 통신 단위로 데이터 전달
- 클라이언트가 규격(패킷)에 맞게 출발지 IP, 목적지 IP, 데이터를 담아서 보내면
, 서버도 규격에 맞게 응답

그러나 IP 프로토콜만으로는 한계가 있음
- 비연결성: 패킷을 받을 대상이 없거나, 서비스가 불능 상태여도 패킷을 전송함
- 비신뢰성: 중간에 패킷이 사라지거나, 패킷이 순서대로 안 오는 경우 대처하기 힘듦
- 프로그램 구분: 같은 IP를 사용하는 서버에서 통신하는 애플리케이션이 복수개면?

### TCP와 UDP
- TCP/IP 패킷 정보
    - IP 패킷: 출발지 IP, 목적지 IP, 기타 ...
    - TCP 세그먼트: 출발지 PORT, 목적지 PORT, 전송 제어, 순서, 검증 정보 ...

- TCP의 특징
    - TCP(Trancmission Control Protocol): 전송 제어 프로토콜
    - 연결 지향 - TCP 3 way handshake(가상 연결, 진짜로 연결된 게 아니라 개념적으로 연결된 것)
        - SYN: 접속 요청
        - ACK: 요청 수락
        - SYN -> SYN+ACK -> ACK -> 데이터 전송(ACK와 같이 하기도 함)
    - 데이터 전달 보증, 순서 보장
        - TCP 세그먼트가 갖고 있는 정보를 이용
    - 신뢰할 수 있는 프로토콜로, 대부분의 애플리케이션에서 사용하는 방식

- UDP의 특징
    - UDP(User Datagram Protocol): 사용자 데이터그램 프로토콜
    - IP와 비슷하며, PORT, 체크섬 정도가 추가됨 -> 애플리케이션에서 추가작업 필요
    - TCP 3 way handshake 없음
        - 단순하고 빠름
    - 데이터 전달을 보증하지 않으며, 순서도 보장되지 않음


## PORT
- 같은 IP 내에서 프로세스를 구분함
- 0 ~ 65535 사이에서 할당 가능
- 0 ~ 1023은 잘 알려진 포트(Well-known port)로, 사용하지 않는 게 좋음
    - FTP - 20, 21(SFTP - 22)
    - Telnet - 23
    - HTTP - 80
    - HTTPS - 443


## DNS
DNS(Domain Name System)는 도메인 네임 시스템의 약자로, 도메인 명을 IP주소로 변환해주는 시스템
- IP가 변경되어도 같은 주소를 사용할 수 있음
- 숫자로 된 IP보다 google.com 같은 주소가 비교적 기억하기 쉬움