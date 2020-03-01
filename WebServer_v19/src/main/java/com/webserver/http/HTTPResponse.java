package com.webserver.http;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 响应对象
 * 该类的每一个实例用于表示服务端发送给客户端的具体响应内容。
 * 每个响应由三部分构成：状态行、响应头、响应正文
 *
 * @Data 2020/2/17 0017
 */
public class HTTPResponse {
    /*
    状态行相关信息定义
     */
    private int statusCode = 200; //状态代码
    private String statusReason = "OK"; //状态描述
    /*
    响应头相关信息定义
     */
    //key：响应头名字 value：响应头对应的值
    private Map<String, String> headers = new HashMap<>();
    /*
    响应正文相关信息定义
     */
    private File entity; //响应正文的实体文件

    private byte[] data; //响应正文的数据
    /*
    连接相关信息定义
     */
    private Socket socket;
    private OutputStream out;

    public HTTPResponse(Socket socket) {
        try {
            this.socket = socket;
            this.out = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将当前响应对象内容以一个标准的HTTP响应格式发送给客户端
     */
    public void flush() {
        sendStatusLine();
        sendHeaders();
        sendContent();
    }

    /**
     * 发送状态行
     */
    private void sendStatusLine() {
        System.out.println("开始发送状态行");
        try {
            String line = "HTTP/1.1" + " " + statusCode + " " + statusReason;
            out.write(line.getBytes("ISO8859-1"));
            out.write(13); //written CR
            out.write(10); //written LF
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("状态行发送完毕");
    }

    /**
     * 发送响应头
     */
    private void sendHeaders() {
        System.out.println("开始发送响应头");
        try {
            /*
            遍历headers，将所有需要发送的头顺序发送
             */
            Set<Map.Entry<String, String>> set = headers.entrySet();
            for (Map.Entry<String, String> header :
                    set) {
                String key = header.getKey();
                String value = header.getValue();
                String line = key + ": " + value;
                out.write(line.getBytes("ISO8859-1"));
                out.write(13);
                out.write(10);
            }

            //单独发送CRLF表示响应头结束
            out.write(13); //written CR
            out.write(10); //written LF
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("响应头发送完毕");
    }

    /**
     * 发送响应正文
     */
    private void sendContent() {
        System.out.println("开始发送响应正文");
        if (entity != null) {
            try (FileInputStream fis = new FileInputStream(entity)) {
                int len = -1;
                byte[] data = new byte[1024 * 10];
                while ((len = fis.read(data)) != -1) {
                    out.write(data, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (data != null) {
            //将这组字节作为正文内容发送给客户端
            try {
                out.write(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("响应正文发送完毕");
    }

    public File getEntity() {
        return entity;
    }

    /**
     * 向当前响应对象中设置正文的实体文件，设置的同时会自动根据该
     * 文件添加两个对应的响应头：Content-Type与Content-Length
     * @param entity
     */
    public void setEntity(File entity) {
        this.entity = entity;
        String fileName = entity.getName();
        /*
        获取文件名称
        404.html
        baidu.png;
         */
        System.out.println(fileName);
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        System.out.println(ext);
        String contentType = HttpContext.getMimeType(ext);
        headers.put("Content-Type", contentType);
        headers.put("Content-Length",entity.length()+" ");
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    /**
     * 向当前响应对象中添加一个响应头
     * @param name
     * @param value
     */
    public void putHeader(String name, String value) {
        headers.put(name, value);
    }

    /**
     * 设置响应正文数据
     * @param data
     */
    public void setContentData(byte[] data) {
        this.data = data;
        this.putHeader("Content-Length",data.length+"");
    }
}

