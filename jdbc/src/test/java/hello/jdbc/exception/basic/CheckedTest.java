package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class CheckedTest {

    @Test
    void checked_catch() {
        Service service = new Service();
        service.callCatch();
    }


    @Test
    void checked_throw() {
        Service service = new Service();
        Assertions.assertThatThrownBy(() -> service.callThrow())
                .isInstanceOf(MyCheckedException.class);
    }


    static class MyCheckedException extends Exception {
        public MyCheckedException(String message) {
            super(message);
        }
    }


    static class Service {
        Repository repository = new Repository();

        // try ~ catch 절을 이용해 예외를 잡아서 처리
        public void callCatch() {
            try {
                repository.call();
            } catch (MyCheckedException e) { // Exception이 최상위 클래스이므로 Exception으로 잡아도 됨
                // 예외 처리 로직
                log.info("예외 처리, message={}", e.getMessage(), e);
            }
        }

        public void callThrow() throws MyCheckedException {
            repository.call();
        }
    }

    static class Repository {

        // Checked Exception은 catch하지 않는 경우 throws로 외부에 던져줘야 함
        public void call() throws MyCheckedException { // Exception이 최상위 클래스이므로 Exception으로 잡아도 됨
            // 던지게 되는 예외만 명시적으로 작성하는 게 좋다(Exception 대신 MyCheckedException 던지기)
            throw new MyCheckedException("ex");
        }
    }
}
