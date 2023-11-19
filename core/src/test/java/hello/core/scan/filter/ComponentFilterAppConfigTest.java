package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.context.annotation.ComponentScan.Filter;

public class ComponentFilterAppConfigTest {
    
    @Test
    void filterScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        BeanA beanA = ac.getBean("beanA", BeanA.class);
        Assertions.assertThat(beanA).isNotNull();

        org.junit.jupiter.api.Assertions.assertThrows(
            NoSuchBeanDefinitionException.class, () -> ac.getBean("beanB", BeanB.class)
        );
    }

    @Configuration
    @ComponentScan(
        includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
        // FilterType 옵션
        // ANNOTATION: 디폴트. 애노테이션을 인식해서 동작
        // ASSIGNABLE_TYPE: 지정한 타입과 자식타입을 인식
        // ASPECTJ: AspectJ 패턴 사용
        // REGEX: 정규표현식
        // CUSTOM: TypeFilter 인터페이스를 구현하여 처리
    )
    static class ComponentFilterAppConfig {
        
    }
}
