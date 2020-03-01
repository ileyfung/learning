package com.webserver.servlet;

import com.webserver.http.HTTPResponse;
import com.webserver.http.HttpRequest;

import java.io.File;

/**
 * 所有Servlet的超类
 * @Data 2020/2/25 0025
 */
public abstract class HttpServlet {
    /**
     * 用于处理请求的方法。ClientHandler在调用某请求对应的处理类（某Servlet）
     * 时，会调用service方法。
     * @param request
     * @param response
     */
    public abstract void service(HttpRequest request, HTTPResponse response);

    /**
     * 跳转到指定页面
     * @param path 从webapps之后开始指定路径，如："/myweb/xxx.html“
     * @param request
     * @param response
     */
    public void forward(String path, HttpRequest request, HTTPResponse response) {
        File file = new File("./WebServer_v19/webapps" + path);
        response.setEntity(file);
    }
}
