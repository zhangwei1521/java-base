package com.zhangwei.javabase.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class ListDemo {
    public static void main(String[] args) {
        test01();
        //test02();
        //test03();
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


    //测试存入null
    private static void test02(){
        List<String> list = new ArrayList<>();
        //get before add will throw IndexOutOfBoundsException
        //String obj1 = list.get(0);

        list.add(null);
        String obj2 = list.get(0);
        System.out.println(obj2);

        //can't print null because it will make compiler can't find the needed method while there are several println
        //System.out.println(null);
        System.out.println((Object) null);
    }
}

