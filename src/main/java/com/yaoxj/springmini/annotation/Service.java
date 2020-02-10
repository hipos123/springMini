package com.yaoxj.springmini.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2020/1/28.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Service {
    String value() default "";
}
