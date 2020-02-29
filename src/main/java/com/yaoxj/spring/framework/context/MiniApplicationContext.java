package com.yaoxj.spring.framework.context;


import com.yaoxj.spring.framework.context.support.MyBeanDefinitionReader;

import java.util.Properties;

public class MiniApplicationContext {
    private String [] configLocations;
    private MyBeanDefinitionReader reader;
    public  MiniApplicationContext(String ... configLocations){
        configLocations=configLocations;
        refresh();
    }

    private void refresh() {
        //定位，加载，注册，依赖注入
    }

    public Properties getConfig(){
        return  this.reader.getConfig();
    }


}
