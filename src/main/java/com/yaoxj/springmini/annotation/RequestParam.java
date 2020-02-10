package com.yaoxj.springmini.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2020/1/28.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParam {
    String value() default "";
}
