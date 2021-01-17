package com.winter.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.Iterator;
import java.util.Objects;

public class SQLXMLParse {

    public void parse(String xml) {
        String path = Objects.requireNonNull(SQLXMLParse.class.getClassLoader().getResource(xml)).getPath();
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read(path);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        assert document != null;
        Element root = document.getRootElement();
        String rootName = root.getName();
        if (rootName.equals("sql")){
            parseElement(root);
        }else {
            throw new IllegalArgumentException("根元素为"+rootName+"非sql,请检查xml文件");
        }
    }

    public void parseElement(Element root){
        assert root != null;
        Iterator<Element> elementIterator = root.elementIterator();
        while (elementIterator.hasNext()){
            Element rootNext = elementIterator.next();
            String rootNextName = rootNext.getName();
            String rootNextText = rootNext.getText();
            if (rootNextName.equals("select")){
                Object select = SQLParse.select(rootNextText);

            }else if (rootNextName.equals("insert")){

            }else if (rootNextName.equals("update")){

            }else if (rootNextName.equals("delete")){

            }else if (rootNextName.equals("properties")){
//                throw new IllegalArgumentException("参数异常：" + rootNextName);
            }
        }
    }
}
