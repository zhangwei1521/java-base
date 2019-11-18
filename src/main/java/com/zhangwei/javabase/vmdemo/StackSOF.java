package com.zhangwei.javabase.vmdemo;

public class StackSOF {

	private int count = 0;
	
	public void stackLeak() {
		count++;
		stackLeak();
	}
	
	// -Xss128k
	public static void main(String[] args) {
		StackSOF demo = new StackSOF();
		try {
			demo.stackLeak();
		} catch(Throwable e) {
			System.out.println("stack length: "+demo.count);
			throw e;
		}

	}

}
