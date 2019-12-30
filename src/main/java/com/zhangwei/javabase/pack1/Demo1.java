package com.zhangwei.javabase.pack1;

import java.net.URL;

public class Demo1 {
    static ThreadLocal threadLocal =  new ThreadLocal();

    public static void main(String[] args) {
        //test02();
        test04();
    }

    private static void test01(){
        if(null==null || ((String)null).length()<1){
            System.out.println("null is empty");
        }
        //null 可以进行强制类型转换
        String str = (String)null;
        if(str==null){
            System.out.println("str is null");
        }
    }

    private static void test02(){
        URL url = Thread.currentThread().getClass().getResource("/");
        System.out.println(url);
        System.out.println(url.getFile());
        System.out.println(url.getFile().substring(1));
    }

    private static void test03(Object obj){
        threadLocal.set(obj);
    }

    private static void test04(){
        int a = 16,b=10;
        if(a==0)
            System.out.println("a==0");
        else if(b==11)
            if(a>15){
                System.out.println("a>15");
            }
            else if(a<5 && a>1) {
                System.out.println("a<5 && a>1");
            }
            if(a<=1)
                System.out.println("a<=1");
    }
}