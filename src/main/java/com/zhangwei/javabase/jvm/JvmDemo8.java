package com.zhangwei.javabase.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试堆溢出
 * java.lang.OutOfMemoryError: Java heap space
 */
public class JvmDemo8 {

    static class MyObject {
        //1k
        private byte[] buf = new byte[1024];
    }

    //java -Xmx1M -Xms1M com.zhangwei.javabase.jvm.JvmDemo8
    public static void main(String[] args) {
        List<MyObject> list = new ArrayList<>();
        int i=0;
        while(true) {
            list.add(new MyObject());
            System.out.println(++i);
        }
    }
}

