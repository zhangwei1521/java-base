package com.zhangwei.javabase.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CycliyBarrierDemo {

	public static void main(String[] args) {
		test01();
	}

	private static void test01() {
		// CyclicBarrier barrier = new CyclicBarrier(5);
		CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + " everyone is here! are you ready?");
			}
		});
		for (int i = 0; i < 5; i++) {
			new Thread() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + " : Hello! ");
					try {
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
