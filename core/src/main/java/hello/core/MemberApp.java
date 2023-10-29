package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//
//        // 기존 직접 new를 통해 생성하던 것을 appConfig에 요청하는 식으로 변경
//        MemberService memberService = appConfig.memberService();

        // ApplicationContext가 스프링 컨테이너라고 보면 됨
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // 빈 등록은 메서드 이름으로 되어있음
        // 두 번째 매개변수가 리턴타입이 된다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        // Ctrl + Alt + V 누르면 new XXX();만 적혀있던 걸
        // XXX xxx = new XXX(); 상태로 만들어줌
        Member member = new Member(1L, "memberA", Grade.VIP);

        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        // 순수 자바코드만으로 테스트 완료
        // 그러나, 늘 이렇게 작성하기는 불편하기 때문에 JUnit을 이용하는 게 바람직하다
        System.out.println("new: " + member.getName());
        System.out.println("find: " + findMember.getName());
    }
}
