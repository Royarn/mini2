package com.royarn.mini.annotation;

import java.lang.reflect.Field;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/8/6 16:18
 */
public class LabelTest {

    static class Obj {

        @Label
        String name;

        public Obj(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {

        try {
            Class<?> clazz = Obj.class;
            Field field = clazz.getDeclaredField("name");
            Label label = field.getAnnotation(Label.class);
            String name = label.value() == null ? field.getName() : label.value();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}