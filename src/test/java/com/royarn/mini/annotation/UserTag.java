package com.royarn.mini.annotation;

import java.lang.annotation.*;

/**
 * Description:
 *
 * @author lizhiqiang
 * @date 2019-06-05
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface UserTag {

    String methodName() default "no method";

    String description() default "";
}
