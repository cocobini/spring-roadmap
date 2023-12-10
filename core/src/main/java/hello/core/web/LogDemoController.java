package hello.core.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    
    private final LogDemoService logDemoService;
    // scope 문제로 의존성 주입 단계에서 오류가 나므로, ObjectProvider를 이용한다.
    // private final ObjectProvider<MyLogger> myLoggerProvider;

    private final MyLogger myLogger;

    @RequestMapping("/log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        // MyLogger myLogger = myLoggerProvider.getObject();

        String requestURL = request.getRequestURL().toString();
        myLogger.setReqeustURL(requestURL);

        myLogger.log("controller test");

        Thread.sleep(1000);

        logDemoService.logic("testId");
        return "OK";
    }
}
