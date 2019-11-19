package com.zhangwei.javabase.io;

import java.net.URL;

public class IODemo7 {
    public static void main(String[] args) {
        test01();
        //test02();
    }

    private static void test01(){
        URL url1 = IODemo7.class.getResource("/");
        URL url2 = IODemo7.class.getResource("");
        System.out.println(url1);
        System.out.println(url2);
        URL url3 = IODemo7.class.getClassLoader().getResource("/");
        URL url4 = IODemo7.class.getClassLoader().getResource("");
        System.out.println(url3);
        System.out.println(url4);
    }

    private static void test02(){

    }
}
