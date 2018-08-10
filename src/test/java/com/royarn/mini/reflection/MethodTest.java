package com.royarn.mini.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/8/3 10:33
 */
public class MethodTest {

    public static void main(String[] args) {
        //获取pojo包括父类在内的所有public方法
        Method[] methods = PojoClass.class.getMethods();
        //获取pojo本类所有的方法，包括哟非public
        Method[] methods1 = PojoClass.class.getDeclaredMethods();
//        for (Method method : methods) {
//            System.out.println(method.getName());
//        }

        //构造对象
        PojoClass pojoClass = new PojoClass("method1", "method2", new Date(), 12);
        for (Method method : methods1) {
            //关闭Java访问检查机制
            method.setAccessible(true);
            if (method.getName().equals("simpleMethod")) {
                try {
                    method.invoke(pojoClass, null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        //通过Class构造实例对象  在源码中newInstance()会调用getConstructor()获取无参数的构造方法
        try {
            PojoClass pojoClass1 = PojoClass.class.newInstance();
            pojoClass1.setVar1("class instance ");
            System.out.println(pojoClass1.getVar1());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
