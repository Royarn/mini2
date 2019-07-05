package com.royarn.mini.annotation;

import java.lang.annotation.*;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/8/6 16:18
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Label {
    String value() default "";
}
