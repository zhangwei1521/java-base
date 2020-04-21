package com.zhangwei.javabase.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class ListDemo1 {
    public static void main(String[] args) {
        //test01();
        //test02();
        test03();
    }

    private static void test01(){
        List<String> yearList = new ArrayList<>();
        System.out.println("size : "+yearList.size());

        yearList.add("2017");
        yearList.add("2018");
        yearList.add("2018");
        if(yearList.contains("2018")){
            System.out.println("yes...");
        }
        String[] years = new String[5];
        years = yearList.toArray(years);
        System.out.println(years[3]);
    }

    private static void test02(){
        Stack<Integer> stack = new Stack<>();
        System.out.println("stack size : "+stack.size());
        System.out.println("stack capacity : "+stack.capacity());
        for(int i=0;i<11;i++){
            stack.push(i);
        }
        System.out.println("stack size : "+stack.size());
        System.out.println("stack capacity : "+stack.capacity());
    }

    //测试存入null
    private static void test03(){
        List<String> list = new ArrayList<>();
        //throw IndexOutOfBoundsException
        //String obj1 = list.get(0);
        list.add(null);
        String obj2 = list.get(0);

        //System.out.println(obj1);
        //can't print null because it will cause compiler can't find the needed method while there are several println
        //valid using   : System.out.println((Object) null);
        //invalid using : System.out.println(null);
        System.out.println(obj2);
    }
}

