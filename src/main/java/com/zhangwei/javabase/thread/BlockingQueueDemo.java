package com.zhangwei.javabase.thread;

import java.util.concurrent.*;

public class BlockingQueueDemo {

	public static void main(String[] args) {
		//test01();
		test02();
	}

	private static void test01() {
		BlockingQueue<String> channel = new ArrayBlockingQueue<String>(2);
		// BlockingQueue<String> channel = new LinkedBlockingQueue<String>(2);
		for (int i = 0; i < 5; i++) {
			new Thread() {
				@Override
				public void run() {
					try {
						//当队列已满时 put 方法会阻塞
						channel.put(Thread.currentThread().getName());
						//当队列已满时 add、offer 方法会抛出异常
						//channel.add(Thread.currentThread().getName());
						//channel.offer(Thread.currentThread().getName());
						//队列已满时将等待50毫秒，如果队列还是满的就抛出异常
						//channel.offer(Thread.currentThread().getName(),50,TimeUnit.MICROSECONDS);
						//队列剩余的空间
						int remainingCapacity = channel.remainingCapacity();

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
						//当队列为空时，take 方法将返回阻塞
						String str = channel.take();
						System.out.println(str);
						//当队列为空时，poll 方法将返回null
						//str = channel.poll();
						//当队列为空时，等待50毫秒后将返回null
						//str = channel.poll(50,TimeUnit.MILLISECONDS);
						//element 方法只返回队列头元素不删除，队列为空抛出异常
						//str = channel.element();
						//peek 方法只返回队列头元素不删除，队列为空返回null
						//str = channel.peek();
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
		//SynchronousQueue的容量为 1
		BlockingQueue<String> channel = new SynchronousQueue<String>();
		try {
			for (int i = 0; i < 2; i++) {
				new Thread() {
					@Override
					public void run() {
						try {
							String str = channel.take();
							System.out.println(Thread.currentThread().getName()+" take out : "+str);
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
						System.out.println(Thread.currentThread().getName()+" put in : hello");
						//如果队列不为空， put 方法将阻塞
						channel.put("hello");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}.start();
			System.out.println(Thread.currentThread().getName()+" put in : SynchronousQueue");
			//如果队列不为空， put 方法将阻塞
			channel.put("SynchronousQueue");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
