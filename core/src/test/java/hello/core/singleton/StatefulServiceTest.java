package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefuleServiceSingleton() {
         ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

//        // 사용자 A -> 10,000원 주문
//        statefulService1.order("userA", 10000);
//
//        // 사용자 B -> 20,000원 주문
//        statefulService2.order("userB", 20000);
//
//        int price = statefulService1.getPrice();
//        System.out.println("price = " + price);
//
//        // 10,000원이 나와야 정상인데 20,000원으로 조회되는 상황
//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

        // 사용자 A -> 10,000원 주문
        int userAPrice = statefulService1.order("userA", 10000);

        // 사용자 B -> 20,000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        // 원하는대로 잘 조회됨
        Assertions.assertThat(userAPrice).isEqualTo(10000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}