package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 1. 파라미터 전송 기능
 * http://localhost:8080/request-param?username=hello&age=20
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RequestParamServlet.service");

        System.out.println("전체 파라미터 조회 시작");
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + " = " +
                        request.getParameter(paramName)));

        System.out.println("전체 파라미터 조회 끝\n");

        System.out.println("단일 파라미터 조회");
        // 이름이 같은 값이 여러 개 있을 때는 가장 먼저 들어온 거 보여줌
        System.out.println("username = " + request.getParameter("username"));
        System.out.println("age = " + request.getParameter("age") + "\n");

        System.out.println("이름이 같은 복수 파라미터 조회");
        String[] usernames = request.getParameterValues("username");
        for(String username : usernames) {
            System.out.println("username = " + username);
        }

        response.getWriter().write("ok");
    }
}
