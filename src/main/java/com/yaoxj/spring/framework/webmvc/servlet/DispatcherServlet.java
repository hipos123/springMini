package com.yaoxj.spring.framework.webmvc.servlet;



import com.yaoxj.spring.framework.context.MiniApplicationContext;
import com.yaoxj.spring.framework.webmvc.MyHandlerAdapter;
import com.yaoxj.spring.framework.webmvc.MyHandlerMapping;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DispatcherServlet extends HttpServlet {

    private List<MyHandlerMapping> myHandlerMappingList=new ArrayList<MyHandlerMapping>();

    Map<MyHandlerMapping,MyHandlerAdapter> myHandlerAdapterMap=new HashMap<MyHandlerMapping,MyHandlerAdapter>();
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
        MiniApplicationContext applicationContext=new MiniApplicationContext(config.getInitParameter("servletContentLocation"));
        initStrategies(applicationContext);
        super.init(config);
    }

    //springmvc 中的九大组件
    private void initStrategies(MiniApplicationContext context) {
        //springmvc的九种策略
        //针对每个用户请求，都会经过一系列处理的策略之后最终才能有结果，每一种策略都可以自定义干预
        //但是最终结果都是一致的
//        initMultipartResolver(context);

//        initLocaleResolver(context);
//        initThemeResolver(context);

//        请求映射到处理器
         initHandlerMappings(context);
//        initHandlerMappings(context);
        //动态匹配method参数，包括类转换，动态赋值
         initHandlerAdapters(context);
//        initHandlerAdapters(context);
//        initHandlerExceptionResolvers(context);
//        initRequestToViewNameTranslator(context);
        //ViewResolvers动态模板的解析，逻辑视图到物理视图的具体事项
//        initViewResolvers(context);
//        initFlashMapManager(context);



    }

    private void initHandlerAdapters(MiniApplicationContext context) {
        //循环myHandlerMappingList，找到里面的方法部分，将方法中的的参数名称，参数类型按 顺序保存下来，放到map中。

    }

    private void initHandlerMappings(MiniApplicationContext context) {
        //从ioc容器里面获取到所有的bean信息，进行循环，注解是control的，并且有requestMapping注解的，
        //拿到这个url，类名，和方法名放到一个map里面，再将一个map放到一个list里面，就组装好了一个url和方法的映射关系



    }
}
