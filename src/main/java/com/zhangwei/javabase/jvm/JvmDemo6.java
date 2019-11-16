package com.zhangwei.javabase.jvm;

import com.sun.nio.zipfs.ZipFileSystem;

import javax.swing.*;

/**
 * 打印类加载器
 */
public class JvmDemo6 {
    public static void main(String[] args) {
        System.out.println(JvmDemo6.class.getClassLoader());
        System.out.println(Object.class.getClassLoader());
        System.out.println(JButton.class.getClassLoader());
        System.out.println(ZipFileSystem.class.getClassLoader());
    }
}
