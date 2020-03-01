package com.webserver.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * WebServer主类
 *
 * @Data 2020/2/13 0013
 */
public class WebServer {
    private ServerSocket server;

    private ExecutorService threadPool;

    public WebServer() {
        try {
            System.out.println("正在启动服务器。。。");
            server = new ServerSocket(8080);
            threadPool = Executors.newFixedThreadPool(100);
            System.out.println("服务器已启动！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            while(true) {
                System.out.println("等待客户端连接。。。");
                Socket socket = server.accept();
                System.out.println("一个客户端连接了！");
                ClientHandler c1 = new ClientHandler(socket);
                threadPool.execute(c1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WebServer server = new WebServer();
        server.start();
    }
}
