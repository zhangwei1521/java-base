package com.zhangwei.javabase.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class DomDemo {
    public static void main(String[] args) {
        try {
            test01();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void test01() throws ParserConfigurationException, IOException, SAXException {
        InputStream inputStream = DomDemo.class.getResourceAsStream("/xml/xml-demo3.xml");

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);

        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.parse(inputStream);
        Element root = document.getDocumentElement();
        System.out.println(root.getNamespaceURI());

    }
}
