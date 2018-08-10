package com.royarn.mini.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/8/3 11:07
 */
public class ConstructorTest {

    public static void main(String[] args) {
        //获取所有包括父类的public构造方法
        Constructor[] constructors = PojoClass.class.getConstructors();
        //获取本类的所有的构造方法，包括非public
        Constructor[] constructors1 = PojoClass.class.getDeclaredConstructors();
        for (Constructor constructor : constructors1) {
            constructor.setAccessible(true);
            try {
                PojoClass pojoClass = (PojoClass) constructor.newInstance(new Object[]{"blink", "shadow"});
                System.out.println(pojoClass.getVar1());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}