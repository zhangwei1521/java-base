package com.zhangwei.javabase.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesDemo {
    public static void main(String[] args) throws IOException {
        //test01();
        //test02();
        test03();
    }

    private static void test01() throws IOException {
        Properties props = new Properties();
        InputStream in = PropertiesDemo.class.getResourceAsStream("/database.properties");
        props.load(in);
        System.out.println(props.getProperty("dataSource.initialSize"));
    }

    private static void test02() throws IOException {
        Properties props = new Properties();
        InputStream in = PropertiesDemo.class.getResourceAsStream("/xml/xml-properties.xml");
        props.loadFromXML(in);
        System.out.println(props.getProperty("username"));
    }

    private static void test03() throws IOException {
        Properties props = new Properties();
        props.setProperty("username","zhangwei");
        OutputStream out1 = new FileOutputStream("target/properties-out.properties");
        OutputStream out2 = new FileOutputStream("target/properties-out.xml");
        props.store(out1,"hello");
        props.storeToXML(out2,"hello","GBK");
    }
}
