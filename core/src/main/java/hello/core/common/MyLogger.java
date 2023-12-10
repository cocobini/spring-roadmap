package hello.core.common;

import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) // 클래스는 TARGET_CLASS, 인터페이스는 INTERFACES 선택
// proxyMode 옵션을 사용하면 가짜 프록시객체를 만들어줌(Provider 처럼 동작함)
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setReqeustURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("["+uuid+"] ["+requestURL+"] " +  message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("["+uuid+"] request scope bean create: " + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("["+uuid+"] request scope bean close: " + this);
    }
}
