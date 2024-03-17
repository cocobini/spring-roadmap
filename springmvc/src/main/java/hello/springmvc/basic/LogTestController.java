package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger; //slf4j 패키지의 Logger를 사용해야 함
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {

    // @Slf4j 어노테이션을 이용해도 됨
    // private final Logger log = LoggerFactory.getLogger(getClass()); // getClass() == LogTestController.class

    @RequestMapping("/log-test")
    public String logTest() { // RestController를 사용하면 리턴타입이 String일 때 View가 아닌 문자열 자체를 리턴
        String name = "Spring";

        // name = Spring
        System.out.println("name = " + name);

        // 2024-03-11T22:14:01.554+09:00  INFO 9720 --- [springmvc] [nio-8080-exec-1] h.springmvc.basic.LogTestController      : info log=Spring
        log.info("info log={}", name); // "info log=" + name 과 같은 사용은 지양(불필요한 연산을 수행하게 됨)

        // 로그 레벨
        log.trace("trace");
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");

        return "ok";
    }

}
