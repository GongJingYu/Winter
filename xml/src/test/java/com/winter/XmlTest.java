package com.winter;

import com.winter.xml.SQLXMLParse;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

public class XmlTest {

    @Test
    public void testXmlParse() throws Exception{
        SQLXMLParse parse = new SQLXMLParse();
        parse.parse("UserMapper.xml");
    }

    //dom4j解析xml文件
    @Test
    public void test1() throws Exception {
        //1.创建SAXReader对象，用于读取xml文件
        SAXReader saxReader = new SAXReader();

        //2.读取xml文件，得到Document对象
        String path = XmlTest.class.getClassLoader().getResource("book.xml").getPath();
        File fis = new File("src/main/resources/book.xml");
        Document document = saxReader.read(path);

        //3.获取根元素
        Element root = document.getRootElement();

        //4.获取根下的子元素
        Iterator<Element> elementIterator = root.elementIterator();
        while (elementIterator.hasNext()){
            Element next = elementIterator.next();
            //属性
            Iterator<Attribute> attributeIterator = next.attributeIterator();
            while (attributeIterator.hasNext()){
                Attribute next1 = attributeIterator.next();
            }
            //子元素
            Iterator<Element> nextElement = next.elementIterator();
            while (nextElement.hasNext()){
                Element nextEle = nextElement.next();
                System.out.println(nextEle.getName() + "=" + nextEle.getText());
            }
//            Element author = next.element("author");
//            Element name = next.element("name");
//            Element price = next.element("price");
//            System.out.println(author.getText() + ", " + name.getText() + ", " + price.getText());
        }
    }

    //dom4j创建xml文件
    @Test
    public void test2() throws IOException {
        //1.通过DocumentHelper创建一个Document对象
        Document document = DocumentHelper.createDocument();

        //2.添加并得到根元素
        Element root = document.addElement("books");

        //3.为根元素添加并得到子元素
        Element book = root.addElement("book");

        //4.为子元素添加属性
        book.addAttribute("id","1");

        //5.为子元素添加子元素
        Element author = book.addElement("author");
        Element name = book.addElement("name");
        Element price = book.addElement("price");

        //6.为子元素添加文本
        author.addText("gjy");
        name.addText("爱你的日子里");
        price.addText("30￥");

        //7.将document输出到xml文件中
//        Writer writer = new FileWriter("src/book2.xml");
//        document.write(writer);
//        writer.close();
        //8.格式良好的输出
        Writer writer = new FileWriter("src/book3.xml");
        OutputFormat outputFormat = OutputFormat.createPrettyPrint();
        XMLWriter xmlWrite = new XMLWriter(writer, outputFormat);
        xmlWrite.write(document);
        xmlWrite.close();
        writer.close();

    }
}
