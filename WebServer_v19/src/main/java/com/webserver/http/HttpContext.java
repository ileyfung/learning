package com.webserver.http;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HTTP协议相关定义
 * @Data 2020/2/19 0019
 */
public class HttpContext {
    /**
     * Context-Type的值与资源后缀名的对应关系
     * key：资源后缀名
     * value：对应的Context-type
     */
    private static Map<String, String> mime_mapping = new HashMap<String, String>();

    static {
        initMimeMapping();
    }

    /**
     * 初始化资源类型
     * 读取conf/web.xml中资源类型列表，添加到mime-mapping中
     * 可以解决大部分市面上常见文件类型与资源类型的映射关系
     */
    private static void initMimeMapping() {

        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(new File("./WebServer_v18/conf/web.xml"));
            Element rootElement = document.getRootElement();
            List<Element> elements = rootElement.elements("mime-mapping");
            for (Element e :
                    elements) {
                String ext = e.elementTextTrim("extension");
                String mime = e.elementTextTrim("mime-type");
                mime_mapping.put(ext, mime);
            }
            //System.out.println(mime_mapping);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
        /**
     * 根据资源后缀名获取对应的Context-Type的值
     * @param ext
     * @return
     */
    public static String getMimeType(String ext) {
        return mime_mapping.get(ext);
    }

    public static void main(String[] args) {
        String fileName = "header.png";
        String ext = fileName.substring(fileName.lastIndexOf(".")+1);
        System.out.println(ext);
        System.out.println(getMimeType(ext));
    }
}
