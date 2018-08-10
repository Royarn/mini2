package com.royarn.mini.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/8/7 10:43
 */
public class CglibProxy {

    static class RealService {
        public void methodCall() {
            System.out.println("method is invoked");
        }
    }

    static class MethodInteceptor implements MethodInterceptor {

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("method invoke begin " + method.getName());
            Object result = methodProxy.invokeSuper(o, objects);
            System.out.println("method invoke end " + method.getName());
            return null;
        }
    }

    public static <T> T getProxy(Class<T> clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(new MethodInteceptor());
        return (T) enhancer.create();
    }

    public static void main(String[] args) {
        RealService service = getProxy(RealService.class);
        service.methodCall();
    }
}