package com.yaoxj.springmini.servlet;

import com.yaoxj.springmini.annotation.AutoWried;
import com.yaoxj.springmini.annotation.Controller;
import com.yaoxj.springmini.annotation.Service;
import com.yaoxj.springmini.demo.action.DemoAction;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2020/1/28.
 */
public class DispatcherServlet extends HttpServlet {
    //查看这个值，按住alt+鼠标
    private Properties properties=new Properties();
    private Map<String,Object> beanIocMap=new ConcurrentHashMap<String,Object>();
    List<String> calssNames=new ArrayList<>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //定位资源文件，配置文件
        doLocationCondfig(config.getInitParameter("servletContentLocation"));

        //加载资源，配置文件。在spring中通过reader将配置资源文件转化成beandefinition
        //加载文件到一个list中，就是需要将扫描的类加载到list中
        doLoad(properties.getProperty("scanPackage"));

        //注册bean到ioc容器中
        doRegisterIoc();

        //依赖注入
        //spring是调用getBean方法才触发依赖注入的
        doAutoWired();

        DemoAction action= (DemoAction) beanIocMap.get("demoAction");
        action.demoTest(null,null,"tim");


        //如果是springmvc 就会多设计一个handlerMapping，将@RequestMapping和method关联上
        //以便于从浏览器中获取到url之后，能够找到具体执行的method，通过反射方式调用
        initHandlerMapping();

    }

    private void initHandlerMapping() {
    }

    private void doRegisterIoc() {
        if(calssNames.isEmpty()){
            return ;
        }else{
            for (String clazzName:calssNames){
                try {
                    Class<?> clazz=Class.forName(clazzName);
                    if(clazz.isAnnotationPresent(Controller.class)){
                        //在spring里面不会直接放这个instance进去，而是放Beandefintion进去
                        beanIocMap.put(lowerFirstCase(clazz.getSimpleName()),clazz.newInstance());
                    }else if(clazz.isAnnotationPresent(Service.class)){
                        Service service=clazz.getAnnotation(Service.class);
                        //有自定service的value的情况下，优先使用它做为beanname
                        String beanName=service.value();
                        if ("".equals(beanName)){
                            beanName=lowerFirstCase(clazz.getSimpleName());
                        }
                        Object instance=clazz.newInstance();
                        beanIocMap.put(beanName,instance);

                        //如果是接口的情况下
                        Class<?>[] interfaces=clazz.getInterfaces();
                        for (Class i:interfaces) {
                            beanIocMap.put(i.getSimpleName(),instance);
                        }

                    }else{
                        continue;
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doAutoWired() {
        if(beanIocMap.isEmpty()){return;}
        for (Map.Entry<String,Object> entry:beanIocMap.entrySet()) {
            Field[] fileds=entry.getValue().getClass().getDeclaredFields();
            for (Field filed:fileds){
                if(!filed.isAnnotationPresent(AutoWried.class)){
                    continue;
                }

                AutoWried autoWried=filed.getAnnotation(AutoWried.class);
                String beanName=autoWried.value().trim();
                if("".equals(beanName)){
//                    beanName=filed.getType().getName();
                    beanName=filed.getName();
                }
                filed.setAccessible(true);//开启强制访问
                try {
                    filed.set(entry.getValue(),beanIocMap.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            
        }
    }

    private void doLoad(String scanPackage) {
        URL url=this.getClass().getClassLoader().getResource("/"+scanPackage.replaceAll("\\.","/"));
        File classDir=new File(url.getFile());
        for (File file:classDir.listFiles()){
            if(file.isDirectory()){
                doLoad(scanPackage+"."+file.getName());
            }else{
                calssNames.add((scanPackage+"."+file.getName()).replace(".class",""));
            }
        }
    }

    private void doLocationCondfig(String location) {
        //在是spring是通过reader进度定位和读取
        InputStream ips=this.getClass().getClassLoader().getResourceAsStream(location.replace("classpath:",""));
        try {
            properties.load(ips);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null!=ips){
                try {
                    ips.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String lowerFirstCase(String str){
        char[] chars=str.toCharArray();
        chars[0]+=32;
        return String.valueOf(chars);

    }


}
