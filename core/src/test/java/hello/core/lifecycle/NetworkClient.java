package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient // implements InitializingBean, DisposableBean 
{
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        // 생성자에서 아래 메서드를 실행하면? 내가 원하는 시점보다 빠르게 실행됨
        // 스프링은 객체를 생성한 뒤에 의존관계를 주입하기 때문!
        // connect();
        // call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작 시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    // 서비스 종료 시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }


    // InitializingBean, DisposableBean 사용 시
    // // 의존관계 주입이 끝나면 실행
    // @Override
    // public void afterPropertiesSet() throws Exception {
    //     connect();
    //     call("초기화 연결 메시지");
    // }

    // // 종료될 때 호출
    // @Override
    // public void destroy() throws Exception {
    //         disconnect();
    // }

    // 애노테이션을 통해 초기화, 소멸 시 동작
    @PostConstruct
    public void init() throws Exception {
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() throws Exception {
        disconnect();
    }
}
