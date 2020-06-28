package com.zhangwei.javabase.design.bridge;

public class BWMEngine implements CarEngine {
    @Override
    public void start() {
        System.out.println("BWMEngine is starting");
    }
}
