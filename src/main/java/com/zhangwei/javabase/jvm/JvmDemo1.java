package com.zhangwei.javabase.jvm;

/**
 * 测试设置堆内存参数
 */
public class JvmDemo1 {
    //-verbose:gc -XX:+PrintGCDetails -Xmx20M -Xms20M -Xmn10M -XX:SurvivorRatio=8 -XX:+UseSerialGC
    public static void main(String[] args) {
        byte[] bArr1 = new byte[2*1024*1024];
        byte[] bArr2 = new byte[2*1024*1024];
        byte[] bArr3 = new byte[2*1024*1024];
        byte[] bArr4 = new byte[4*1024*1024];
    }
}
