package hello.servlet.web.frontcontroller.v1;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV1 {

    // Servlet과 유사하게 작성
    // 프론트 컨트롤러는 이 인터페이스를 호출하여 구현과 관계없이 일관적인 로직을 수행
    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
