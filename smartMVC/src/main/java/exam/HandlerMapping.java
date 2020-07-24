package exam;

import mvc.RequestMapping;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Data 2020/7/21 0021
 */
public class HandlerMapping {
    //定义一个map用于存放  根据地址执行对应的方法 key：url，value：requestHandler对象，方法
    private Map<String,RequestHandler> map = new HashMap<>();

    //定义初始化方法 传入类名，将注解的value放入key，requestHandler对象，带有注解方法放入value
    public void init(String className) throws Exception{
        //加载类
        Class cls = Class.forName(className);
        //创建类实例
        Object obj = cls.newInstance();
        //获取该类全部方法
        Method[] methods = cls.getDeclaredMethods();
        //遍历
        for (Method method : methods) {
            //getAnnotation（）如果存在指定类型的此元素的批注，则返回此元素的批注，否则返回null。
            RequestMapping annotation = method.getAnnotation(RequestMapping.class);
            if (annotation != null) {
                //获取注解上的值
                String path = annotation.value();
                //将有注解的方法和该对象传入requestHandler
                RequestHandler handler = new RequestHandler(method,obj);
                map.put(path,handler);
            }
        }
    }

    public RequestHandler get(String path) {
        return map.get(path);
    }
}
