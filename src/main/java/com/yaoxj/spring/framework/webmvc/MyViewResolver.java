package com.yaoxj.spring.framework.webmvc;

import java.io.File;

/**
 * Created by Administrator on 2020/2/29.
 */
public class MyViewResolver {
    private String viewName;
    private File templateFile;

    public MyViewResolver(String viewName, File templateFile) {
        this.viewName = viewName;
        this.templateFile = templateFile;
    }
}
