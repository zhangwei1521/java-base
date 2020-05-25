package com.zhangwei.javabase.collection;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetDemo {
    public static void main(String[] args) {
        test01();
    }

    private static void test01() {
        Set<String> set = new HashSet<>();
        set = new TreeSet<>();
        set.add("hello");
        set.add("java");
        set.add("hello");
        System.out.println(set.size());
    }

}
