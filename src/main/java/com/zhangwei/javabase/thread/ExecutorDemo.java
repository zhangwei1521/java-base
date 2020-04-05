package com.zhangwei.javabase.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //test01();
        //test02();
        //test03();
        //test04();
        test05();
    }

    public static void test01() throws InterruptedException {
        final AtomicInteger counter = new AtomicInteger(0);
        CountDownLatch latch = new CountDownLatch(1);
        Executor executor =  new ThreadPoolExecutor(4,8,5, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10));
        executor.execute(()-> {
            System.out.println("task done");
            counter.addAndGet(1);
            latch.countDown();
        });
        System.out.println("end");
        counter.addAndGet(1);
        //while (counter.get()!=2){}
        latch.await();
        System.exit(0);
    }

    public static void test02(){
        //60秒后销毁所有线程
        ExecutorService executorService =  Executors.newCachedThreadPool();
        executorService.submit(()-> System.out.println("task done"));
        System.out.println("end");
    }

    public static void test03(){
        ExecutorService executorService =  Executors.newFixedThreadPool(1);
        executorService =  Executors.newSingleThreadExecutor();
        Future future = executorService.submit(()-> System.out.println("task done"));
        //轮询任务是否执行结束，结束才手动关闭线程池
        while (!future.isDone()){}
        System.out.println("end");
        executorService.shutdown();
    }

    public static void test04() throws InterruptedException, ExecutionException {
        Executor executor = Executors.newCachedThreadPool();
        CompletionService<Integer> completionService = new ExecutorCompletionService(executor);
        ArrayList<Integer> list = new ArrayList<>(200);
        for(int i=0;i<100;i++){
            final int j = i;
            completionService.submit(()->{
                //System.out.println(Thread.currentThread().getName());
                //并发添加元素，可能会造成元素丢失
                list.add(j);
                return j;
            });
        }
        ArrayList<Integer> result = new ArrayList<>(200);
        for(int i=0;i<100;i++){
            //take方法会阻塞
            Future<Integer> future = completionService.take();
            result.add(future.get());
        }
        Collections.sort(result);
        Collections.sort(list);
        System.out.println(list);
        System.out.println(result);
    }

    public static void test05(){
        ExecutorService executorService =  Executors.newFixedThreadPool(1);
        FutureTask<String> futureTask = new FutureTask<String>(()->{
            System.out.println("done.........");
            return "hello";
        }){
            //这种回调的写法依赖了executorService实例的内部实现
            @Override
            protected void done(){
                if(!isCancelled()){
                    try {
                        String result = get();
                        System.out.println(result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        executorService.submit(futureTask);
    }
}
