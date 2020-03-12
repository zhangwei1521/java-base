package com.zhangwei.javabase.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public class XmlParseDemo {
    public static void main(String[] args) throws Exception {
        //test01();
        test02();
    }

    //使用DOM解析XML文档
    private static void test01() throws ParserConfigurationException, IOException, SAXException {
        //InputStream inputStream = XmlParseDemo.class.getResourceAsStream("/xml/xml-demo.xml");
        InputStream inputStream = XmlParseDemo.class.getResourceAsStream("/xml/xml-demo2.xml");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        //验证
        documentBuilder = validateDocumentBuilder(documentBuilderFactory);

        Document document = documentBuilder.parse(inputStream);
        Element root = document.getDocumentElement();
        NodeList node1s = root.getElementsByTagName("properties");
        Element propertiesElement = (Element)node1s.item(0);
        NodeList node2s = root.getElementsByTagName("environments");
        Element environmentsElement = (Element)node2s.item(0);

        Configuration configuration = new Configuration();
        configuration.properties = propertiesElement.getAttribute("resource");

        List<Environment> environments = new LinkedList<>();
        configuration.environments = environments;
        node1s = environmentsElement.getElementsByTagName("environment");
        for (int i=0;i<node1s.getLength();i++){
            Element environmentElement = (Element)node1s.item(i);
            Environment env = new Environment();
            environments.add(env);

            env.id = environmentElement.getAttribute("id");
            node2s = environmentElement.getElementsByTagName("transactionManager");
            Element transactionManagerElement = (Element)node2s.item(0);
            env.transactionManager = transactionManagerElement.getAttribute("type");

            node2s = environmentElement.getElementsByTagName("dataSource");
            Element dataSourceElement = (Element)node2s.item(0);
            DataSource dataSource = new DataSource();
            env.dataSource = dataSource;

            node2s = dataSourceElement.getElementsByTagName("property");
            for (int j=0;j<node2s.getLength();j++){
                Element propertyElement = (Element)node2s.item(j);
                String attrName = propertyElement.getAttribute("name");
                String attrVal = propertyElement.getAttribute("value");
                switch (attrName){
                    case "driver":
                        dataSource.driver = attrVal;
                        break;
                    case "url":
                        dataSource.url = attrVal;
                        break;
                    case "username":
                        dataSource.username = attrVal;
                        break;
                    case "password":
                        dataSource.password = attrVal;
                        break;
                }
            }
        }

        System.out.println(configuration);
    }

    private static DocumentBuilder validateDocumentBuilder(DocumentBuilderFactory documentBuilderFactory) throws ParserConfigurationException {
        //验证
        documentBuilderFactory.setValidating(true);
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        builder.setErrorHandler(new ErrorHandler() {
            @Override
            public void error(SAXParseException exception) throws SAXException {
                System.err.println(exception);
                throw exception;
            }

            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                throw exception;
            }

            @Override
            public void warning(SAXParseException exception) throws SAXException {
            }
        });
        return builder;
    }

    //使用SAX解析XML文档
    private static void test02() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser parser = saxParserFactory.newSAXParser();
        InputStream in = XmlParseDemo.class.getResourceAsStream("/xml/xml-demo.xml");
        SAXHandler handler = new SAXHandler();
        parser.parse(in,handler);
        //System.out.println("================");
    }
}

class SAXHandler extends DefaultHandler{
    @Override
    public void characters(char[] chars,int start,int end) throws SAXException {
        System.out.println(new String(chars,start,end));
        super.characters(chars,start,end);
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("start parse xml document!");
        super.startDocument();
    }

    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) throws SAXException {
        System.out.println("start parse element : ");
        System.out.print("\t"+qName);
        if(attributes != null){
            for(int i=0; i<attributes.getLength();i++){
                System.out.print(" ["+attributes.getQName(i)+" : "+attributes.getValue(i)+"] ");
            }
        }
        System.out.println();
        super.startElement(uri,localName,qName,attributes);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("parse element complete : "+qName);
        super.endElement(uri,localName,qName);
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("parse xml document complete");
        super.endDocument();
    }
}

class Configuration{
    String properties;
    List<Environment> environments;

    public String toString(){
        return "{properties: " +properties+",environments: " +environments+"}";
    }
}

class Environment{
    String id;
    String transactionManager;
    DataSource dataSource;

    public String toString(){
        return "{id: "+id+",transactionManager: "+transactionManager+", dataSource: "+dataSource+"}";
    }
}

class DataSource{
    String driver;
    String url;
    String username;
    String password;
    public String toString(){
        return "{driver: "+driver+", url: "+url+", username: "+username+", password: "+password+"}";
    }
}