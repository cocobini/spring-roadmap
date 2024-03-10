package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
    private final MemberRepository memberRepository = MemberRepository.getInstance();

    // 한 컨트롤러에 여러 개의 Mapping을 작성할 수 있음

    @RequestMapping(value = "/new-form", method = RequestMethod.GET) // Method를 지정하지 않으면 GET, POST 요청을 모두 받음
    public String newForm() {
        return "new-form"; // 문자열을 리턴하면 View 이름으로 인식함
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(
            // @RequestParam 어노테이션을 이용해 데이터를 받을 수 있음
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model // ModelAndView 대신 Model 사용
    ) {

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);

        return "save-result";
    }

    @GetMapping // @RequestMapping + RequestMethod.GET(POST는 PostMapping)
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);

        return "members";
    }
}
