package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/* 컨테이너까지 올리는 테스트를 통합테스트라고 하는데, 최소한 이런 개입 없이
 작은 단위로 테스트하는 것이 좋음(설계가 잘 된 테스트) */
@SpringBootTest // 스프링 컨테이너와 테스트를 함께 신행
@Transactional // 테스트 종료 시 Rollback -> 테스트 반복 실행 가능
class MemberServiceIntegrationTest {

    // 테스트는 '테스트'가 목적이기 때문에 간편하게 작성해도 된다!
    @Autowired MemberService memberService; // 필드인젝션
    @Autowired MemberRepository memberRepository;

    @Test
    @Commit // 트랜잭션 단위를 커밋할 수 있음
    void join() {
        Member member = new Member();
        member.setName("hello");

        Long saveId = memberService.join(member);

        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void joinValidate() {
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        memberService.join(member1);
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }

        IllegalStateException e
                = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}