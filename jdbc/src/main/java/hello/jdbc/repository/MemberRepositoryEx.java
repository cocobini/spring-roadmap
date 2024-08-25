package hello.jdbc.repository;

import hello.jdbc.domain.Member;

import java.sql.SQLException;

public interface MemberRepositoryEx {
    // 구현체가 예외를 던지려면, 인터페이스에도 throws 구문이 작성되어야 함
    Member save(Member member) throws SQLException; // 인터페이스가 특정 기술에 종속되어버림
    Member findById(String memberId) throws SQLException;
    void update(String memberId, int money) throws SQLException;
    void delete(String memberId) throws SQLException;
}
