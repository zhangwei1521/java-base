package com.zhangwei.javabase.design.singleton;

public class Singleton2 {
    private static volatile Singleton2 instance;

    private Singleton2(){}

    public static synchronized Singleton2 getInstance1(){
        if(instance==null){
            instance = new Singleton2();
        }
        return instance;
    }

    public static Singleton2 getInstance2(){
        if(instance==null){
            synchronized(Singleton2.class){
                if(instance==null){
                    instance = new Singleton2();
                }
            }
        }
        return instance;
    }
}
