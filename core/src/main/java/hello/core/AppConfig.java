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
@Configuration
public class AppConfig {

    // 실제로 어떤 구현체를 선택할 건지 여기서 설정한다.
    @Bean
    public MemberService memberService() {
        // MemberServiceImpl 클래스에 생성자를 만들고, 생성자를 통해서 객체를 만들어 리턴해줌
        return new MemberServiceImpl(memberRepository()); // Ctrl+Alt+M으로 Extract Method 기능 사용
    }

    // Bean 어노테이션을 통해 스프링 컨테이너에 등록
    @Bean
    private static MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    // 이렇게 변경하면 역할이 명확히 보이고, 중복이 줄어들어 설정을 바꿔야 할 때 훨씬 편하다.
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    private static DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy(); // 구현체를 바꾸고 싶다면 이 부분만 교체하면 된다.
    }


}
