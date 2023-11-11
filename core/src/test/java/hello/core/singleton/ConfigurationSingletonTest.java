package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        // @Bean 등록 시 private static -> public으로 변경
        System.out.println("memberRepository = " + memberRepository);
        System.out.println("memberRepository1 = " + memberRepository1);
        System.out.println("memberRepository2 = " + memberRepository2);

        assertThat(memberRepository).isSameAs(memberRepository1).isSameAs(memberRepository2);
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 설정파일 자체도 빈이 된다.
        AppConfig bean = ac.getBean(AppConfig.class);

        // bean.getClass() = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$...
        // 스프링이 CGLIB라는 바이트코드 조작 라이브러리를 사용해 AppConfig 클래스를 상속받은 임의의 새 클래스를 만들고,
        // 해당 클래스를 스프링 빈으로 등록한 것임
        System.out.println("bean.getClass() = " + bean.getClass());
    }
}
