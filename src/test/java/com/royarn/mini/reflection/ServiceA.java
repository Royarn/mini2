package com.royarn.mini.reflection;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/8/6 15:02
 */
public class ServiceA {

    @SimpleInject
    ServiceB serviceB;
    public void callB() {
        serviceB.action();
    }
}
