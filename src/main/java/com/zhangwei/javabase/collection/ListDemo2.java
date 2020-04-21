package com.zhangwei.javabase.collection;

import com.zhangwei.javabase.collection.my.ArrayList;
import com.zhangwei.javabase.collection.my.List;
import com.zhangwei.javabase.collection.my.MyArrayUtil;

public class ListDemo2 {
    public static void main(String[] args) {
        test01();
    }

    private static void test01(){
        List<Integer> list = new ArrayList<>(1);
        System.out.println("size : "+list.size());
        list.add(1);
        System.out.println("size : "+list.size());
        list.add(1);
        System.out.println("size : "+list.size());
        //list.add(null);
        list.add(3);
        System.out.println("size : "+list.size());
        //list.get(-1);
        // can not use int here, because jvm will do translation from Integer to int,
        // if return null, the translating will throw NullPointerException
        Integer item = list.get(2);
        System.out.println("item at 2(index) : "+item);
        //list.get(3);
        boolean c0 = list.contains(0);
        boolean c1 = list.contains(1);
        boolean cnull = list.contains(null);
        System.out.println("contains 0 : "+c0);
        System.out.println("contains 1 : "+c1);
        System.out.println("contains null : "+cnull);
        int fidx0 = list.firstIndexOf(0);
        int fidx1 = list.firstIndexOf(1);
        int fidxNull = list.firstIndexOf(null);
        System.out.println("first index of 0 : "+fidx0);
        System.out.println("first index of 1 : "+fidx1);
        System.out.println("first index of null : "+fidxNull);
        int[] aidx = list.allIndexOf(1);
        System.out.println("all index of 1 : "+ MyArrayUtil.toString(aidx));
        Integer del = list.remove(2);
        System.out.println("del : "+del);
        System.out.println("size : "+list.size());
        int delCount = list.removeAll(1);
        System.out.println("delCount : "+delCount);
        System.out.println("size : "+list.size());
        list.add(5);
        Integer old = list.set(0,10);
        System.out.println("old : "+old);
        System.out.println("new : "+list.get(0));
        list.add(10);
        list.replaceAll(10,20);
        Integer[] array = list.toArray(new Integer[list.size()]);
        System.out.println("array : "+MyArrayUtil.toString(array));
        List<Integer> subList = list.subList(0,1);
        System.out.println("sublist : "+MyArrayUtil.toString(subList.toArray(new Integer[subList.size()])));
    }
}
