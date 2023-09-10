package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean // MemberService가 빈으로 등록됨
    public MemberService memberService() {
        return new MemberService(memberRepository()); // memberRepository 주입
    }

    @Bean // MemberRepository가 빈으로 등록됨
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository(); // MemberRepository는 인터페이스이므로 구현체를 이용해 객체 생성
    }
}
