package com.zhangwei.javabase.vmdemo;

import java.util.ArrayList;
import java.util.List;

public class RuntimeConstantPoolOOM {
	public static void main(String[] args) {
		/*String str1 = new StringBuilder("ja").append("va").toString();
		System.out.println(str1==str1.intern());
		
		String str2 = new StringBuilder("计算机").append("软件").toString();
		System.out.println(str2==str2.intern());*/
		
		//java -XX:PermSize=10M -XX:MaxPermSize=10M RuntimeConstantPoolOOM
		//jdk1.6及之前的虚拟机会发生oom，方法区内存溢出
		//jdk1.7开始String.intern方法不再将字符串常量复制保存到方法区，而是引用字符串对象在堆中的地址。
		List<String> list = new ArrayList<>();
		int i = 0;
		while(true) {
			list.add(String.valueOf(i++).intern());
		}
	}
}
