package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// 빌드할 때는 빠지기 때문에 운영환경에 배포 안 됨(경로에 주의하자!)
public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        Member member = new Member(1L, "memberA", Grade.VIP);

        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // 테스트에 실패했을 때 원인을 알기 쉬움
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
