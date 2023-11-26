package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor // final이 붙은 필드를 생성자를 통해 만들어줌
public class MemberServiceImpl implements MemberService{

    // 인터페이스에 대한 의존만 남음!
    private final MemberRepository memberRepository;

    // @Autowired // 의존관계 자동 주입
    // 기본 조회 전략은 '타입이 같은 빈을 찾아서 주입하는 것'
    // ac.getBean(MemberRepository.class)와 같은 기능
    // public MemberServiceImpl(MemberRepository memberRepository) { // 생성자 주입
    //     this.memberRepository = memberRepository;
    // }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트용 메서드
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
