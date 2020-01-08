package com.zhangwei.javabase.thread;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Collections;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockoutDemo {
    static final ThreadMXBean threadMxBean = ManagementFactory.getThreadMXBean();

    public static void main(String[] args) {
        //test01();
        test02();
    }

    private static void test01(){
        new Thread(()->{
            try {
                Object lock = new Object();
                synchronized (lock){
                    lock.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void test02(){
        Lock lock1 = new ReentrantLock();
        //Lock lock2 = new ReentrantLock();
        lock1.lock();
        Thread t1 = new Thread(()->{
            //lock1.lock();
            try {
                int c = System.in.read();
                System.out.println(c);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("finish");
        });
        t1.start();
        try {
            Thread.sleep(100);
            //threadMxBean.getThreadInfo(t1.getId());
            System.out.println(t1.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
