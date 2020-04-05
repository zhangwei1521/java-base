package com.zhangwei.javabase.thread;

import java.util.Calendar;
import java.util.concurrent.*;

public class ScheduledExecutorDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //test01();
        //test02();
        test03();
    }

    private static void test01() throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(5);
        ScheduledFuture<String> future = scheduledExecutor.schedule(()->{
            System.out.println("hello");
            return "success";
        },5, TimeUnit.SECONDS);
        System.out.println("==========");
        String result = future.get();
        System.out.println("result : "+result);
        scheduledExecutor.shutdown();
    }

    private static void test02() throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(5);
        System.out.println("hello : "+ Calendar.getInstance().getTime().getSeconds());
        ScheduledFuture future = scheduledExecutor.scheduleAtFixedRate(()->{
            System.out.println("hello : "+ Calendar.getInstance().getTime().getSeconds());
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },5, 5,TimeUnit.SECONDS);

    }

    private static void test03() throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(5);
        System.out.println("hello : "+ Calendar.getInstance().getTime().getSeconds());
        ScheduledFuture future = scheduledExecutor.scheduleWithFixedDelay(()->{
            System.out.println("hello : "+ Calendar.getInstance().getTime().getSeconds());
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },5, 5,TimeUnit.SECONDS);
    }
}
