package com.zhangwei.javabase.collection;

import java.util.HashSet;
import java.util.Set;

public class SetDemo {
    public static void main(String[] args) {
        test01();
    }

    private static void test01() {
        Set<String> set = new HashSet<>();
        set.add("hello");
        set.add("java");
        set.add("hello");
        System.out.println(set.size());
    }

}
