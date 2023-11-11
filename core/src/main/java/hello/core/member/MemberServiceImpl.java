package hello.core.member;

public class MemberServiceImpl implements MemberService{

    // 인터페이스에 대한 의존만 남음!
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) { // 생성자 주입
        this.memberRepository = memberRepository;
    }

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
