package day13;

import mvc.ContextConfigListener;
import mvc.HandlerMapping;

/**
 * @Data 2020/7/22 0022
 */
public class Demo03 {
    public static void main(String[] args) throws Exception {
        /**
         * 测试 读取配置文件，初始化HandlerMapping
         */
        ContextConfigListener l = new ContextConfigListener();
        HandlerMapping mapping = l.loadController("beans.xml");
    }
}
