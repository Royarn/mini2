package com.royarn.mini.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.List;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/8/3 14:45
 */
public class GenericDemo {

    static class GenericTest<U extends Comparable<U>, V> {
        U u;
        V v;
        List<String> stringList;

        public U test(List<? extends Number> numbers) {
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        Class<?> clazz = GenericTest.class;
        //类的类型参数
        for (TypeVariable variable : clazz.getTypeParameters()) {
            System.out.println(variable.getName() + " extends " + Arrays.toString(variable.getBounds()));
        }
        //字段：泛型类型
        Field field = clazz.getDeclaredField("u");
        System.out.println(field.getName() + " = " + field.getGenericType());
        //字段：参数化的类型
        Field field1 = clazz.getDeclaredField("stringList");
        Type listType = field1.getGenericType();
        if (listType instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) listType;
            System.out.println();
        }
    }
}