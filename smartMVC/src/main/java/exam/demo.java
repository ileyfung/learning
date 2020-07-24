package exam;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @Data 2020/7/21 0021
 */
public class demo {
    public static void main(String[] args) throws Exception{
        //动态加载类
        HandlerMapping handlerMapping  = new HandlerMapping();
        handlerMapping.init("mvc.BizController");

        String path = "/controller/add.do";
        //通过路径拿到对应的对象
        RequestHandler handler = handlerMapping.get(path);
        //通过该对象拿到控制器对象和有注解的方法
        Object controller = handler.getController();
        Method method = handler.getMethod();
        //执行方法
        HttpServletRequest request = null;
        Object invoke = method.invoke(controller, request);
        System.out.println(invoke);
    }
}
