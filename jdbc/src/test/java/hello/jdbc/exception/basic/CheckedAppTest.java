package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class CheckedAppTest {

    @Test
    void checked() {
        Controller controller = new Controller();
        assertThatThrownBy(() -> controller.request()) // 복구 불가능한 예외이고, 불필요한 의존관계가 생김
                .isInstanceOf(Exception.class);
    }
    
    static class Controller {
        Service service = new Service();
        public void request() throws SQLException, ConnectException {
            service.logic();
        }
    }
    
    static class Service {
        Repository repository = new Repository();
        NetworkClient networkClient = new NetworkClient();

        // throws Exception을 사용할 수는 있지만, 안티패턴이므로 사용에 유의
        // -> 처리해야 하는 중요한 에러도 놓칠 수 있음
        public void logic() throws SQLException, ConnectException {
            repository.call();
            networkClient.call();
        }
    }
    
    static class NetworkClient {
        public void call() throws ConnectException {
            throw new ConnectException("연결 실패");
        }
    }
    
    static class Repository {
        public void call() throws SQLException {
            throw new SQLException("ex");
        }
    }
    
}
