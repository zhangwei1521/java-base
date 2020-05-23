package com.zhangwei.javabase.design.strategy;

public class SandCleanKeeper implements CleanKeeper {
    @Override
    public void clean() {
        System.out.println("clean with sand");
    }
}
