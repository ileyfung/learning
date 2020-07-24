package mvc;

import java.lang.reflect.Method;

/**
 * Handler处理器
 * 请求处理器，用处理用户请求，封装控制器对象和控制器上的业务方法
 * @Data 2020/7/19 0019
 */
public class RequestHandler {
    private Object controller; //可以是任何对象
    private Method method; //可以是任何方法

    public RequestHandler() {
    }

    public RequestHandler(Object controller, Method method) {
        this.controller = controller;
        this.method = method;
    }

    public Object getController() {
        return controller;
    }

    public Method getMethod() {
        return method;
    }

    @Override
    public String toString() {
        return "RequestHandler{" +
                "controller=" + controller +
                ", method=" + method +
                '}';
    }
}
