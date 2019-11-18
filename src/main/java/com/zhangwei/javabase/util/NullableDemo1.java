package com.zhangwei.javabase.util;

import com.sun.istack.internal.Nullable;

public class NullableDemo1 {
    public static void main(String[] args) {
        test01(null);
    }

    private static void test01(@Nullable String str){
        System.out.println(str.toUpperCase());
    }
}
