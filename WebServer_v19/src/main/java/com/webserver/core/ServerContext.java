package com.webserver.core;

import com.webserver.servlet.*;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务端相关配置信息
 * @Data 2020/2/27 0027
 */
public class ServerContext {
    /**
     * 请求与对应Servlet的关系
     * key：请求路径
     * value：对应的Servlet实例
     */
    private static Map<String, HttpServlet> servletMapping = new HashMap<>();
    static {
        initServletMapping();
    }

    private static void initServletMapping() {
       /* servletMapping.put("/myweb/reg",new RegServlet());
        servletMapping.put("/myweb/login",new LoginServlet());
        servletMapping.put("/myweb/change_pw", new ChangeServlet());
        servletMapping.put("/myweb/showAllUser",new ShowAllUserServlet());*/

       /*
       解析conf/servlets.xml文件，将根标签下所有名为<servlet>的子标签得到，
       然后将每个servlet字标签的属性path作为key将属性className的值得到后利用
       反射加载这个类对象并进行实例化，将实例化的对象作为value保存到servletMapping
       这个Map中完成初始化
        */
       try {
           SAXReader reader = new SAXReader();
           Document read = reader.read(new File("./WebServer_v19/conf/servlets.xml"));
           Element rootElement = read.getRootElement();
           List<Element> servlets = rootElement.elements("servlet");
           for (Element e :
                   servlets) {
               String path = e.attributeValue("path");
               String className = e.attributeValue("className");
               Class cls = Class.forName(className);
               HttpServlet servlet= (HttpServlet)cls.newInstance();
               servletMapping.put(path,servlet);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    /**
     * 根据请求获取对应的Servlet
     * @param path
     * @return
     */
    public static HttpServlet getServlet(String path) {
        return servletMapping.get(path);
    }
}
