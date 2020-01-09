package com.zhangwei.javabase.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadExceptionHandleDemo {
    volatile boolean inited = false;
    static AtomicInteger threadIndex = new AtomicInteger(0);
    final BlockingQueue<String> channel = new ArrayBlockingQueue<>(100);

    public static void main(String[] args) {
        ThreadExceptionHandleDemo demo = new ThreadExceptionHandleDemo();
        demo.init();
        for(int i=0;i<100;i++){
            demo.sendMsg("msg"+i);
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    private void sendMsg(String msg){
        channel.add(msg);
    }

    private synchronized void init(){
        if(inited){
            return;
        }
        CustomerThread customerThread = new CustomerThread();
        customerThread.setName("customer-"+threadIndex.getAndAdd(1));
        customerThread.setUncaughtExceptionHandler(new CustomerThreadExceptionHandler());
        customerThread.start();
        inited = true;
    }

    private class CustomerThread extends Thread{
        @Override
        public void run(){
            String msg=null;
            for (;;){
                try {
                    msg = channel.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                process(msg);
            }
        }

        private void process(String msg) {
            if((int)(Math.random()*100)<10){
                throw new RuntimeException("random exception");
            }
            System.out.println(msg);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class CustomerThreadExceptionHandler implements Thread.UncaughtExceptionHandler{
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            String tname = t.getName();
            System.err.println(tname+" terminated : "+e);
            inited = false;
            init();
        }
    }
}
