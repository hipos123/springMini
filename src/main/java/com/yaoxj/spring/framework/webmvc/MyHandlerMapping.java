package com.yaoxj.spring.framework.webmvc;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @program: springMini
 * @description:
 * @author: yaoxj
 * @create: 2020-02-29 15:38
 **/
public class MyHandlerMapping {
    private Object control;
    private Method method;
    private Pattern pattern;

    public MyHandlerMapping(Object control, Method method, Pattern pattern) {
        this.control = control;
        this.method = method;
        this.pattern = pattern;
    }

    public Object getControl() {
        return control;
    }

    public void setControl(Object control) {
        this.control = control;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }
}
