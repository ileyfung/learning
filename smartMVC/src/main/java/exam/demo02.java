package exam;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * @Describe 读取xml文件
 * @Data 2020/7/22 0022
 */
public class demo02 {
    public static void main(String[] args)throws Exception {
        //创建SAXReader对象
        SAXReader reader = new SAXReader();
        //从resource文件夹中读取xml文件
        InputStream in = demo02.class.getClassLoader().getResourceAsStream("beans.xml");
        //使用SAX从给定的InputSource读取文档,返回新创建的文档实例
        Document document = reader.read(in);
        //获取xml文件中的根元素
        Element rootElement = document.getRootElement();
        //获取根元素下所有子元素
        List<Element> elements = rootElement.elements();
        for (Element element : elements) {
            //attributeValue:这将返回具有给定完全限定名称的属性的属性值；如果没有此类属性，则返回null；如果属性值为空，则返回空字符串。
            String value = element.attributeValue("class");
            System.out.println(value);
        }
    }
}
