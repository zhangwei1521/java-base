package com.zhangwei.javabase.thread;

public class ThreadStopDemo {
    public static void main(String[] args) {
        test01();
    }

    private static void test01(){
        PureTask task = new PureTask();
        Thread t1 = new Thread(task);
        t1.start();
        try {
            Thread.sleep(5);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        task.stop();
    }
}

class PureTask implements Runnable{
    private boolean proceed = true;

    public void stop(){
        proceed = false;
        Thread.currentThread().interrupt();
    }

    @Override
    public void run() {
        int i=0;
        while (i<10000){
            if(!proceed){
                System.out.println("found interrupted, return");
                return;
            }
            //这个方法可能会清除中断标记，但是不抛出异常
            doSomething();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
                return;
            }
        }
    }

    private void doSomething(){
        if(Thread.interrupted()){
            System.out.println("found interrupted, do nothing");
        }
    }
}
