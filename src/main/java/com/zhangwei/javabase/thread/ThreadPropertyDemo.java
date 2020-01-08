package com.zhangwei.javabase.thread;

public class ThreadPropertyDemo {

	public static void main(String[] args) {
		Thread mainThread = Thread.currentThread();
		long tid = mainThread.getId();
		String tname = mainThread.getName();
		String ttype = mainThread.isDaemon() ? "daemon-thread" : "user-thread";
		int tpriority = mainThread.getPriority();
		Thread.State tstate = mainThread.getState();

		System.out.println("tid : " + tid);
		System.out.println("tname : " + tname);
		System.out.println("ttype : " + ttype);
		System.out.println("tpriority : " + tpriority);
		System.out.println("tstate : " + tstate);

		Thread thread1 = new Thread();
		Thread.State state1 = thread1.getState();
		System.out.println("state1 : " + state1);
	}

}
