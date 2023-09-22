package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository를 상속하기만 해도 구현체를 알아서 만들어서 빈으로 등록해줌
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, // <Entity, PK Type>
                                                        MemberRepository {

    // JpaRepository를 상속하면 기본적인 CRUD 쿼리를 제공해줌
    // find, save, delete 등...

    // 특정 규칙에 맞춰 메소드명을 작성하면 쿼리를 만들어줌
    @Override
    Optional<Member> findByName(String name); // select m from Member m where m.name = ?

    // 복잡한 동적 쿼리의 경우 Querydsl을 이용

    // JPA만으로 처리하기 힘든 경우 JDBC, MyBatis 등을 병용
}
