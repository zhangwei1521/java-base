package com.zhangwei.javabase.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

	public static void main(String[] args) {
		test01();
	}

	private static void test01() {
		int parties = 5;
		// CyclicBarrier barrier = new CyclicBarrier(5);
		CyclicBarrier barrier = new CyclicBarrier(parties, new Runnable() {
			@Override
			public void run() {
				//最后一个到达的线程将执行这个任务
				System.out.println(Thread.currentThread().getName() + " everyone is here! are you ready?");
			}
		});
		int threads = parties;
		//threads = parties+1;
		for (int i = 0; i < threads; i++) {
			new Thread() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + " : Hello! ");
					try {
						//当第parties个线程执行await时，将唤醒前面的等待线程，同时重置CyclicBarrier为初始状态
						int val = barrier.await();
						System.out.println(Thread.currentThread().getName() + " get : " + val);
						System.out.println("done !");
					} catch (InterruptedException | BrokenBarrierException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
	}

}
