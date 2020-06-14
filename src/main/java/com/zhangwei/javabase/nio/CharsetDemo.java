package com.zhangwei.javabase.nio;

import java.nio.charset.Charset;
import java.util.SortedMap;

public class CharsetDemo {
    public static void main(String[] args) {
        //test01();
        test02();
    }

    private static void test01() {
        SortedMap<String, Charset> charsetMap = Charset.availableCharsets();
        for(String name : charsetMap.keySet()){
            System.out.println(name);
        }
    }

    private static void test02() {
        System.out.println(Charset.defaultCharset().name());
    }
}
