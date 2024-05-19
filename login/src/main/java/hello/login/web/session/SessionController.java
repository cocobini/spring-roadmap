package hello.login.web.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
public class SessionController {

    @GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null) {
            return "세션 없음";
        }

        // 세션에 보관된 값들을 모두 출력
        session.getAttributeNames().asIterator()
                .forEachRemaining(name -> log.info("session name={}, value={}", name, session.getAttribute(name)));

        log.info("sessionId={}", session.getId());

        // 세션을 비활성화 시키는 시간
        log.info("maxInactiveInterval={}", session.getMaxInactiveInterval());

        // 생성된 시간(생성일자)
        log.info("creationTime={}", session.getCreationTime());

        // 마지막 접근 시간(중요!)
        log.info("lastAccessedTime={}", session.getLastAccessedTime());

        // 새로 만들어진 세션인지
        log.info("isNew={}", session.isNew());

        return "세션 출력";
    }
}
