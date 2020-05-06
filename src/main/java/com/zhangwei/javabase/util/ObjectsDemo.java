package com.zhangwei.javabase.util;

import java.util.Objects;

public class ObjectsDemo {
    public static void main(String[] args) {
        test01();
    }

    private static void test01() {
        int hash1 = Objects.hashCode("a");
        int hash2 = Objects.hash("a");
        System.out.println(hash1);
        System.out.println(hash2);
    }
}
