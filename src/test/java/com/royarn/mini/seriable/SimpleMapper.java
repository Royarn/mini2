package com.royarn.mini.seriable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/8/3 13:27
 */
public class SimpleMapper {

    public static String toString(Object object) {

        try {
            Class<?> clazz = object.getClass();
            StringBuilder builder = new StringBuilder();
            builder.append(clazz.getName() + "\n");
            for (Field field : clazz.getDeclaredFields()) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                builder.append(field.getName() + "=" + field.get(object).toString() + "\n");
            }
            return builder.toString();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object fromString(String string) {
        try {
            String[] lines = string.split("\n");
            if (lines.length == 0) {
                throw new IllegalArgumentException(string);
            }
            Class<?> clazz = Class.forName(lines[0]);
            Object obj = clazz.newInstance();
            for (int i=1;i<lines.length;i++) {
                String[] fv = lines[i].split("=");
                if (fv.length != 2) {
                    throw new IllegalArgumentException(lines[i]);
                }
                Field field = clazz.getDeclaredField(fv[0]);
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                setInitValue(field, obj, fv[1]);
            }
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setInitValue(Field field, Object object, String value) throws Exception {

        Class<?> clazz = field.getType();
        if (clazz == int.class) {
            field.setInt(object, Integer.parseInt(value));
        }
        else if (clazz == byte.class) {
            field.setInt(object, Byte.parseByte(value));
        }
        else if (clazz == short.class) {
            field.setShort(object, Short.parseShort(value));
        }
        else if (clazz == float.class) {
            field.setFloat(object, Float.parseFloat(value));
        }
        else if (clazz == double.class) {
            field.setDouble(object, Double.parseDouble(value));
        }
        else if (clazz == boolean.class) {
            field.setBoolean(object, Boolean.parseBoolean(value));
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
