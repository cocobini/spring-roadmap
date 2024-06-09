package hello.exception.api;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ApiExceptionV2Controller {

    // 해당 클래스 내에 있는 @ExceptionHandler
    // -> 이 클래스 안에 Mapping된 URL에 대해서만 동작
    // @ControllerAdvice를 통해 여러 컨트롤러에서 발생하는 것들을 한꺼번에 처리할 수 있음

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(IllegalArgumentException.class) // 해당 예외를 처리
//    public ErrorResult illegalExHandle(IllegalArgumentException e) {
//
//        log.error("[exceptionHandle] ex", e);
//        // 정상로직으로 리턴 -> HttpStatusCode가 200이 되므로
//        // @ResponseStatus 어노테이션을 이용해 에러코드를 지정
//        return new ErrorResult("BAD", e.getMessage());
//    }
//
//    @ExceptionHandler // 컨트롤러 호출과 거의 유사하다고 보면 됨
//    public ResponseEntity<ErrorResult> userExHandle(UserException e) {
//        log.error("[exceptionHandle] ex", e);
//        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
//
//        // HttpStatus를 리턴값에서 직접 지정
//        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
//    }
//
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler
//    public ErrorResult exHandle(Exception e) { // 디테일한 예외처리로 핸들링이 안 된 경우
//        log.error("[exceptionHandle] ex", e);
//        return new ErrorResult("EX", "내부 오류");
//    }

    @GetMapping("/api2/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id) {
        if (id.equals("ex")) {
            throw new RuntimeException("잘못된 사용자");
        }
        if (id.equals("bad")) {
            throw new IllegalArgumentException("잘못된 입력 값");
        }
        if (id.equals("user-ex")) {
            throw new UserException("사용자 오류");
        }
        return new MemberDto(id, "hello " + id);
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String memberId;
        private String name;
    }
}
