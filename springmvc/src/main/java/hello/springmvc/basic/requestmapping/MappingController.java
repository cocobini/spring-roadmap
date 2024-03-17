package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * /hello-basic, /hello-basic/, /hello-go, /hello-go/ 전부 가능
     * GET, POST, PUT, PATCH, DELETE 전부 가능
     */
    @RequestMapping(value = {"/hello-basic", "/hello-go"})
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    /**
     * 특정 HTTP 메서드 요청만 허용
     * GET, HEAD, POST, PUT, PATCH, DELETE
     */
    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }

    /**
     * @RequestMapping + RequestMethod 애노테이션 사용 가능
     * @GetMapping, @PostMapping, @PutMapping, @PatchMapping, @DeleteMapping
     */
    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mapping-get-v2");
        return "ok";
    }

    /**
     * PathVariable 사용, 변수명이 같으면 괄호 생략 가능(애노테이션은 써줘야 함!)
     *
     * @PathVariable("userId") String userId -> @PathVariable String userId
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath userId={}", data);
        return "ok";
    }

    /**
     * PathVariable 여러 개 사용
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long
            orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    /**
     * 특정 파라미터로 추가 매핑, 표현을 통해 다양한 조건 붙이기 가능
     * params="mode", mode가 있어야 된다
     * params="!mode" mode가 없어도 된다
     * params="mode=debug" mode의 값이 debug여야 한다
     * params="mode!=debug" (! = ) mode의 값이 debug가 아니어야 한다
     * params = {"mode=debug","data=good"} 둘 중에 하나를 만족해야 한다
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 특정 헤더로 추가 매핑, 표현을 통해 다양한 조건 붙이기 가능
     * params와 유사하지만 헤더에 조건을 걸어주는 것
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * Content-Type 헤더 기반 추가 매핑, Header의 Content-Type이 조건에 맞는 경우에만 호출
     * 마찬가지로 다양한 조건 사용 가능
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * Accept 헤더 기반 Media Type
     * Accept할 수 없으면 406 Not Acceptable 리턴
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     */
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}