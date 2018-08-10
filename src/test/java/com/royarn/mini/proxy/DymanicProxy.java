package com.royarn.mini.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/8/6 17:41
 */
public class DymanicProxy {

    static interface IService {
        void methodCall();
    }

    static class RealService implements IService {

        @Override
        public void methodCall() {
            System.out.println("real service call");
        }
    }

    static class SimpleInvocationHandler implements InvocationHandler {

        private Object object;

        public SimpleInvocationHandler(Object object) {
            this.object = object;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("entering " + method.getName());
            Object result = method.invoke(object, args);
            System.out.println("exit " + method.getName());
            return null;
        }
    }

    public static void main(String[] args) {
        IService service = new RealService();
        IService procySvc = (IService) Proxy.newProxyInstance(IService.class.getClassLoader(), new Class<?>[] {IService.class},
                    new SimpleInvocationHandler(service));
        procySvc.methodCall();
    }
}
