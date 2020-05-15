package com.zhangwei.javabase.thread;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicVariableDemo {
    public static void main(String[] args) {
        test01();
    }

    private static void test01() {
        AtomicBoolean ab = new AtomicBoolean();
        System.out.println(ab);
        if(ab.compareAndSet(true,false)){
            System.out.println("success");
        }
    }
}
