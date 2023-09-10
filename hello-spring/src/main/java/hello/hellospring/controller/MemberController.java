package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // 빈 등록
public class MemberController {

    // MemberController의 생성자를 통해 주입받음
    private final MemberService memberService;


    /*
    @Autowired // '필드 주입' 방법(한 번 의존성이 주입된 뒤에 수정이 어려우므로 권장하지 않음)
    private final MemberService memberService;

    @Autowired // 'setter 주입' 방법(불변성이 보장되지 않으므로 권장하지 않음)
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
     */

    @Autowired // 생성자를 이용한 의존성 주입(권장됨)
    public MemberController (MemberService memberService) { // MemberService도 어노테이션을 이용해 빈으로 등록해줘야 함
        this.memberService = memberService;
    }
}
