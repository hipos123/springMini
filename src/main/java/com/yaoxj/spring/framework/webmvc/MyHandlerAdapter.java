package com.yaoxj.spring.framework.webmvc;

import java.util.Map;

/**
 * Created by Administrator on 2020/2/29.
 */
public class MyHandlerAdapter {
    //里面放的是参数名称和位置
    private Map<String,Integer> handlerAdapter;

    public MyHandlerAdapter(Map<String, Integer> handlerAdapter) {
        this.handlerAdapter = handlerAdapter;
    }
}
