package com.royarn.mini.annotation;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/8/6 15:11
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * 注解在jdk1.5后引入，简化java开发
 *  声明式编程
 *      基于注解应用：
 *          1.序列化定制
 *          2.DI容器 --构建Bean时耦合关系表达
 */
public class Serieisable {

    /**
     * 将对象序列化为字符串
     */
    public static String parseObj2Str(Object object) {

        try {
            StringBuilder builder = new StringBuilder();
            Class<?> clazz = object.getClass();
            builder.append(clazz.getName() + "\n");
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                builder.append(field.getName() + "=" + field.get(object) + "\n");
            }
            return builder.toString();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将字符串反序列化对象
     */
    public static Object parseStr2Obj(String str) {

        try {
            String[] strings = str.split("\n");
            if (strings.length == 0) {
                throw new RuntimeException("Instance have nothing ");
            }
            Class<?> clazz = Class.forName(strings[0]);
            Object obj = clazz.newInstance();
            for (int i=1;i<strings.length;i++) {
                if (strings[i].indexOf("=") == -1) {
                    throw new RuntimeException("IllegalArgument parameter ");
                }
                String[] kvData = strings[i].split("=");
                Field field = clazz.getDeclaredField(kvData[0]);
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                setFieldInfo(field, obj, kvData[1]);
            }
            return obj;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void setFieldInfo(Field field, Object obj, String value) throws Exception {

        Class<?> clazz = field.getType();
        if (clazz == Integer.class) {
            field.set(obj, Integer.parseInt(value));
        }
        else if (clazz == Short.class) {
            field.set(obj, Short.parseShort(value));
        }
        else if (clazz == Byte.class) {
            field.set(obj, Byte.parseByte(value));
        }
        else if (clazz == Float.class) {
            field.set(obj, Float.parseFloat(value));
        }
        else if (clazz == Double.class) {
            field.set(obj, Double.parseDouble(value));
        }
        else if (clazz == String.class) {
            field.set(obj, value);
        }
        else {
            //构建普通引用类型实例对象
            Constructor<?> constructor = clazz.getConstructor(new Class[] {Integer.class, String.class, String.class});
            field.set(obj, constructor.newInstance(obj));
        }
    }
}
