package com.webserver.servlet;

import com.webserver.http.HTTPResponse;
import com.webserver.http.HttpRequest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 展示用户列表
 *
 * @Data 2020/2/26 0026
 */
public class ShowAllUserServlet extends HttpServlet {
    public void service(HttpRequest request, HTTPResponse response) {
        /*
        1、读取user.dat文件，将所有用户读取出来。其中每个用户用一个Map保存，
        key保存属性名，value保存该属性的值。再将这些Map实例存入一个list集合备用

        2、利用Thymeleaf将list集合的数据绑定到showAllUser.html页面上
        页面上要对应的添加Thymeleaf需要的属性，否则Thymeleaf不知道如何绑定
         */
        try (RandomAccessFile raf = new RandomAccessFile("./WebServer_v19/src/user.dat", "r")) {
            List<Map<String,String>> userList = new ArrayList<>();
            for (int i = 0; i < raf.length() / 64; i++) {
                byte[] data = new byte[32];
                raf.read(data);
                String username = new String(data, "utf-8").trim();
                raf.read(data);
                String password = new String(data, "utf-8").trim();
                //将每一个用户信息放入一个Map里面
                Map<String, String> user = new HashMap<>();
                user.put("username", username);
                user.put("password", password);
                //将一个 存放每一个一个用户的Map 放入List中
                userList.add(user);
            }
            //thymeleaf模板引擎，用来将数据绑定到静态页面的核心组件
            TemplateEngine eng = new TemplateEngine();

            FileTemplateResolver resolver = new FileTemplateResolver();
            //设置字符集，告知引擎静态页面的字符集
            resolver.setCharacterEncoding("utf-8");
            //将解析器设置到引擎上，使得引擎知道如何处理静态页面
            eng.setTemplateResolver(resolver);

            //Context用来存储所有在页面上显示的数据
            Context context = new Context();
            //将保存所有用户的list集合存入Context
            context.setVariable("list",userList);
            /*
            调用引擎的process处理方法，该方法就是将指定的数据与指定的页面
            进行绑定。参数一：静态页面路径  参数二；需要在静态页面上动态显示的数据
            该方法返回值为一个字符串，内容就是绑定了动态数据的静态页面所对应的HTML代码
             */
            String line = eng.process("./WebServer_v19/webapps/myweb/showAllUser.html", context);

            //将生成的页面设置到response中响应给客户端
            byte[] data = line.getBytes("utf-8");
            response.setContentData(data);
            response.putHeader("Content-Type","text/html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
