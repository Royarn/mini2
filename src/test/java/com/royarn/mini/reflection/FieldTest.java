package com.royarn.mini.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/8/3 9:45
 */
public class FieldTest {

    public static void main(String[] args) {
        //获取pojo包括父类在内的所有public属性信息
        Field[] fields = PojoClass.class.getFields();
        //获取pojo本类中所有属性信息，包括非public
        Field[] fields1 = PojoClass.class.getDeclaredFields();
        PojoClass pojoClass = new PojoClass("data1", "data2", new Date(), 12);
        for (Field field : fields1) {
            //关闭java访问检查机制
            field.setAccessible(true);
            try {
                if (field.get(pojoClass) instanceof String) {
                    System.out.println(field.get(pojoClass));
                } else if (field.get(pojoClass) instanceof  Date) {
                    System.out.println(((Date) field.get(pojoClass)).getMonth());
                } else {
                    System.out.println(field.get(pojoClass));
                }
                System.out.println("Field Modifiers : " + field.getModifiers() + "and Type : " + field.getType());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
