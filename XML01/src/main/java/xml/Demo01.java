package xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * 使用dom4j读取xml文件
 *
 * @Data 2020/2/20 0020
 */
public class Demo01 {
    public static void main(String[] args) throws DocumentException, IOException {
        SAXReader reader = new SAXReader();
        //Document document = reader.read(new File("./XML01/books.xml"));
        FileInputStream fis = new FileInputStream("./XML01/books.xml");
        Document document = reader.read(fis);
        fis.close();
        System.out.println(document.asXML());

        //读取根元素 root 根，Element元素
        Element rootElement = document.getRootElement();
        System.out.println("根元素：" + rootElement.asXML());

        //获取元素的全部子元素
        //这里是获取root元素的全部book子元素
        List<Element> elements = rootElement.elements();
        Iterator<Element> iterator = elements.iterator();
        while (iterator.hasNext()) {
            Element next = iterator.next();
            System.out.println("根元素下的全部子元素" + next.asXML());
        }
        System.out.println("***********");
        for (Element e :
                elements) {
            System.out.println("根元素下的全部子元素：" + e.asXML());
        }
        System.out.println("************");

        //获取一批指定名字的子元素
        List<Element> book = rootElement.elements("book");
        for (Element e :
                book
        ) {
            //e是book元素的
            System.out.println(e.asXML());
            System.out.println("***********");
            //获取book下的子元素
            Element name = e.element("name");
            Element author = e.element("author");
            System.out.println(name.asXML());
            System.out.println(author.asXML());
            //获取元素中文字内容
            System.out.println("*************");
            String nameTextTrim = name.getTextTrim(); //去除前后空白
            String authorTextTrim = author.getTextTrim();
            System.out.println(nameTextTrim);
            System.out.println(authorTextTrim);
        }
        System.out.println("****************");
        //读取元素属性值
        System.out.println("读取元素属性值");
        for (Element e :
                book) {
            //获取元素属性值 attribute 属性，value 值
            String id = e.attributeValue("id");
            String lang = e.attributeValue("lang");
            System.out.println(id+","+lang);
        }
    }
}
