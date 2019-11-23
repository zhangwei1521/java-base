package com.zhangwei.javabase.jvm;

import com.zhangwei.javabase.string.StringUtils;

import java.net.URL;
import java.net.URLClassLoader;

//测试URLClassLoader
public class JvmDemo11 {
    public static void main(String[] args) {
        try {
            URL[] urls={new URL("http://127.0.0.1/base.jar")};
            URLClassLoader classLoader = new URLClassLoader(urls);
            StringUtils stringUtils = (StringUtils)classLoader.loadClass("com.zhangwei.javabase.string.StringUtils").newInstance();
            System.out.println(stringUtils.isEmpty("12"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
