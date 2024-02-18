package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter // Json 라이브러리가 기본으로 호출(Java 프로퍼티 접근법)
@ToString
public class HelloData {
    private String username;
    private int age;
}
