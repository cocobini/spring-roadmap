package hello.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

@Configuration
@ComponentScan ( // @Component 애노테이션이 붙은 클래스를 Bean으로 등록해준다.

    // @Configuration이 메타 애노테이션으로 @Component를 사용 중이므로, Configuration이 등록되지 않게끔 제외시켜준다.
    // AppConfig 클래스에 등록된 빈들과 충돌이 날 가능성이 있기 때문에!(보통 제외하지는 않는다. 예제코드를 살리기 위해서 한 것!)
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
    // 반대로 includeFilters 는 대상을 추가로 지정함

    // 컴포넌트 스캔 시작 위치를 직접 지정
    // 해당 패키지부터 그 패키지의 하위 패키지까지 탐색함
    // basePackages = "hello.core.member"

    // basePackageClasses는 지정한 클래스의 패키지를 탐색 시작 위치로 지정
    // basePackageClasses = AutoAppConfig.class

    // 지정하지 않으면 @ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작 위치
    // 관례대로 사용하는 것을 권장함(디폴트 설정 사용)

    // @Controller, @Service, @Repository, @Configuration 모두 @Component를 메타 애노테이션으로 가짐
) 
public class AutoAppConfig {
    // ComponentScan의 네이밍 전략은 '클래스명을 사용하되 맨 앞글자만 소문자를 쓰는 것'
    // 빈 이름을 직접 지정하고 싶으면 @Component("빈 이름") 으로 부여

    // 아래와 같이 자동 등록 빈과 수동 등록 빈의 이름이 겹치는 경우
    // 수동 등록 빈이 우선권을 가진다.(수동 빈이 오버라이딩)
    // -> 의도하지 않게 오버라이딩되면 오류가 발생할 수 있고, 원인을 찾기 어려우므로
    //  최근에는 디폴트 설정이 '이름이 겹치면 에러가 발생'하도록 되어있음
    @Bean(name = "memoryMemberRepository")
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
