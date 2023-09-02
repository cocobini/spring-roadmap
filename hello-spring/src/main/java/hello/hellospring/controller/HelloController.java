package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") // 서버로 요청이 들어오면 해당 메서드, url로 Mapping된 게 있는지 확인하고 처리
    public String Hello(Model model) {
        model.addAttribute("data", "hello?");
        return "hello"; // 리턴값이 문자열이면 viewResolver가 화면을 찾아서 처리
        // resources:templates/{viewName}.html이 기본 viewName 매핑
    }
}
