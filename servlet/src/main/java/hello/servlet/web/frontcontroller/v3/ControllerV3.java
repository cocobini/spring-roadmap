package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    // Servlet에 종속되지 않음
    ModelView process(Map<String, String> paramMap);
}
