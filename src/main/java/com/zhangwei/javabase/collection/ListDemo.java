package com.zhangwei.javabase.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

    //泛型测试
    private static void test03(){
        List<Object> l1 = new ArrayList<>();
        List<String> l2 = new ArrayList<>();
        l1.add("hello");
        f1(l1);
        //编译不通过，如果允许传入List<String>，那f1方法可能会向其写入非String类型，产生运行时异常
        //f1(l2);

        //l3中存储的类型是String的父类
        List<? super String> l3 = l2;
        //所以可以存储String
        l3.add("hello");
        //l3中存储的类型是String的父类，但是不明确具体类型，所以不能存入Object
        //l3.add(new Object());
        //l3中存储的类型是String的父类，所以取出的类型不能直接向下转为String
        //String s1 = l3.get(0);
        //l3中存储的类型是String的父类，所以取出的类型可以直接向上转为Object
        Object o1 = l3.get(0);

        //l4中存储的类型是String的子类
        List<? extends String> l4 = l2;
        //所以不能存储String
        //l4.add("hello");

        //泛型通配符 ? 只用在带泛型的类声明和泛型方法声明中
        //List<?> l5 = new ArrayList<>();
        //l5.add("hello");
    }

    private static void test04(){
        LinkedList<String> list = new LinkedList<>();
    }

    private static void f1(List<Object> l){
        l.add(Integer.valueOf(1));
    }

}

