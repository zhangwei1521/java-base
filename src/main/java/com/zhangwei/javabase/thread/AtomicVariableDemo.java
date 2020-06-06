package com.zhangwei.javabase.thread;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

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

    private static void test02() {
        AtomicInteger ai = new AtomicInteger();
        ai = new AtomicInteger(1);
        ai.set(2);
        ai.compareAndSet(2,3);
    }
}
