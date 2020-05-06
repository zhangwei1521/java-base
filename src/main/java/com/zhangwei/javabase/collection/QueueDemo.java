package com.zhangwei.javabase.collection;

import java.util.LinkedList;
import java.util.Queue;

public class QueueDemo {
    public static void main(String[] args) {
        test01();
    }

    private static void test01() {
        Queue<Integer> queue = new LinkedList();
        for(int i=0; i<10; i++){
            queue.add(i);
        }
        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }
    }
}
