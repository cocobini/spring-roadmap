package hello.exception;

import hello.exception.filter.LogFilter;
import hello.exception.interceptor.LogInterceptor;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns( // 제외할 URL
                        "/css/**", "/*.ico"
                        , "/error", "/error-page/**" // 오류 페이지 경로를 전부 제외함
                );
    }

//    @Bean
    public FilterRegistrationBean logFilter() {

        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();

        filterRegistrationBean.setFilter(new LogFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");

        // 여러 가지 등록할 수 있음
        // 아무것도 넣지 않으면 디폴트는 DispatcherType.REQUEST(클라이언트 요청일 때만 필터 호출)
        filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ERROR);

        return filterRegistrationBean;
    }
}
