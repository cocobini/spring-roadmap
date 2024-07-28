package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@RequiredArgsConstructor
public class MemberServiceV2 {

    private final DataSource dataSource;
    private final MemberRepositoryV2 memberRepository;

    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        Connection con = dataSource.getConnection();

        try {
            con.setAutoCommit(false);

            // 비즈니스 로직 수행
            bizLogic(fromId, toId, money, con);

            // 예외가 발생하지 않은 경우에만 commit
            con.commit();
        } catch (Exception e) {
            // 예외 발생 시 롤백
            con.rollback();
            throw new IllegalStateException();
        } finally {
            if(con != null) {
                try {
                    // 커넥션이 삭제되는 것이 아니라 풀에 되돌아가므로 오토커밋 설정 원복 후 close
                    con.setAutoCommit(true);
                    con.close();
                } catch (Exception e) {
                    log.info("error", e); // Exception을 로그로 남길 때는 {}를 쓰지 않음
                }
            }
        }


    }

    private void bizLogic(String fromId, String toId, int money, Connection con) throws SQLException {
        Member fromMember = memberRepository.findById(con, fromId);
        Member toMember = memberRepository.findById(con, toId);
        memberRepository.update(con, fromId, fromMember.getMoney() - money);
        validation(toMember);
        memberRepository.update(con, toId, toMember.getMoney() + money);
    }

    private void validation(Member toMember) {
        if (toMember.getMemberId().equals("ex")) {
            throw new IllegalStateException("이체중 예외 발생");
        }
    }
}
