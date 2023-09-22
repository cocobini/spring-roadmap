package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

//    private DataSource dataSource; // 스프링이 자체적으로 빈을 생성해줌
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

//    private EntityManager em;
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean // MemberService가 빈으로 등록됨
    public MemberService memberService() {
        return new MemberService(memberRepository); // memberRepository 주입
    }

//    @Bean // MemberRepository가 빈으로 등록됨
//    public MemberRepository memberRepository() {
//        // MemberRepository는 인터페이스이므로 구현체를 이용해 객체 생성
//        // 애플리케이션을 조립하는 코드만 수정하면 다른 부분을 전혀 수정하지 않아도 됨(다형성)
//
//        // return new MemoryMemberRepository();
//        // return new JdbcMemberRepository(dataSource);
//        // return new JpaMemberRepository(em);
//
//    }
}
