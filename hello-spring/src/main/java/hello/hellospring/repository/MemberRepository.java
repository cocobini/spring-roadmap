package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    // null을 리턴할 여지가 있는 경우 Optional을 통한 처리(Java 8부터 사용 가능)
    Optional<Member> findById(Long id);
    Optional<Member> findByname(String name);

    List<Member> findAll();
}
