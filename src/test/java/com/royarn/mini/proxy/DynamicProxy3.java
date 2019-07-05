package com.royarn.mini.proxy;

import com.google.gson.Gson;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author lizhiqiang
 * @date 2019-05-29
 */
public class DynamicProxy3 {
}

class DyanmicProxyHandler implements InvocationHandler {
    private Object target;
    public DyanmicProxyHandler (Object target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("#########start to proxy########### Class : " + proxy.getClass()
        + "####### Method : " + method + " ######### args : " + args);
        if (args != null) {
            for (Object argument : args) {
                System.out.println("########argument###########" + args);
            }
        }
        return method.invoke(target, args);
    }
}

class SimpleDynamicProxy<T> {

    private List<T> datas = new ArrayList<>();
    public boolean get(T ele) {
        if (datas.contains(ele))
            return true;
        return false;
    }

    public T add(T ele) {
        if (null != ele)
            datas.add(ele);
        return ele;
    }

    @Override
    public String toString() {
        return new Gson().toJson(datas);
    }

    public static void main(String[] args) {
        SimpleDynamicProxy<String> simple = new SimpleDynamicProxy<>();
        simple.add("blink");
        simple.add("royarn");
        System.out.println(simple.get("blink"));

    }
}