package com.zhangwei.javabase.thread;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class PhilosopherEatingDemo {
    public static void main(String[] args) {
        test01();
    }

    private static void test01(){
        Chopstick[] chopsticks = new Chopstick[5];
        Philosopher[] philosophers = new Philosopher[5];
        for(int i=0;i<5;i++){
            chopsticks[i] = new Chopstick();
        }
        for(int i=0;i<5;i++){
            //philosophers[i] = new SimplePhilosopher(chopsticks[i],chopsticks[(i+1)%5]);
            //philosophers[i] = new FixedPhilosopher(chopsticks[i],chopsticks[(i+1)%5]);
            philosophers[i] = new NewPhilosopher(chopsticks[i],chopsticks[(i+1)%5]);
        }
        for(int i=0;i<5;i++){
            final int j = i;
            new Thread(()->{
                while (true){
                    philosophers[j].eat();
                    philosophers[j].think();
                }
            }).start();
        }
        //new DeadLockDetector().start();
    }
}

interface Philosopher{
    void think();
    void eat();
}

class AbstractPhilosopher implements Philosopher{
    public void think(){
        String threadName = Thread.currentThread().getName();
        long threadId = Thread.currentThread().getId();
        //System.out.println(Thread.currentThread().getName()+" : thinking....");
    }
    public void eat(){}
}

class SimplePhilosopher extends AbstractPhilosopher{
    private Chopstick left;
    private Chopstick right;

    private static final Object lockObj = new Object();

    public SimplePhilosopher(Chopstick l,Chopstick r){
        this.left = l;
        this.right = r;
    }

    public void eat(){
        doEat1();
        //doEat2();
    }
    private void doEat1(){
        synchronized (left){
            left.pickUp();
            synchronized (right){
                right.pickUp();
                //System.out.println(Thread.currentThread().getName()+" : eating....");
                right.putDown();
            }
            left.putDown();
        }
    }
    private void doEat2(){
        synchronized (lockObj){
            left.pickUp();
            right.pickUp();
            //System.out.println(Thread.currentThread().getName()+" : eating....");
            right.putDown();
            left.putDown();
        }
    }
}

class Chopstick{
    private String status = "DOWN";

    public void pickUp(){
        status = "UP";
    }

    public void putDown(){
        status = "DOWN";
    }
}

class FixedPhilosopher extends AbstractPhilosopher{
    private Chopstick one;
    private Chopstick theOther;
    private static final Object lockObj = new Object();

    public FixedPhilosopher(Chopstick l,Chopstick r){
        int lh = System.identityHashCode(l);
        int rh = System.identityHashCode(r);
        if(lh<rh){
            this.one = l;
            this.theOther = r;
        }
        else if(lh>rh){
            this.one = r;
            this.theOther = l;
        }
        else {
            this.one = null;
            this.theOther = null;
        }
    }
    public void eat(){
        if(one!=null){
            synchronized (one){
                one.pickUp();
                synchronized (theOther){
                    theOther.pickUp();
                    //System.out.println(Thread.currentThread().getName()+" : eating....");
                    theOther.putDown();
                }
                one.putDown();
            }
        }
        else {
            synchronized (lockObj){
                one.pickUp();
                theOther.pickUp();
                //System.out.println(Thread.currentThread().getName()+" : eating....");
                theOther.putDown();
                one.putDown();
            }
        }
    }
}

class NewPhilosopher extends AbstractPhilosopher{
    private Chopstick left;
    private Chopstick right;
    private static final ConcurrentHashMap<Chopstick, ReentrantLock> LOCK_MAP;
    static {
        LOCK_MAP = new ConcurrentHashMap<>();
    }
    public NewPhilosopher(Chopstick left,Chopstick right){
        this.left = left;
        this.right = right;
        LOCK_MAP.putIfAbsent(left,new ReentrantLock());
        LOCK_MAP.putIfAbsent(right,new ReentrantLock());
    }

    public void eat(){
        doEat1();
        //doEat2();
    }

    private void doEat1(){
        final ReentrantLock leftLock = LOCK_MAP.get(left);
        final ReentrantLock rightLock = LOCK_MAP.get(right);
        try {
            boolean accessFlag = leftLock.tryLock(3, TimeUnit.SECONDS);
            if(!accessFlag){
                System.err.println("timeout for wait eating!");
                return;
            }
            left.pickUp();
            accessFlag = rightLock.tryLock(3, TimeUnit.SECONDS);
            if(!accessFlag){
                System.err.println("timeout for wait eating!");
                return;
            }
            right.pickUp();
            //....
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(rightLock.isHeldByCurrentThread()){
                right.putDown();
                rightLock.unlock();
            }
            if(leftLock.isHeldByCurrentThread()){
                left.putDown();
                leftLock.unlock();
            }
        }
    }

    private void doEat2(){
        try {
            if(pickUpChopstick(left) && pickUpChopstick(right)){
                //....
                left.putDown();
                right.putDown();
            }
        } finally {
            if(LOCK_MAP.get(right).isHeldByCurrentThread()){
                LOCK_MAP.get(right).unlock();
            }
            if(LOCK_MAP.get(left).isHeldByCurrentThread()){
                LOCK_MAP.get(left).unlock();
            }
        }
    }

    private boolean pickUpChopstick(Chopstick chopstick){
        final ReentrantLock lock = LOCK_MAP.get(chopstick);
        try {
            lock.lockInterruptibly();
        } catch (InterruptedException e){
            e.printStackTrace();
            if(LOCK_MAP.get(right).isHeldByCurrentThread()){
                LOCK_MAP.get(right).unlock();
            }
            if(LOCK_MAP.get(left).isHeldByCurrentThread()){
                LOCK_MAP.get(left).unlock();
            }
            return false;
        }
        chopstick.pickUp();
        return true;
    }
}
