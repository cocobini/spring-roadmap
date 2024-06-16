package hello.typeconverter;

import hello.typeconverter.converter.*;
import hello.typeconverter.formatter.MyNumberFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 컨버터를 직접 추가해주는 경우 더 높은 '우선순위'를 가짐

        // 우선순위를 위해 기존 등록한 내용을 주석처리(컨버터가 포맷터보다 우선순위가 높음)
//        registry.addConverter(new StringToIntegerConverter());
//        registry.addConverter(new IntegerToStringConverter());
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());
        // 추가
        registry.addFormatter(new MyNumberFormatter());
    }

}
