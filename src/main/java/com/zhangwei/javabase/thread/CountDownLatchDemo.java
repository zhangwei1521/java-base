package com.zhangwei.javabase.thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

	private boolean status1 = false;
	private boolean status2 = false;
	private boolean status3 = false;

	public static void main(String[] args) throws InterruptedException {
		 test01();
		//test02();
	}

	private static void test01() throws InterruptedException {
		CountDownLatchDemo demo = new CountDownLatchDemo();
		CountDownLatch latch = new CountDownLatch(5);
		demo.doSomething1(latch);
		latch.await();
		System.out.println("start..." + latch.getCount());
	}

	private void doSomething1(CountDownLatch latch) {
		for (int i = 1; i < 8; i++) {
			new Thread() {
				@Override
				public void run() {
					try {
						System.out.println(Thread.currentThread().getName() + " : " + latch.getCount());
					} finally {
						//latch减到0以后就不会再减1，所以不会为出现负数
						latch.countDown();
					}
				}
			}.start();
		}
	}

	private static void test02() throws InterruptedException {
		CountDownLatchDemo demo = new CountDownLatchDemo();
		CountDownLatch latch = new CountDownLatch(2);
		demo.doSomething2(latch);
		latch.await();
		System.out.println("start..." + latch.getCount());
		System.out.println("status1 is true ? " + demo.status1);
		System.out.println("status2 is true ? " + demo.status2);
		System.out.println("status3 : " + demo.status3);
	}

	private void doSomething2(CountDownLatch latch) {
		new Thread() {
			@Override
			public void run() {
				step1();
				latch.countDown();
				step2();
				latch.countDown();
				step3();
				step4();
			}
		}.start();
		new Thread() {
			@Override
			public void run() {
				step2();
				latch.countDown();
				step4();
			}
		}.start();
	}

	private void step1() {
		System.out.println("step1...");
		status1 = true;
	}

	private void step2() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("step2...");
		status2 = true;
	}

	private void step3() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("step3...");
		status3 = true;
	}

	private void step4() {
		System.out.println("step4...");
	}
}
