package com.zhangwei.javabase.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class SynchronizedCollectionDemo {
    public static void main(String[] args) {
        //test01();
        //test02();
        test03();
    }

    private static void test01(){
        //List<Integer> list = new ArrayList<>();
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        CyclicBarrier barrier = new CyclicBarrier(20);
        CountDownLatch latch = new CountDownLatch(20);
        for (int i=0;i<20;i++){
            final int j = i;
            new Thread(()->{
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                list.add(j);
                latch.countDown();
            }).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    private static void test02(){
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        for (int i=0;i<20;i++){
            final int j = i;
            new Thread(()->{
                list.add(j);
            }).start();
        }
        Iterator<Integer> iterator = list.iterator();
        synchronized (list){
            while (iterator.hasNext()){
                System.out.println(iterator.next());
            }
        }
    }

    private static void test03(){
        //CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        ConcurrentLinkedQueue<Integer> list = new ConcurrentLinkedQueue<>();
        for (int i=0;i<20;i++){
            final int j = i;
            new Thread(()->{
                list.add(j);
            }).start();
        }
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
