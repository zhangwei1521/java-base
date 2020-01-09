package com.zhangwei.javabase.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadFactoryDemo {
    public static void main(String[] args) {
        ThreadFactory threadFactory = new MyThreadFactory();
        threadFactory.newThread(()->{
            System.out.println("hello");
        });
    }
}

class MyThreadFactory implements ThreadFactory{
    AtomicInteger threadIndex = new AtomicInteger(0);
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setDaemon(false);
        thread.setPriority(4);
        thread.setUncaughtExceptionHandler(new MyThreadExceptionHandler());
        thread.setName("mythread-"+threadIndex.getAndAdd(1));

        return thread;
    }
}

class MyThreadExceptionHandler implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        String tname = t.getName();
        System.err.println(tname+" terminated : "+e);
    }
}