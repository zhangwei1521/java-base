package com.zhangwei.javabase.thread;

import java.util.Date;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {

	private Lock lock = new ReentrantLock();

	private Condition cond1 = lock.newCondition();

	private Condition cond2 = lock.newCondition();

	private boolean status1 = false;

	private boolean status2 = false;

	public static void main(String[] args) throws InterruptedException {
		// test01();
		test02();
	}

	// 测试Condition的过早唤醒问题解决方式
	private static void test01() throws InterruptedException {
		ConditionDemo demo = new ConditionDemo();
		for (int i = 1; i < 5; i++) {
			final int j = i;
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						demo.doSomething1(j % 2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			thread.setName("thread-" + i);
			thread.start();
		}
		// Thread.sleep(500);
		demo.doUpdateStatus(1);
		Thread.sleep(500);
		demo.doUpdateStatus(0);
	}

	private void doSomething1(int sign) throws InterruptedException {
		lock.lock();
		try {
			if (sign == 0) {
				while (!status1) {
					System.out.println(Thread.currentThread().getName() + " wait status1");
					cond1.await();
				}
			}
			if (sign == 1) {
				while (!status2) {
					System.out.println(Thread.currentThread().getName() + " wait status2");
					cond2.await();
				}
			}
			doNow();
		} finally {
			lock.unlock();
		}
	}

	private void doNow() {
		System.out.println("------doNow start!!!------");
		System.out.println("thread: " + Thread.currentThread().getName());
		System.out.println("status1 = " + status1);
		System.out.println("status2 = " + status2);
		System.out.println("------doNow end!!!!!------");
	}

	private void doUpdateStatus(int sign) {
		lock.lock();
		try {
			if (sign == 0) {
				status1 = true;
				cond1.signalAll();
			}
			if (sign == 1) {
				status2 = true;
				cond2.signalAll();
			}
		} finally {
			lock.unlock();
		}
	}

	// 测试Condition的判断超时等待解决方式
	private static void test02() throws InterruptedException {
		ConditionDemo demo = new ConditionDemo();
		for (int i = 1; i < 3; i++) {
			final int j = i;
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						demo.doSomething2(j * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			thread.setName("thread-" + i);
			thread.start();
		}
		Thread.sleep(1200);
		demo.doUpdateStatus(0);
	}

	private void doSomething2(int timeToWait) throws InterruptedException {
		Date deadline = new Date(System.currentTimeMillis() + timeToWait);
		lock.lock();
		try {
			while (!status1) {
				boolean getSignal = cond1.awaitUntil(deadline);
				if (!getSignal) {
					System.out.println(Thread.currentThread().getName() + " timeout....");
					return;
				}
			}
			doNow();
		} finally {
			System.out.println("finally do....");
			lock.unlock();
		}
	}
}
