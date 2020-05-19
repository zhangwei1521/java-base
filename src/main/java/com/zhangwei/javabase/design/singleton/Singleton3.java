package com.zhangwei.javabase.design.singleton;

public class Singleton3 {
    private Singleton3(){}

    private static class SingletonHolder{
        private static final Singleton3 instance = new Singleton3();
    }

    public static Singleton3 getInstance(){
        return SingletonHolder.instance;
    }
}
