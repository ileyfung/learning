package com.webserver.servlet;

import com.webserver.http.HTTPResponse;
import com.webserver.http.HttpRequest;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * 修改密码
 *
 * @Data 2020/2/24 0024
 */
public class ChangeServlet extends HttpServlet {

    public void service(HttpRequest request, HTTPResponse response) {
        //获取登录信息
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String new_password = request.getParameter("new_password"); //新密码
        String again_password = request.getParameter("again_password"); //第二次输入的密码

        System.out.println("用户名：" + username);
        System.out.println("密码：" + password);
        System.out.println("新密码：" + new_password);
        System.out.println("第二次输入的密码：" + again_password);

        try (RandomAccessFile raf = new RandomAccessFile("./WebServer_v19/src/user.dat", "rw")) {
            for (int i = 0; i < raf.length() / 64; i++) {
                raf.seek(i * 64);
                byte[] data = new byte[32];
                raf.read(data);
                String name = new String(data, "utf-8").trim(); //读取用户名
                if (name.equals(username)) { //判断用户名
                    raf.read(data);
                    String pass = new String(data, "utf-8").trim(); //读取密码
                    if (pass.equals(password)) { //判断密码
                        //修改密码操作
                        if (new_password.equals(again_password)) { //判断两次输入的密码是否一致
                            raf.seek(i * 64 + 32); //将指针移动到当前用户名对应的密码位置
                            data = again_password.getBytes("utf-8");
                            data = Arrays.copyOf(data, 32);
                            raf.write(data);  //将新密码写入
                            //修改成功返回页面 并结束方法
                            forward("/myweb/change_success.html", request, response);
                            return;
                        } else {
                            //两次密码不一致 返回提示页面 并结束方法
                            forward("/myweb/new_again.html", request, response);
                            return;
                        }
                    }
                    /*
                    只要用户名对了，无论是密码是否匹配，都应当停止后续 读取工作。
                    因为user.dat文件中不存在重复的用户名，减少没有必要的读取操作
                    提高性能。
                     */
                    break;
                }
            } //for循环结束
            forward("/myweb/login_fail.html", request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
