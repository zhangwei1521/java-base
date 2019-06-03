package com.zhangwei.javabase.classpath;

public class TestDemo1 {
    public static void main(String[] args) {
       //printClassPath();
        test01();
    }

    private static void printClassPath(){
        System.out.println(Thread.currentThread().getClass().getResource("/").getPath());
        System.out.println();
        String classpath = System.getProperty("java.class.path");
        String [] arr = classpath.split(";");
        for(String path : arr){
            System.out.println(path);
        }
    }

    private static void test01(){
        int a = 49;
        System.out.println(a<<8);
    }
}
