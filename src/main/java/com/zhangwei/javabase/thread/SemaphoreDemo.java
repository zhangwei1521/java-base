package com.zhangwei.javabase.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    public static void main(String[] args) {
        //test01();
        test02();
        //test03();
    }

    private static void test01() {
        Semaphore semaphore = new Semaphore(1);
        semaphore.release();
        try {
            semaphore.acquire();
            System.out.println("acquire success");
            semaphore.acquire();
            System.out.println("acquire again success");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void test02() {
        Semaphore semaphore = new Semaphore(1);
        try {
            semaphore.acquire();
            System.out.println("acquire success");
            //boolean result = semaphore.tryAcquire();
            boolean result = semaphore.tryAcquire(100, TimeUnit.MILLISECONDS);
            if(result){
                System.out.println("tryAcquire success");
            }
            else {
                System.out.println("tryAcquire fail");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void test03() {
        BlockingQueue<String> channel = new LinkedBlockingQueue<String>();
        int semaphorePermits = 2;
        Semaphore semaphore = new Semaphore(semaphorePermits);
        int total = 9;
        int threshold = 8;
        for (int i = 0; i < total; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName()+" put in");
                        channel.put(Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        //如果限制队列最大数据量，需要使用 threshold-semaphorePermits
                        if (channel.size() <= (threshold-semaphorePermits)) {
                            semaphore.release();
                        }
                    }
                }
            }.start();
        }
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //队列当前数据量=threshold
        System.out.println("channel size : "+channel.size());
        System.out.println("waiting threads : "+semaphore.getQueueLength());
        System.out.println("left semaphore permits : "+semaphore.availablePermits());
        while (channel.size() > 0) {
            try {
                System.out.println("take out : "+channel.take());
                if(semaphore.availablePermits() < semaphorePermits){
                    semaphore.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("waiting threads : "+semaphore.getQueueLength());
        System.out.println("left semaphore permits : "+semaphore.availablePermits());
    }

}
