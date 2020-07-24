package exam;

import java.lang.reflect.Method;

/**
 * @Data 2020/7/21 0021
 */
public class RequestHandler {
    private Method method;
    private Object controller;

    public RequestHandler() {
    }

    @Override
    public String toString() {
        return "RequestHandler{" +
                "method=" + method +
                ", controller=" + controller +
                '}';
    }

    public Method getMethod() {
        return method;
    }

    public Object getController() {
        return controller;
    }

    public RequestHandler(Method method, Object controller) {
        this.method = method;
        this.controller = controller;
    }
}
