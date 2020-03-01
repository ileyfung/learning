package xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

/**
 * 读取web.xml 为document
 * 获取根元素 web-app
 * 获取根元素中 mime-mapping 所有子元素
 * 遍历每个mime-mapping
 * 获取 mime-mapping 的 extension 子元素，并获取子元素中的文本。
 * 获取 mime-mapping 的 mime-type 子元素，并获取子元素中的文本。
 * @Data 2020/2/22 0022
 */
public class GetWebXML {
    public static void main(String[] args) {
        SAXReader reader = new SAXReader();
        try {
            //读取web.xml文件
            Document document = reader.read(new File("./XML01/web.xml"));
            Element rootElement = document.getRootElement(); //获取文件根元素全部内容
            List<Element> elements = rootElement.elements("mime-mapping"); //获取根元素下所有的指定元素
            //遍历指定元素内容
            for (Element e:elements
                 ) {
                String extension = e.element("extension").getTextTrim(); //获取指定子元素的内容
                //String mime = e.element("mime-type").getTextTrim();
                String mime = e.elementTextTrim("mime-type");
                System.out.println("extension: " + extension);
                System.out.println("mime-type: " + mime);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
