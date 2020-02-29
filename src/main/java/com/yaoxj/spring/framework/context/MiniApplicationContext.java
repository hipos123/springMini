package com.yaoxj.spring.framework.context;


public class MiniApplicationContext {
    private String [] configLocations;
    public  MiniApplicationContext(String ... configLocations){
        configLocations=configLocations;
        refresh();
    }

    private void refresh() {
        //定位，加载，注册，依赖注入
    }
}
