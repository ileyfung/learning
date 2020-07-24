package day13;
import mvc.BizController;
import mvc.RequestMapping;

import java.lang.reflect.Method;

public class Demo01 {
    public static void main(String[] args) throws Exception {
        /*
         * 解析 BizController类的@RequestMapping注解
         */
        //加载类
        Class cls = Class.forName("mvc.BizController");
        //获取类的全部方法
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            //利用RequestMapping.class类型找到的对象是RequestMapping类型的对象，包含value方法
            RequestMapping rm = method.getAnnotation(RequestMapping.class);
            if (rm != null) {
                String value = rm.value();
                System.out.println(value);
                System.out.println(method);
            }
        }

    }

}




