package com.zhangwei.javabase.jvm;

import java.io.IOException;

/**
 * 打印GC日志
 *
 *  java -verbose:gc -XX:+PrintGCDetails -Xmx20M -Xms20M -Xmn10M -XX:SurvivorRatio=8 com.zhangwei.javabase.jvm.JvmDemo10 1
 *  这段代码没有发生GC，是由于最后的4MB大于新生代可用空间，所以直接将其放到了老年代
 *
 *  java -verbose:gc -XX:+PrintGCDetails -Xmx20M -Xms20M -Xmn10M -XX:SurvivorRatio=8 com.zhangwei.javabase.jvm.JvmDemo10 2
 *  发生两次GC
 */
public class JvmDemo10 {
    public static void main(String[] args) throws IOException {
        if(args == null || args[0] == null){
            System.err.println("usage : one param needed to choose testcase");
            return;
        }
        if(args[0].equals("1")){
            test01();
        }
        else if(args[0].equals("2")){
            test02();
        }
    }

    private static void test01() throws IOException {
        byte[] bArr1 = new byte[2*1024*1024];
        byte[] bArr2 = new byte[2*1024*1024];
        byte[] bArr3 = new byte[2*1024*1024];
        pause();

        byte[] bArr4 = new byte[4*1024*1024];
        pause();
    }

    private static void test02() throws IOException {
        byte[] bArr1 = new byte[2*1024*1024];
        byte[] bArr2 = new byte[2*1024*1024];
        byte[] bArr3 = new byte[2*1024*1024];
        pause();

        byte[] bArr4 = new byte[3*1024*1024];
        pause();
    }

    private static void pause() throws IOException {
        System.out.println("enter something to continue : ");
        byte[] buf = new byte[10];
        System.in.read(buf);
    }
}
