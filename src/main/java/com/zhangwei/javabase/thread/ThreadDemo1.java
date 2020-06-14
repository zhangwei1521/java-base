package com.zhangwei.javabase.thread;

import java.io.IOException;

public class ThreadDemo1 {
    public static void main(String[] args) {
        //test01();
        //test02();
        test03();
    }

    private static void test01(){
        Thread t1 = new Thread(){
            @Override
            public void run(){
                System.out.println("thread name : "+Thread.currentThread().getName());
                while (true);
            }
        };
        t1.start();
        //线程对象不会在显示引用不存在时就被JVM回收，这和普通对象是不同的
        t1 = null;
    }

    private static void test02(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread currentThread = Thread.currentThread();
                System.out.println("thread id : "+currentThread.getId());
                System.out.println("thread name : "+currentThread.getName());
                System.out.println("thread isDaemon : "+currentThread.isDaemon());
                System.out.println("thread priority : "+currentThread.getPriority());
                System.out.println("thread state : "+currentThread.getState());
            }
        });
        t1.start();
    }

    private static void test03(){
        Thread t1 = new Thread(){
            @Override
            public void run(){
                try {
                    System.in.read();
                    System.out.println("exit");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //线程发起IO请求不会导致线程的状态变化，线程仍然是RUNNING状态。
        System.out.println(t1.getState());
    }
}
