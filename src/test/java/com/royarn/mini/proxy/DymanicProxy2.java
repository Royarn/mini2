package com.royarn.mini.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/8/7 10:05
 */

/**
 * 静态代理
 *  耦合真实服务，不灵活
 *  动态代理
 *      java SDK 实现
 *          不能耦合真实服务类型
 *          只能耦合Object
 *          相比静态代理，他只需要耦合Object
 *      局限：
 *          1.只能代理接口定义的方法，返回的代理对象也是接口类型，不能转换为具体类型
 *          2.若类没有实现任何接口，那就无法实现代理
 *      cglib实现
 *          可代理任何实际类型
 */
public class DymanicProxy2 {

    static interface Service {
        void methodCall();
    }

    static class TargetService implements Service {

        @Override
        public void methodCall() {
            System.out.println("target service call");
        }
    }

    static class Handler implements InvocationHandler {

        Object object;

        public Handler(Object object) {
            this.object = object;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("service call begin");
            method.invoke(object, args);
            System.out.println("serivce call end");
            return null;
        }
    }

    public static void main(String[] args) {
        Service service = new TargetService();
        Service service1 = (Service) Proxy.newProxyInstance(Service.class.getClassLoader(), new Class[] {Service.class}, new Handler(service));
        service1.methodCall();
    }
}
