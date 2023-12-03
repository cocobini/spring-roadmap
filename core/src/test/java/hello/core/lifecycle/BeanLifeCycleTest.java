package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
    
    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close(); // ApplicationContext 는 제공해주지 않는 메서드(보통 직접 닫을 일이 없어서)
    }

    @Configuration
    static class LifeCycleConfig {
        @Bean 
        // 초기화, 소멸 메서드를 빈 등록 시 함께 등록
        // destroyMethod 의 디폴트는 "(inferred)"로, close 혹은 shutdown이라는 메서드를 자동 호출
        // 디폴트 설정을 막으려면 destroyMethod = "" 과 같이 사용
        //(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
