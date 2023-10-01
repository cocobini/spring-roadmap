package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// Component를 메타 애노테이션으로 갖는 Service 애노테이션을 이용해 Bean 등록
// @Service
@Transactional // JPA 사용 시 트랜잭션 처리 필요
public class MemberService {
    private final MemberRepository memberRepository;

//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) {

        // 메서드 동작 시간 측정
        long start = System.currentTimeMillis();

        try {
            // 중복된 이름은 가입 불가하도록 함
            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();
        } finally { // 예외가 발생해도 처리되도록 finally 블록에 작성
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        } // 이 메서드를 모든 곳에 찍어줘야 한다면 굉장히 번거로울 것
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { // 리턴값이 null이 아니면 예외 던짐
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
