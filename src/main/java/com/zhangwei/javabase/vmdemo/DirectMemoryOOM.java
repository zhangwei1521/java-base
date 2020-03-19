package com.zhangwei.javabase.vmdemo;

import java.lang.reflect.Field;

//import sun.misc.Unsafe;

public class DirectMemoryOOM {
	
	private static final int _1M = 1024*1024;
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		/*Field unsafeField = Unsafe.class.getDeclaredFields()[0];
		unsafeField.setAccessible(true);
		Unsafe unsafe = (Unsafe)unsafeField.get(null);
		while(true) {
			unsafe.allocateMemory(_1M);
		}*/
	}

}
