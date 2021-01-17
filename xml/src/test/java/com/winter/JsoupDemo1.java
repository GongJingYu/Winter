package com.winter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class JsoupDemo1 {

    @Test
    public void test2() throws IOException {
        String path = Objects.requireNonNull(JsoupDemo1.class.getClassLoader().getResource("user.xml")).getPath();
        Document document = Jsoup.parse(new File(path), "UTF-8");
//        System.out.println(document);

        Elements student = document.getElementsByTag("student");
//        System.out.println(student);

        Elements number = document.getElementsByAttribute("number");
        System.out.println(number);
    }

    @Test
    public void test1() throws Exception{
        //1. 获取Document对象,根据xml文档获取
        //1.1 获取xml文件的路径
        String path = Objects.requireNonNull(JsoupDemo1.class.getClassLoader().getResource("user.xml")).getPath();

        //1.2 解析xml文档，加载文件进内存，获取dom树 ---> document对象
        Document document = Jsoup.parse(new File(path), "UTF-8");
        //1.3 获取元素对象
        Elements name = document.getElementsByTag("name");
        System.out.println("name.size() = " + name.size());

        Element element = name.get(0);
        System.out.println(element.text());
    }
}
