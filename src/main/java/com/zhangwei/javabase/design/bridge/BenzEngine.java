package com.zhangwei.javabase.design.bridge;

public class BenzEngine implements CarEngine {
    @Override
    public void start() {
        System.out.println("BenzEngine is starting");
    }
}
