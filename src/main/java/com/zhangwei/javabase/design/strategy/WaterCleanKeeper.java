package com.zhangwei.javabase.design.strategy;

public class WaterCleanKeeper implements CleanKeeper {
    @Override
    public void clean() {
        System.out.println("clean with water");
    }
}
