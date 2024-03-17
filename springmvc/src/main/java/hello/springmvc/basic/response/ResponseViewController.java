package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");

        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!!");
        return "response/hello";
    }

    /**
     * 리턴 타입이 void인 경우 URL을 리소스 경로로 인식
     * 권장하는 방식이 아님
     */
    @RequestMapping("/response/hello") // template/response/hello.html이 view가 됨
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello!!");
    }

}