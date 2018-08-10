package com.royarn.mini.reflection;

import java.lang.reflect.Field;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/8/6 15:03
 */
public class SimpleContainer {

    public <T> T getInstance(Class<T> clazz) {

        try {
            //模拟spring创建Bean --构建obj实例
            T obj = clazz.newInstance();
            //获取field集合
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                //关闭java访问检查机制
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                Class<?> fieldClazz = field.getType();
                field.set(field, getInstance(fieldClazz));
            }
            return obj;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
