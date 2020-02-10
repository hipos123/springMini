package com.yaoxj.springmini.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2020/1/28.
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller {
      String value() default "";
}
