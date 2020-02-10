package com.yaoxj.springmini.demo.service.impl;

import com.yaoxj.springmini.annotation.Service;
import com.yaoxj.springmini.demo.service.IDemoService;

/**
 * Created by Administrator on 2020/1/28.
 */
@Service("demoService")
public class DemoServiceImpl  implements IDemoService{
    @Override
    public String getTestByName(String name) {
        return "my name is:"+name;
    }
}
