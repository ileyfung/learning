package day13;

import mvc.HandlerMapping;

/**
 * @Data 2020/7/21 0021
 */
public class Demo02 {
    public static void main(String[] args) throws Exception{
        /**
         * 测试HandlerMapping的初始化方法是否可用
         */
        HandlerMapping mapping = new HandlerMapping();
        mapping.init("mvc.BizController");

        //执行/emp/add.do对应的方法
        //String path = "/emp/add.do";
        /*RequestHandler handler = mapping.get(path);
        Method method = handler.getMethod();
        Object controller = handler.getController();
        HttpServletRequest request = null;
        Object val = method.invoke(controller,request);
        System.out.println(val);*/
        String val = mapping.execute("/controller/add.do",null);
        System.out.println(val);
    }
}
