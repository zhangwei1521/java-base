package com.zhangwei.javabase.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.*;

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
        InputStream inputStream = DomDemo.class.getResourceAsStream("/xml/book2.xml");

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setValidating(true);

        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        documentBuilder.setEntityResolver(new EntityResolver() {
            @Override
            public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
                System.out.println(publicId+" : "+systemId);
                return null;
            }
        });
        documentBuilder.setErrorHandler(new ErrorHandler() {
            @Override
            public void warning(SAXParseException exception) throws SAXException {
                System.out.println("warning : "+exception);
            }

            @Override
            public void error(SAXParseException exception) throws SAXException {
                System.out.println("error : "+exception);
            }

            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                System.out.println("fatalError : "+exception);
            }
        });

        Document document = documentBuilder.parse(inputStream);
        Element root = document.getDocumentElement();
        System.out.println(root.getTagName());

    }
}
