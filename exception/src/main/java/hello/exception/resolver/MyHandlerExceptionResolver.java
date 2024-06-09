package hello.exception.resolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Exception ex) {

        try {
            if (ex instanceof IllegalArgumentException) {
                log.info("IllegalArgumentException resolver to 400");
                // Exception을 sendError로 바꿔치기(정상흐름처럼 변경)
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());

                // 빈 ModelAndView를 리턴하면 뷰를 렌더링하지 않고 정상흐름으로 서블릿이 리턴
                return new ModelAndView();
            }
        } catch (IOException e) {
            log.error("resolver ex", e);
        }

        // null을 리턴하면 기존에 발생한 예외를 서블릿 밖으로 던짐
        return null;
    }
}