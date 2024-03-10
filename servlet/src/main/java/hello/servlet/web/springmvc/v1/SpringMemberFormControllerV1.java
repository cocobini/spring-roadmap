package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// @Controller // 클래스 레벨에 존재하면 스프링 MVC가 애노테이션 기반 컨트롤러로 인식함
@RequestMapping // 클래스 레벨에 존재하면 스프링 MVC가 애노테이션 기반 컨트롤러로 인식함
@Component // @Controller 대신 @RequestMapping을 사용하기 때문에 Component 어노테이션도 필요
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}
