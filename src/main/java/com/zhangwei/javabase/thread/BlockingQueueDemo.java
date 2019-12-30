package com.zhangwei.javabase.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

public class BlockingQueueDemo {

	public static void main(String[] args) {
		// test01();
		// test02();
		// test03();
		// test04();
		test05();
	}

	private static void test01() {
		BlockingQueue<String> channel = new ArrayBlockingQueue<String>(2);
		// BlockingQueue<String> channel = new LinkedBlockingQueue<String>(2);
		for (int i = 0; i < 5; i++) {
			new Thread() {
				@Override
				public void run() {
					try {
						channel.put(Thread.currentThread().getName());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}

		for (int i = 0; i < 5; i++) {
			/*
			 * try { System.out.println(channel.take()); } catch (InterruptedException e) {
			 * e.printStackTrace(); }
			 */
			new Thread() {
				@Override
				public void run() {
					try {
						System.out.println(channel.take());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}

		/*
		 * try { System.out.println(channel.take()); } catch (InterruptedException e) {
		 * e.printStackTrace(); }
		 */
	}

	private static void test02() {
		BlockingQueue<String> channel = new SynchronousQueue<String>();
		try {
			for (int i = 0; i < 2; i++) {
				new Thread() {
					@Override
					public void run() {
						try {
							System.out.println(channel.take());
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}.start();
			}
			new Thread() {
				@Override
				public void run() {
					try {
						channel.put(Thread.currentThread().getName());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}.start();
			channel.put(Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void test03() {
		BlockingQueue<String> channel = new ArrayBlockingQueue<>(5);
		// BlockingQueue<String> channel = new SynchronousQueue<String>();
		System.out.println(channel.offer("hello"));
		System.out.println(channel.poll());
	}

	private static void test04() {
		BlockingQueue<String> channel = new LinkedBlockingQueue<String>();
		Semaphore semaphore = new Semaphore(2);
		for (int i = 0; i < 9; i++) {
			new Thread() {
				@Override
				public void run() {
					try {
						semaphore.acquire();
						channel.put(Thread.currentThread().getName());
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						if (channel.size() <= 6) {
							semaphore.release();
						}
					}
				}
			}.start();
		}
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(channel.size());
		while (channel.size() >= 3) {
			String[] objs = new String[3];
			for (int i = 0; i < 3; i++) {
				try {
					objs[i] = channel.take();
					semaphore.release();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(objs[0] + "\n" + objs[1] + "\n" + objs[2]);
		}
		System.out.println(channel.size());
	}

	private static void test05() {
		Semaphore semaphore = new Semaphore(1);
		semaphore.release();
		try {
			semaphore.acquire();
			System.out.println("acquire success");
			semaphore.acquire();
			System.out.println("acquire again success");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
