package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();

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
