package com.yaoxj.springmini.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2020/1/28.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoWried {
    String value() default "";
}
