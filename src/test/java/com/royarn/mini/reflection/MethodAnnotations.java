package com.royarn.mini.reflection;

import java.lang.annotation.*;
import java.lang.reflect.Method;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/8/6 10:43
 */
public class MethodAnnotations {

    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.RUNTIME)
    static @interface QueryParam {
        String value();
    }

    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.RUNTIME)
    static @interface DefaultValue {
        String value();
    }

    public void hello(@QueryParam("action") String action,
                      @QueryParam("sort") @DefaultValue("asc") String sort) {

    }

    public static void main(String[] args) throws Exception {
        Class<?> clazz = MethodAnnotations.class;
        Method method = clazz.getMethod("hello", new Class[] {String.class, String.class});
        Annotation[][] annotations = method.getParameterAnnotations();
        for (int i=0;i<annotations.length;i++) {
            System.out.println("Annotations for Parameter " + (i+1));
            Annotation[] annotationArr = annotations[i];
            for (int j = 0;j<annotationArr.length;j++) {
               for (Annotation annotation : annotationArr) {
                   if (annotation instanceof  QueryParam) {
                       QueryParam queryParam = (QueryParam) annotation;
                       System.out.println(queryParam.annotationType().getSimpleName() + " : " + queryParam.value());
                   } else if (annotation instanceof DefaultValue) {
                       DefaultValue defaultValue = (DefaultValue) annotation;
                       System.out.println(defaultValue.annotationType().getSimpleName() + " : " + defaultValue.value());
                   }
               }
            }
        }
    }
}
