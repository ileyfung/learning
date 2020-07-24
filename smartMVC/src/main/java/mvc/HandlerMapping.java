package mvc;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * map用于映射请求path到对应的请求处理器
 * 如：将/emp/list.do 映射到 bizColtroller.list()方法
 * @Data 2020/7/19 0019
 */
public class HandlerMapping {
    private Map<String,RequestHandler> map = new HashMap<>();

    /**
     * 初始化方法，解析控制器中的注解，将注解和注解标注的方法添加到map中
     * @param className 控制器类名
     */
    public void init(String className) throws Exception {
        //加载类
        Class cls = Class.forName(className);
        //得到全部方法
        Method[] methods = cls.getDeclaredMethods();
        Object controller = cls.newInstance();
        for (Method method : methods) {
            RequestMapping rm = method.getAnnotation(RequestMapping.class);
            if (rm != null) {
                //找到注解上标注的路径
                String path = rm.value();
                //创建请求处理器对象，封装控制器对象和方法
                RequestHandler handler = new RequestHandler(controller,method);
                //请求路径和对应的“请求处理器”添加到map
                map.put(path,handler);
                System.out.println(path+": "+handler);
            }
        }
    }

    public RequestHandler get(String path) {
        return map.get(path);
    }

    /**
     * 用于根据path执行对应控制器方法
     * @param path 请求路径
     * @param request 执行控制器时候的的参数
     * @return 控制器执行以后的返回值
     */
    public String execute(String path, HttpServletRequest request) throws Exception{
        RequestHandler handler = get(path);
        Object controller = handler.getController();
        Method method = handler.getMethod();
        Object val = method.invoke(controller, request);
        String value = (String)val;
        return value;

    }
}
