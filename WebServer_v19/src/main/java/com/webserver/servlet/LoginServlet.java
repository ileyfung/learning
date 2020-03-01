package com.webserver.servlet;

import com.webserver.http.HTTPResponse;
import com.webserver.http.HttpRequest;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * @Data 2020/2/24 0024
 */
public class LoginServlet extends HttpServlet{
    public void service(HttpRequest request, HTTPResponse response) {
        System.out.println("开始处理登录。。。");
        //获取用户登录信息
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //验证登录
        try (RandomAccessFile raf = new RandomAccessFile("./WebServer_v18/src/user.dat", "rw")){
            //通过遍历user.dat判断用户是否存在并且密码是否正确
            for (int i = 0; i < raf.length() / 64; i++) {
                raf.seek(i * 64);
                byte[] data = new byte[32];
                raf.read(data);
                String user = new String(data,"utf-8").trim();
                if (user.equals(username)) {
                    raf.read(data);
                    String pass = new String(data, "utf-8").trim();
                    if (pass.equals(password)) {
                        //登录成功
                        forward("/myweb/login_success.html",request,response);
                        return;
                    }
                    /*
                    只要用户名对了，无论是密码是否匹配，都应当停止后续 读取工作。
                    因为user.dat文件中不存在重复的用户名，减少没有必要的读取操作
                    提高性能。
                     */
                    break;
                }
             } //for循环结束
            //登录失败
            forward("/myweb/login_fail.html",request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("处理登录结束。。。");
    }
}
