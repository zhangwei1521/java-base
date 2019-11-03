package com.zhangwei.javabase.vmdemo;

import java.util.ArrayList;
import java.util.List;

public class HeapOOMDemo {
	static class MyObject {}
	
	public static void main(String[] args) {
		List<MyObject> list = new ArrayList<>();
		while(true) {
			list.add(new MyObject());
		}
	}
}
