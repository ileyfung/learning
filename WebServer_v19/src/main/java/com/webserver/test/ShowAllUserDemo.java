package com.webserver.test;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @ClassName ShowAllUserDemo
 * @Deacription
 * 使用raf读取user.dat文件
 * 循环读取该文件，循环次数应当是文件长度/100
 * 每条记录读取时：
 * 首先读取32字节，将该字节按照UTF-8编码转换为字符串，注意，转换后要trim，
 * 因为这个字符串含义空白字符。
 * 以此类推读取密码，昵称。之后读取int值，是年龄
 * 输出格式：张三，2322，三，22
 * @Date 2020/1/11 0011
 **/
public class ShowAllUserDemo {
    public static void main(String[] args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile("./WebServer_v15/src/user.dat","rw");
        for (int i = 0; i < raf.length() / 64; i++) {
            byte[] bytes = new byte[32];
            raf.read(bytes);
            String name = new String(bytes,"utf-8").trim();

            raf.read(bytes);
            String ps = new String(bytes,"utf-8").trim();

            System.out.println("姓名：" + name + "\t密码：" + ps);
        }
        raf.close();
    }
}
