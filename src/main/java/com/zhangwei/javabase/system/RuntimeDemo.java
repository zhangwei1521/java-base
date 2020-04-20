package com.zhangwei.javabase.system;

public class RuntimeDemo {
    public static void main(String[] args) {
        test01();
    }

    private static void test01(){
        //双核心四线程的CPU这里返回的值为4
        int processorsQuantity = Runtime.getRuntime().availableProcessors();
        System.out.println(processorsQuantity);
    }
}
