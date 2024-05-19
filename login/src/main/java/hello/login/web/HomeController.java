package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

//    @GetMapping("/")
    public String home() {
        return "home";
    }

//    @GetMapping("/")
    public String homeLogin(
            // required를 false로 해야 쿠키가 없는 사용자도 이용할 수 있음
            @CookieValue(name = "memberId", required = false) Long memberId,
            Model model
    ) {

        if (memberId == null) {
            return "home";
        }

        // 로그인
        Member loginMember = memberRepository.findById(memberId);
        if (loginMember == null) {
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
    }

//    @GetMapping("/")
    public String homeLoginV2(HttpServletRequest request, Model model) {

        // 세션 관리자에 저장된 회원 정보 조회
        Member member = (Member) sessionManager.getSession(request);

        // 로그인
        if (member == null) {
            return "home";
        }

        model.addAttribute("member", member);
        return "loginHome";
    }

//    @GetMapping("/")
    public String homeLoginV3(HttpServletRequest request, Model model) {

        // 로그인 안 한 사람은 세션이 없어야 하므로 매개변수 false
        // 세션은 메모리를 사용하므로 꼭 필요할 때만 사용
        HttpSession session = request.getSession(false);
        if(session == null) {
            return "home";
        }

        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (member == null) {
            return "home";
        }

        model.addAttribute("member", member);
        return "loginHome";
    }

    @GetMapping("/")
    public String homeLoginV3Spring(
            // 복잡한 로직을 깔끔하게 처리할 수 있음
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member,
            Model model) {
        if (member == null) {
            return "home";
        }

        model.addAttribute("member", member);
        return "loginHome";
    }

}