package com.royarn.mini.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/8/7 10:57
 */
public class CglibProxy2 {

    static class StudentInfo {

        private Integer id;
        private String name;
        private String remark;

        public StudentInfo() {this(1, "royarn", "763094810@qq.com");}

        public StudentInfo(Integer id, String name, String remark) {
            this.id = id;
            this.name = name;
            this.remark = remark;
        }

        public String objToStr() {
            return "{id : " + id + " , name : " + name + " , remark : " + remark + " }";
        }
    }

    /**
     * 代理逻辑
     */
    static class MethodInteCeptor implements MethodInterceptor {

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("inteceptor begin " + method.getName());
            Object result = methodProxy.invokeSuper(o, objects);
            System.out.println("inteceptor end " + method.getName());
            return result;
        }
    }

    /**
     * 构建代理实例
     */
    public static <T> T getProxyInstance(Class<T> clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(new MethodInteCeptor());
        return (T) enhancer.create();
    }

    public static void main(String[] args) {
        StudentInfo info = getProxyInstance(StudentInfo.class);
        String msg = info.objToStr();
        System.out.println(msg);
    }
}
