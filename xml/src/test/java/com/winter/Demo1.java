package com.winter;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.Objects;

public class Demo1 {

    @Test
    public void test() throws Exception{
        org.jsoup.nodes.Document document = Jsoup.parse(new URL("https://github.com/GongJingYu/Winter"), 10000);
        System.out.println(document);
    }

    @Test
    public void testJsoup() throws Exception{
        String path = Objects.requireNonNull(Demo1.class.getClassLoader().getResource("UserMapper.xml")).getPath();
        org.jsoup.nodes.Document document = Jsoup.parse(new File(path), "UTF-8");

        Elements sql = document.getElementsByTag("sql");
        boolean b = sql.hasText();

        Elements insert = document.getElementsByTag("insert");
        System.out.println(insert);

        String text = insert.text();
        System.out.println(text);

        String id = insert.attr("id");
        System.out.println(id);
    }

    @Test
    public void testDom4j() throws Exception{
        SAXReader saxReader = new SAXReader();
        String path = Objects.requireNonNull(Demo1.class.getClassLoader().getResource("UserMapper.xml")).getPath();
        Document document = saxReader.read(path);

        Element rootElement = document.getRootElement();

        Iterator<Element> elementIterator = rootElement.elementIterator();
        while (elementIterator.hasNext()){
            Element subElement = elementIterator.next();
            System.out.println("subElement = " + subElement);
        }
    }
}
