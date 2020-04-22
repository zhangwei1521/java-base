package com.zhangwei.javabase.collection;

import java.util.Stack;

public class StackDemo {
    public static void main(String[] args) {
        test01();
        //test02();
        //test03();
    }

    private static void test01(){
        //default capacity is 10, when size==capacity,next put will do capacity *= 2;
        Stack<Integer> stack = new Stack<>();
        System.out.println("stack size : "+stack.size());
        System.out.println("stack capacity : "+stack.capacity());
        for(int i=0;i<11;i++){
            stack.push(i);
        }
        System.out.println("stack size : "+stack.size());
        System.out.println("stack capacity : "+stack.capacity());
    }

}
