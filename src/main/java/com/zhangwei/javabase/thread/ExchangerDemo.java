package com.zhangwei.javabase.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class ExchangerDemo {

	public static void main(String[] args) {
		test01();
	}

	private static void test01() {
		Exchanger<List<String>> exchanger = new Exchanger<>();
		new Thread() {
			@Override
			public void run() {
				List<String> buf1 = new ArrayList<String>();
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 3; j++) {
						buf1.add(i + "-" + j);
					}
					try {
						//exchange将阻塞等待直到有其他线程也执行了exchange方法
						//Exchanger就像一个一手交钱一手交货的市场
						List<String> ll = exchanger.exchange(buf1);
						System.out.println(Thread.currentThread().getName() + " get : " + ll);
						buf1 = new ArrayList<String>();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("===============");
				}
			}
		}.start();

		new Thread() {
			@Override
			public void run() {
				List<String> buf1 = new ArrayList<String>();
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 3; j++) {
						buf1.add(i + "=" + j);
					}
					try {
						List<String> ll = exchanger.exchange(buf1);
						System.out.println(Thread.currentThread().getName() + " get : " + ll);
						buf1 = new ArrayList<String>();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("************");
				}
			}
		}.start();
	}

}
