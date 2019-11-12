package com.zhangwei.javabase.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class ListDemo1 {
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

    private static void test03(){
        MyList myList = new MyList();
        Object[] objs = myList.toArray();
        Vector numbers = new Vector<>(myList);
        System.out.println(Object[].class);

        SubList subList = new SubList();

    }
}

class MyList<E> extends ArrayList<E>{
    public Integer[] toArray(){
        return new Integer[]{1,2};
    }

    public Object getObj(){
        return "1";
    }
}

class SubList<E> extends MyList{
    public Integer getObj(){
        Class clazz = SubList.class;
        return 2;
    }
}
