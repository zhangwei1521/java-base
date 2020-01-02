package com.zhangwei.javabase.thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadLocalDemo {

    private static Map<String,String> map = new HashMap<>();

    private static ThreadLocal<String> tl1 = new ThreadLocal<>();

    //这样就不需要在每个方法中去创建SimpleDateFormat对象，且是线程安全的
    private static ThreadLocal<SimpleDateFormat> tl2 = ThreadLocal.withInitial(()->new SimpleDateFormat("yyyy-MM-dd"));

    public static void main(String[] args) {
        //test01();
        //test02();
        test03();
    }

    private static void test01(){
        CyclicBarrier barrier = new CyclicBarrier(100);
        for (int i=0;i<100;i++){
            new Thread(()->{
                //doSomething1(barrier);
                doSomething2(barrier);
            }).start();
        }
    }

    private static void test02(){
        String[] dateStrs = { "2018-12-31", "2018-12-30", "2018-12-29", "2018-12-28", "2018-12-27" };
        CyclicBarrier barrier = new CyclicBarrier(5);
        for (int i=0;i<5;i++){
            final int j = i;
            new Thread(()->{
                Date date = parseDate(dateStrs[j],barrier);
                System.out.println(Thread.currentThread().getName() + " get : " + date);
            }).start();
        }
    }

    private static void test03(){
        Random random = new Random();
        random.setSeed(12);
        for (int i=0;i<100;i++){
            new Thread(()->{
                //System.out.println(Thread.currentThread().getName()+" get: "+ random.nextInt(10));
                System.out.println(Thread.currentThread().getName()+" get: "+ ThreadLocalRandom.current().nextInt());
            }).start();
        }
    }

    private static void doSomething1(CyclicBarrier barrier){
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e){
            e.printStackTrace();
        }
        //使用map，线程不安全
        String str1 = map.get(Thread.currentThread().getName());
        if(str1==null){
            map.put(Thread.currentThread().getName(),Thread.currentThread().getName());
        }
        System.out.println("a: "+Thread.currentThread().getName()+"\tb:"+map.get(Thread.currentThread().getName()));
    }
    private static void doSomething2(CyclicBarrier barrier){
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e){
            e.printStackTrace();
        }
        //使用ThreadLocal
        String str2 = tl1.get();
        if(str2==null){
            tl1.set(Thread.currentThread().getName());
        }
        System.out.println("1: "+Thread.currentThread().getName()+"\t2: "+tl1.get());
    }
    private static Date parseDate(String dateStr,CyclicBarrier barrier){
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e){
            e.printStackTrace();
        }
        try {
            return tl2.get().parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
