package mvc;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.InputStream;
import java.util.List;

/**
 * 初始化当前URL到控制器的映射表
 */
@WebListener()
public class ContextConfigListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    // Public constructor is required by servlet spec
    public ContextConfigListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
        try {
            ServletContext ctx = sce.getServletContext();
            /*HandlerMapping mapping = new HandlerMapping();
            mapping.init("mvc.BizController");*/
            //获取根路径
            String path = ctx.getContextPath();
            ctx.setAttribute("root", path);

            HandlerMapping mapping = loadController("beans.xml");
            ctx.setAttribute("handlerMapping", mapping);
            System.out.println("初始化了HandlerMapping");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 读取beans.xml配置文件
     * 解析配置文件中定义的控制器类，将控制器类的url映射到对应的控制器方法
     *
     * @param xml
     * @return 包含url到控制器方法映射关系的HandlerMapping对象
     */
    public HandlerMapping loadController(String xml) throws Exception {
        SAXReader reader = new SAXReader();
        //读取资源文件夹 的配置文件
        InputStream in = ContextConfigListener.class.getClassLoader().getResourceAsStream(xml); //getResourceAsStream:返回用于读取指定资源的输入流。
        System.out.println("读取配置文件：" + in);
        Document document = reader.read(in); //使用SAX从给定的InputSource读取文档,返回新创建的文档实例
        Element root = document.getRootElement(); //beans返回根元素
        List<Element> list = root.elements(); //获取根元素下所有的 元素
        HandlerMapping mapping = new HandlerMapping();
        for (Element element : list) {
            //在bean元素上获取class属性的值作为类名
            //attributeValue:这将返回具有给定完全限定名称的属性的属性值；如果没有此类属性，则返回null；如果属性值为空，则返回空字符串。
            String className = element.attributeValue("class");
            System.out.println(className);
            mapping.init(className);
        }
        return mapping;
    }


    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attribute
         is replaced in a session.
      */
    }
}
