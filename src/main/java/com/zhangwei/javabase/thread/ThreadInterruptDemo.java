package com.zhangwei.javabase.thread;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadInterruptDemo {
    public static void main(String[] args) {
        //test01();
        //test02();
        //test03();
        test04();
    }

    private static void  test01(){
        Thread t1 = new Thread(()->{
            try(InputStream in = new FileInputStream("/tem_file/files/app.js");){
                int c;
                int i = 0;
                while ((c = in.read()) != -1){
                    if(Thread.currentThread().isInterrupted()){
                        i++;
                        System.out.println("get interrupted indicator");
                        if(i==2){
                            Thread.interrupted();
                        }
                    }
                }
                System.out.println("success!");
            } catch (Exception e){
                e.printStackTrace();
            }
        });
        t1.start();
        System.out.println("before");
        t1.interrupt();
        System.out.println("after");
    }

    private static void  test02(){
        Thread t1 = new Thread(()->{
            step1();
            step2();
        });
        t1.start();
        System.out.println("before");
        try {
            Thread.sleep(5);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        t1.interrupt();
        System.out.println("after");
    }

    private static void  step1(){
        System.out.println("step1....start....");
        while (true){
            if(Thread.currentThread().isInterrupted()){
                System.out.println("step1....canceled....");
                break;
            }
            System.out.println("step1....doing....");
        }
    }

    private static void  step2(){
        System.out.println("step2。。。。start。。。。");
        if(Thread.currentThread().isInterrupted()){
            System.out.println("step2。。。。continue。。。。");
        }
        System.out.println("step2。。。。finish。。。。");
    }

    private static void  test03(){
        Thread t1 = new Thread(()->{
            int i=0;
            while (i++ < 500){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("thread....stop....");
                    return;
                }
                System.out.println("....step1 doing....");
            }
            System.out.println(".....other work......");
            i=0;
            while (i++ < 10000){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("thread....stop....");
                    return;
                }
                System.out.println("....step2 doing....");
            }
        });
        t1.start();
        System.out.println("before");
        try {
            Thread.sleep(5);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        t1.interrupt();
        System.out.println("after");
    }

    private static void  test04(){
        Thread t1 = new Thread(()->{
            ReentrantLock lock = new ReentrantLock();
            while (true){
                System.out.println("now : "+System.currentTimeMillis());
                try {
                    //Thread.sleep(5);
                    lock.lockInterruptibly();
                    lock.unlock();
                } catch (InterruptedException e){
                    e.printStackTrace();
                    lock.unlock();
                    break;
                }
            }
        });
        t1.start();
        System.out.println("before");
        try {
            Thread.sleep(5);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        t1.interrupt();
        System.out.println("after");
    }
}
