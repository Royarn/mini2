package com.royarn.mini.annotation;

import java.lang.annotation.*;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-29
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ThreadSafe {

    String value() default "";
}
