package com.zhangwei.javabase.system;

import java.util.Map;
import java.util.Properties;

public class SystemDemo {
    public static void main(String[] args) {
        //test01();
        //test02();
        //test03();
        test04();
        //test05();
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

    private static void test04(){
        //java提供的运行时属性变量
        Properties props = System.getProperties();
        props.forEach((k,v)-> System.out.printf("%s : %s\n",k,v));
    }

    private static void test05(){
        //操作系统的环境变量
        Map<String,String> envMap = System.getenv();
        envMap.forEach((k,v)-> System.out.printf("%s : %s\n",k,v));
    }

    //打印classpath
    private static void test06() {
        System.out.println(Thread.currentThread().getClass().getResource("/").getPath());
        System.out.println();
        String classpath = System.getProperty("java.class.path");
        String[] arr = classpath.split(";");
        for (String path : arr) {
            System.out.println(path);
        }
    }
}
