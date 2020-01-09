package com.zhangwei.javabase.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPauseDemo{
    public static void main(String[] args) throws InterruptedException {
        test01();
    }

    private static void test01() throws InterruptedException {
        Runnable task1 = createTask("task1");
        Runnable task2 = createTask("task2");

        ThreadPauseTool threadPauseTool = new ThreadPauseTool();
        new Thread(()->{
            try {
                threadPauseTool.doAction(task1);
                threadPauseTool.doAction(task2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(100);
        threadPauseTool.suspend();
        System.out.println("waiting........");
        threadPauseTool.resume();
    }

    private static Runnable createTask(String taskName){
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println(taskName+" start");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(taskName+" end");
            }
            @Override
            public String toString(){
                return taskName;
            }
        };
        return task;
    }
}

class ThreadPauseTool extends ReentrantLock {
    private boolean status = true;
    private Condition condition = newCondition();
    public void suspend(){
        status = false;
    }

    public void resume(){
        lock();
        try {
            status = true;
            condition.signalAll();
        } finally {
            unlock();
        }
    }

    public void doAction(Runnable task) throws InterruptedException {
        lock();
        try {
            while (!status){
                System.out.println("task paused...");
                condition.await();
            }
            task.run();
        } finally {
            unlock();
        }
    }
}
