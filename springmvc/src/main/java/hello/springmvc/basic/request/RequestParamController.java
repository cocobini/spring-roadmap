package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        // 반환 타입이 없으면서 이렇게 응답에 값을 직접 집어넣으면, view를 조회하지 않음
        response.getWriter().write("ok");
    }

    @ResponseBody // RestController를 쓴 것과 같다(view 조회를 하지 않고 HTTP 응답으로 만들어줌)
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            // 파라미터 이름으로 바인딩
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {
        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            // 요청 파라미터명과 바인딩할 변수의 변수명이 같으면 괄호 생략
            // SpringBoot 3.2부터는 자바 컴파일러에 -parameters 옵션을 넣어주어야 애노테이션에 적는 이름을 생략할 수 있음
            @RequestParam String username,
            @RequestParam int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * String, int 등의 단순 타입이면 @RequestParam 도 생략 가능
     */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * @RequestParam.required 사용 시 주의할 점
     * 1.
     * /request-param-required?username= 처럼 요청하면 빈문자로 통과함(빈문자와 null은 다르다!)
     * 2.
     * /request-param-required 처럼 요청을 보내는데 매핑을 int age에 하도록 되어있는 경우
     * required를 false로 주면 성공할 것 같지만, 에러가 발생함
     * int형 변수에 null을 입력하는 것은 불가능하기 때문
     * 즉, requried는 null을 허용하는 것이지 '매핑을 안 하는' 옵션이 아님
     * -> 자료형을 Integer로 바꾸거나, defaultValue를 사용하여 해결
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username, // true이면 해당 파라미터가 필수로 들어와야 함
            @RequestParam(required = false) Integer age) { // false이면 해당 파라미터가 존재하지 않아도 됨
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            // defaultValue는 빈 문자의 경우에도 적용
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * @RequestParam Map, MultiValueMap
     * Map(key=value)
     * MultiValueMap(key=[value1, value2, ...]) ex) (key=userIds, value=[id1, id2])
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"),
                paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) { // @ModelAttribute가 setter를 이용해 자동으로 바인딩
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    /**
     * @ModelAttribute 생략 가능
     * String, int 같은 단순 타입 = @RequestParam
     * argument resolver 로 지정해둔 타입 외 = @ModelAttribute
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

}