package com.zhangwei.javabase.string;

public class StringBuilderDemo {
    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder("abcde");
        builder.replace(0,1,"A");
        System.out.println(builder);
    }
}
