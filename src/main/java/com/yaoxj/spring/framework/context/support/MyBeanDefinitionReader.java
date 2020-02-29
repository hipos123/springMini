package com.yaoxj.spring.framework.context.support;

import java.util.Properties;

/**
 * Created by Administrator on 2020/2/29.
 */

public class MyBeanDefinitionReader {
    Properties config=new Properties();

    public Properties getConfig() {
        return config;
    }

    public void setConfig(Properties config) {
        this.config = config;
    }
}
