package com.zhangwei.javabase.collection;

public class ArrayDemo {
    public static void main(String[] args) {
        //test01();
        test02();
    }

    private static void test01(){
        Object[] array = new Object[10];
        //数组设置元素前默认保存了null
        if(array[0]==null){
            System.out.println("array keep null when no element set");
        }
    }

    private static void test02(){
        //允许创建长度为0的数组
        Object[] array = new Object[0];
        System.out.println(array.length);
    }
}
