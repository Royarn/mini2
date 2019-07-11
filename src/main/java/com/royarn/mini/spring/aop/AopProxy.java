package com.royarn.mini.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;
import java.lang.reflect.Method;

/**
 * Description:
 *
 * @author lizhiqiang
 * @date 2019-07-10
 */
@Configuration
@ComponentScan("com.royarn.mini.spring.aop")
@EnableAspectJAutoProxy
public class AopProxy {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopProxy.class);
        ServiceImpl service = (ServiceImpl) context.getBean("serviceImpl");
        service.callMethod();
    }
}

//define annotation
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface Action {
    String method();
}
//define a pointcut
@Service
class ServiceImpl {
    @Action(method = "callMethod")
    public void callMethod() {
        System.out.println("SerivceImpl method is called");
    }
}
//define a pointcut
@Aspect
@Component
class AspectContext {
    @Pointcut("@annotation(com.royarn.mini.spring.aop.Action)")
    public void serviceAspect() {}

    @After("serviceAspect()")
    public void afterCall(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Action action = method.getAnnotation(Action.class);
        System.out.println(action.method());
    }
}

