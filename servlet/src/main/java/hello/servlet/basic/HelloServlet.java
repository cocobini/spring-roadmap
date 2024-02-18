package hello.servlet.basic;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    // HTTP 요청을 통해 매핑된 URL이 호출되면, 서블릿 컨테이너가 이 메서드를 실행(public이 아닌 protected!)
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");

        // interface 구현체가 찍힘
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        // 쿼리 파라미터 사용
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        // 리턴
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("Hello " + username); // message body
    }
}
