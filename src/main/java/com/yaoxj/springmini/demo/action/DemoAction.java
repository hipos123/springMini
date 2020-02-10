package com.yaoxj.springmini.demo.action;

import com.yaoxj.springmini.annotation.AutoWried;
import com.yaoxj.springmini.annotation.Controller;
import com.yaoxj.springmini.annotation.RequestMapping;
import com.yaoxj.springmini.annotation.RequestParam;
import com.yaoxj.springmini.demo.service.IDemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2020/1/28.
 */
@Controller
@RequestMapping("/demo")
public class DemoAction {

    @AutoWried
    IDemoService demoService;

    @RequestMapping("/test")
    public void demoTest(HttpServletRequest request,HttpServletResponse response,
                         @RequestParam("name") String name){

        String result=demoService.getTestByName(name);
        System.out.println(result);
//        try {
//            response.getWriter().print(result);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
