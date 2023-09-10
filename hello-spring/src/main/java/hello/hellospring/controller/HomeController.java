package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // Controller가 정적 리소스보다 우선순위를 가지므로 index 대신 home.html로 이동
    public String home() {
        return "home";
    }
}
