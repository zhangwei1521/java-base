package com.zhangwei.javabase.system;

import java.util.Properties;

public class SystemDemo {
    public static void main(String[] args) {
        //test01();
        //test02();
        test03();
    }

    //java -Dmy.name=zhangwei com.zhangwei.javabase.system.SystemDemo
    private static void test01(){
        Properties props = System.getProperties();
        String name = props.getProperty("my.name");
        System.out.println("hello : "+name);
    }

    //headless模式
    private static void test02(){
        String value = System.getProperty("java.awt.headless");
        System.out.println(value);
        value = System.getProperty("java.awt.headless",Boolean.toString(true));
        System.out.println(value);
        System.setProperty("java.awt.headless",Boolean.toString(true));
    }

    //
    private static void test03(){
        int i = System.identityHashCode(1);
        int j = System.identityHashCode("1");
        System.out.println(i);
        System.out.println(j);
    }
}
