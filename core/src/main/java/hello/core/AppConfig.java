package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Spring 으로 전환
@Configuration // 싱글톤을 위한 어노테이션
public class AppConfig {

    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()
    // new 를 여러 번 호출해도 Singleton이 깨지지 않는 이유는?
    // 로그 출력을 통해 알아보자.

    // 참고
    // @Bean 어노테이션 사용 시 static 키워드를 사용하면 싱글톤을 보장하지 않으므로 주의


    // 실제로 어떤 구현체를 선택할 건지 여기서 설정한다.
    @Bean
    public MemberService memberService() {
        // MemberServiceImpl 클래스에 생성자를 만들고, 생성자를 통해서 객체를 만들어 리턴해줌

        System.out.println("call memberService()");
        return new MemberServiceImpl(memberRepository()); // Ctrl+Alt+M으로 Extract Method 기능 사용
    }

    // Bean 어노테이션을 통해 스프링 컨테이너에 등록
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call memberRepository()");
        return new MemoryMemberRepository();
    }

    // 이렇게 변경하면 역할이 명확히 보이고, 중복이 줄어들어 설정을 바꿔야 할 때 훨씬 편하다.
    @Bean
    public OrderService orderService() {
        System.out.println("call orderService()");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy(); // 구현체를 바꾸고 싶다면 이 부분만 교체하면 된다.
    }


}
