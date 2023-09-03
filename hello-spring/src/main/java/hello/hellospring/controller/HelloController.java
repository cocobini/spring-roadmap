package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") // 서버로 요청이 들어오면 해당 메서드, url로 Mapping된 게 있는지 확인하고 처리
    public String hello(Model model) {
        model.addAttribute("data", "hello?");
        return "hello"; // 리턴값이 문자열이면 viewResolver가 화면을 찾아서 처리
        // resources:templates/{viewName}.html이 기본 viewName 매핑
    }

    @GetMapping("hello-mvc")
    public String helloMvc(
            // required 값을 true(default)에서 false로 바꾸면 parameter를 넘기지 않아도 됨
            @RequestParam(name="name", required = false) String name,
            Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // http Body에 데이터를 직접 넣음
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody // 객체를 리턴할 경우 JSON 방식으로 리턴하는 게 디폴트
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
