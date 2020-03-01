package com.webserver.servlet;

import com.webserver.http.HTTPResponse;
import com.webserver.http.HttpRequest;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * 处理用户注册业务
 *
 * @Data 2020/2/23 0023
 */
public class RegServlet extends HttpServlet {

    public void service(HttpRequest request, HTTPResponse response) {
        System.out.println("RegServlet: 开始处理注册。。。");
        //1、通过request获取用户的注册信息
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username: " + username);
        System.out.println("password: " + password);
        //2、将注册信息写入文件user.dat
        try (RandomAccessFile raf = new RandomAccessFile("./WebServer_v19/src/user.dat", "rw")) {
            /*
            首先判断user.dat文件中首付已经存在该用户，若存在则响应用户注册提示页面，
            提示该用户已存在，否则将该用户信息写入user.dat文件
            注册提示页面：reg_have_user.html
            提示显示一行文字：该用户已存在，请重新填写用户名！
             */
            for (int i = 0; i < raf.length() / 64; i++) {
                raf.seek(i * 64);
                byte[] data = new byte[32];
                raf.read(data);
                String str = new String(data, "utf-8").trim();
                if (username.equals(str)) {
                    //用户已存在
                    forward("/myweb/reg_have_user.html",request,response);
                    return;
                }
            }
            raf.seek(raf.length()); //指针指向文件末尾
            //写用户名
            byte[] data = username.getBytes("utf-8");
            System.out.println(data.length);
            data = Arrays.copyOf(data, 32);
            raf.write(data);
            //写密码
            data = password.getBytes("utf-8");
            data = Arrays.copyOf(data, 32);
            raf.write(data);

            //注册完毕，响应注册成功页面给客户端
        } catch (Exception e) {
            e.printStackTrace();
        }
        //3、设置response响应客户端注册结果
        forward("/myweb/reg_success.html",request,response);
        System.out.println("RegServlet：处理注册完毕！");
    }
}
