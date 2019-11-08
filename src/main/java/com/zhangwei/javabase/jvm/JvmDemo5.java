package com.zhangwei.javabase.jvm;

/**
 * 测试classpath
 */
public class JvmDemo5 {
    public static void main(String[] args) {
        test01();
    }

    private static void test01() {
        System.out.println(Thread.currentThread().getClass().getResource("/").getPath());
        System.out.println();
        String classpath = System.getProperty("java.class.path");
        String[] arr = classpath.split(";");
        for (String path : arr) {
            System.out.println(path);
        }
    }
}
