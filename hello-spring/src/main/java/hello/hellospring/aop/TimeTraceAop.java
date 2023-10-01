package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
//@Component를 사용해도 되지만, Bean으로 등록시켜서 쓰기도 한다.
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))") // 어노테이션을 통한 타겟팅 가능
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            Object result = joinPoint.proceed();
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }

    // AOP를 이용해 병목을 찾을 수도 있다.
}
