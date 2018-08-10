package com.royarn.mini.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/8/7 10:17
 */
public class GeneralProxy {

    static interface OrderService {
        void orderInfo();
    }

    static class OrderServiceImpl implements OrderService {

        @Override
        public void orderInfo() {
            System.out.println("order item center");
        }
    }

    static interface CustomService {
        void accountInfo();
    }

    static class CustomerServiceImpl implements CustomService {

        @Override
        public void accountInfo() {
            System.out.println("account center");
        }
    }

    /**
     * 代理操作 --定义代理行为
     */
    static class Handler implements InvocationHandler {

        Object object;

        public Handler(Object object) {
            this.object = object;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("proxy is executing");
            method.invoke(object, args);
            System.out.println("proxy is finishing");
            return null;
        }
    }


    /**
     * 构建通用代理实例
     * @param args
     */
    public static <T> T getProxyInstace(Class<T> intf, T object) {
        return (T) Proxy.newProxyInstance(intf.getClassLoader(), new Class<?>[] {intf}, new Handler(object));
    }

    public static void main(String[] args) {
        OrderService orderService = new OrderServiceImpl();
        CustomService customService = new CustomerServiceImpl();
        OrderService orderServiceProxy = getProxyInstace(OrderService.class, orderService);
        orderServiceProxy.orderInfo();
        CustomService customServiceProxy = getProxyInstace(CustomService.class, customService);
        customServiceProxy.accountInfo();
//        OrderService orderServiceProxy = (OrderService) Proxy.newProxyInstance(OrderService.class.getClassLoader(), new Class[] {OrderService.class}, new Handler(orderService));
//        orderServiceProxy.orderInfo();
//        CustomService customService = new CustomerServiceImpl();
//        CustomService customServiceProxy = (CustomService) Proxy.newProxyInstance(CustomService.class.getClassLoader(), new Class[] {CustomService.class}, new Handler(customService));
//        customServiceProxy.accountInfo();

    }
}
