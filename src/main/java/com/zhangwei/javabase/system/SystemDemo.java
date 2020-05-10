package com.zhangwei.javabase.system;

import java.util.Properties;

public class SystemDemo {
    public static void main(String[] args) {
        test01();
    }

    //java -Dmy.name=zhangwei com.zhangwei.javabase.system.SystemDemo
    private static void test01(){
        Properties props = System.getProperties();
        String name = props.getProperty("my.name");
        System.out.println("hello : "+name);
    }
}
