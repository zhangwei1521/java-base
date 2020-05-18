package com.zhangwei.javabase.jvm;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class JvmDemo11 {

    private static final int _1M = 1024*1024;

    //-Xmx5M
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
        // 虽然堆内存只给了5M，但是由于ByteBuffer.allocateDirect()分配的内存不在堆空间中，而是不属于JVM管理的直接内存
        // 所以这里不会出现OutOfMemoryError
        List<ByteBuffer> list = new ArrayList<>();
        int i=0;
        while (i<20){
            ByteBuffer buf = ByteBuffer.allocateDirect(_1M);
            System.out.println(++i);
        }

    }
}
