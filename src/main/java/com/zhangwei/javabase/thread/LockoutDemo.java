package com.zhangwei.javabase.thread;

/**
 * 锁死：一个线程一直等待被唤醒，但是由于某些原因，此线程一直没有被唤醒。
 */
public class LockoutDemo {

    public static void main(String[] args) {
        test01();
    }

    private static void test01(){
        new Thread(()->{
            try {
                //这里使用临时对象作为锁，将导致不能被其他线程通知，导致锁死
                Object lock = new Object();
                synchronized (lock){
                    lock.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    //嵌套监视器导致锁死
    private static void test02(){
        Object monitorX = new Object();
        Object monitorY = new Object();
        final boolean[] status = {false};
        new Thread(()->{
            synchronized(monitorX){
                synchronized(monitorY){
                    while(!status[0]){
                        // wait方法执行后会释放当前线程持有的监视器monitorY，
                        // 但是不会释放监视器monitorX
                        try {
                            monitorY.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 因为monitorX没有被释放，所以这里就会一直等待，
                // 导致执行monitorY.wait()的线程永远不会被唤醒。
                synchronized(monitorX){
                    synchronized(monitorY){
                        status[0] = true;
                        monitorY.notify();
                    }
                }
            }
        }).start();
    }
}
