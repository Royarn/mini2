package com.royarn.mini.seriable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/8/3 14:08
 */
public class Serializable {

    public static String serializable(Object object) {

        try {
            Class<?> clazz = object.getClass();
            StringBuilder builder = new StringBuilder();
            builder.append(clazz.getName() + "\n");
            //封装数据
            for (Field field : clazz.getDeclaredFields()) {
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

    public static Object desrializable(String string) {

        try {
            String[] lines = string.split("\n");
            if (lines.length == 0) {
                throw new IllegalArgumentException(string);
            }
            //构造空实例
            Class<?> clazz = Class.forName(lines[0]);
            Object object = clazz.newInstance();
            if (lines.length > 1) {
                for (int i=1;i<lines.length;i++) {
                    String[] kvData = lines[i].split("=");
                    if (kvData.length != 2) {
                        throw new IllegalArgumentException(lines[i]);
                    }
                    Field field = clazz.getDeclaredField(kvData[0]);
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    setValue(field, object, kvData[1]);
                }
            }
            return object;
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

    public static void setValue(Field field, Object object, String value) throws Exception {

        Class<?> clazz = field.getType();
        if (clazz == int.class) {
            field.set(object, Integer.parseInt(value));
        }
        else if (clazz == byte.class) {
            field.set(object, Byte.parseByte(value));
        }
        else if (clazz == short.class) {
            field.set(object, Short.parseShort(value));
        }
        else if (clazz == float.class) {
            field.set(object, Float.parseFloat(value));
        }
        else if (clazz == double.class) {
            field.set(object, Double.parseDouble(value));
        }
        else if (clazz == String.class) {
            field.set(object, value);
        }
        else {
            Constructor<?> constructor = clazz.getConstructor(new Class[] {String.class});
            field.set(object, constructor.newInstance(value));
        }
    }
}
