package com.royarn.mini.proxy;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/8/6 17:23
 */
public class StaticProxy {

    static interface IService {
        void methodCall();
    }

    static class RealService implements IService {
        @Override
        public void methodCall() {
            System.out.println("real service call ");
        }
    }

    static class TraceProxy implements IService {

        IService service;

        public void setService(IService service) {
            this.service = service;
        }

        @Override
        public void methodCall() {
            System.out.println("real service call start ");
            service.methodCall();
            System.out.println("rell service call end ");
        }
    }

    public static void main(String[] args) {
        IService service = new RealService();
        TraceProxy traceProxy = new TraceProxy();
        traceProxy.setService(service);
        traceProxy.methodCall();
    }
}
