package com.zhangwei.javabase.collection;

public class ArrayDemo {
    public static void main(String[] args) {
        //test01();
        test02();
    }

    private static void test01(){
        Object[] array = new Object[10];
        if(array[0]==null){
            System.out.println("array keep null when no element set");
        }
    }

    private static void test02(){
        Object[] array = new Object[0];
        System.out.println(array.length);
    }
}
