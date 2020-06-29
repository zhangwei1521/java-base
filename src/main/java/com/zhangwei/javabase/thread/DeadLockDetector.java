package com.zhangwei.javabase.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class DeadLockDetector extends Thread{
    static final ThreadMXBean threadMxBean = ManagementFactory.getThreadMXBean();
    private final long timeInterval;

    public DeadLockDetector(long timeInterval){
        setName("DeadLockDetector");
        setDaemon(true);
        this.timeInterval = timeInterval;
    }
    public DeadLockDetector(){
        this(2000);
    }

    public static ThreadInfo[] findDeadLockThread(){
        long [] ids = threadMxBean.findDeadlockedThreads();
        return null==ids ? new ThreadInfo[0] : threadMxBean.getThreadInfo(ids);
    }

    public static Thread findThreadById(long threadId){
        for (Thread t : Thread.getAllStackTraces().keySet()){
            if(t.getId()==threadId){
                return t;
            }
        }
        return null;
    }

    public static boolean interruptThread(long threadId){
        Thread thread = findThreadById(threadId);
        if(thread!=null){
            thread.interrupt();
            return true;
        }
        return false;
    }

    public void run(){
        ThreadInfo [] threadInfos;
        int i=0;
        while (true){
            threadInfos = findDeadLockThread();
            if(threadInfos.length>0){
                System.out.println("detect dead lock!!!");
                interruptThread(threadInfos[i++ % threadInfos.length].getThreadId());
                continue;
            }
            i=0;
            try {
                Thread.sleep(timeInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
