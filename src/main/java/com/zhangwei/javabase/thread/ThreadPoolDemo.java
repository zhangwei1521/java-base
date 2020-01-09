package com.zhangwei.javabase.thread;

import java.sql.Time;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        //test01();
        //test02();
        test03();
    }

    private static void test01(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4,8,5, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new RejectedExecutionHandler(){
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println(Thread.currentThread().getName()+" : "+r);
                    }
                });
        //executor.prestartAllCoreThreads();
        //getActiveCount方法并不靠谱
        System.out.println("initial thread count : "+executor.getPoolSize());

        for (int i=0;i<19;i++){
            final int j = i;
            executor.submit(()->{
                System.out.println("hello "+j);
                try {
                    Thread.sleep(j*4);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
            System.out.println("=================");
            System.out.println("thread count : "+executor.getPoolSize());
            System.out.println("active count : "+executor.getActiveCount());
            System.out.println("task count : "+executor.getTaskCount());
            System.out.println("completed task count : "+executor.getCompletedTaskCount());
            System.out.println("queue size : "+executor.getQueue().size());
            System.out.println("=================");
        }
        //executor.shutdown();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("final thread count : "+executor.getPoolSize());
        System.out.println("largest pool size : "+executor.getLargestPoolSize());
        executor.shutdownNow();
    }

    private static void test02(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4,8,5, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10));
        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(100);
                return "sucess";
            }
        });
        //future = executor.submit(Executors.callable(()->{},"success"));
        //future.cancel(true);
        try {
            if(!future.isCancelled()){
                //String result = future.get();
                String result = future.get(20, TimeUnit.MILLISECONDS);
                System.out.println(result);
            }
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            if(e instanceof TimeoutException){
                future.cancel(true);
                System.out.println("task canceled");
            }
            else {
                e.printStackTrace();
            }
        }
        executor.shutdown();
    }

    private static void test03(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4,8,5, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10)){
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println(t.getName()+" will execute "+r);
            }
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println(r+" execute finished"+(t==null ? "" : ", exception happended : "+t.getMessage()));
            }
        };
        Future future = executor.submit(new Runnable() {
            @Override
            public void run()  {
                int i = 20/0;
                System.out.println(Thread.currentThread().getName()+" doing");
            }
        });
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdown();

    }
}
