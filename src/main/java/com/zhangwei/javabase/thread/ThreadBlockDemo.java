package com.zhangwei.javabase.thread;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadBlockDemo {
    public static void main(String[] args) {
        test01();
    }

    private static void test01(){
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();
        lock1.lock();   //1
        lock2.lock();   //2
        Thread t1 = new Thread(()->{
            //lock方法等待期间线程状态为WAITING
            lock1.lock();   //3，等待4

            try {
                //使用阻塞IO，等待期间线程状态仍是RUNNABLE
                int c = System.in.read();   //5
                System.out.println(c);
            } catch (IOException e) {
                e.printStackTrace();
            }
            lock1.unlock();     //6

            lock2.lock();       //8，等待9
            //等待synchronized期间线程状态为 BLOCKED
            synchronized (lock1){
                lock2.unlock();
                System.out.println("finish");
            }
        });
        t1.start();

        try {
            Thread.sleep(100);
            System.out.println("waiting lock : "+t1.getState());
            lock1.unlock();     //4

            Thread.sleep(100);
            System.out.println("waiting io : "+t1.getState());

            lock1.lock();   //7，等待6
            synchronized (lock1){
                lock2.unlock();     //9
                Thread.sleep(100);
                System.out.println("waiting synchronized : "+t1.getState());
            }
            lock1.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
