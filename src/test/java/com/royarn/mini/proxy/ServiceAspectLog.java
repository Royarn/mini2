package com.royarn.mini.proxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/8/7 11:37
 */

@Aspect({})
public class ServiceAspectLog {

    public static void before(Object object, Method method, Object[] args) {
        System.out.println("invoke begin : " + method.getDeclaringClass().getSimpleName() + " : " + method.getName() +
                " args :" + Arrays.toString(args));
    }

    public static void after(Object object, Method method, Object[] args,
                             Object result) {
        System.out.println("invoke end : " + method.getDeclaringClass().getSimpleName() + " : " + method.getName() +
                " args :" + Arrays.toString(args) + " result : " + result);
    }
}
