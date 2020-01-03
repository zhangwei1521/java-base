package com.zhangwei.javabase.thread;

import java.util.HashMap;
import java.util.Map;

public class TaskOwnObjDemo {
    public static ThreadLocal<Map<String,Object>> tmap = new ThreadLocal(){
        @Override
        protected Map<String,Object> initialValue(){
            return new HashMap<>();
        }
    };

    public static void main(String[] args) {
        //test01();
        test02();
    }

    private static void test02(){
        Thread t1 = new Thread(()->{
            Map<String,Object> map = tmap.get();
            tmap = null;
        });
        t1.start();
        try {
            t1.join();
            if(tmap==null){
                System.out.println("tmap is null");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void test01(){
       for (int i=0;i<5;i++){
           new Thread(()->{
               service1();
               service2();
           }).start();
       }
    }

    private static void service1(){
        tmap.get().put(Thread.currentThread().getName(),"service1");
    }

    private static void service2(){
        System.out.println(tmap.get().get(Thread.currentThread().getName()));
        //如果使用ThreadLocal实例来实现任务私有的对象，在任务开始前需要先清空ThreadLocal绑定的线程特有对象的状态
        tmap.remove();
        tmap.get().put(Thread.currentThread().getName(),"service2");
        Object val = tmap.get().get(Thread.currentThread().getName());
        System.out.println(val);
    }
}
