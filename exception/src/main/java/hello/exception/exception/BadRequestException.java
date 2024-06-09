package hello.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// ResponseStatusExceptionResolver가 상태코드를 지정함
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "잘못된 요청 오류") // 내부적으로 sendError를 호출
public class BadRequestException extends RuntimeException {
}
