package com.zhangwei.javabase.vmdemo;

import java.util.ArrayList;
import java.util.List;

public class RuntimeConstantPoolOOM {
	public static void main(String[] args) {
		/*String str1 = new StringBuilder("ja").append("va").toString();
		System.out.println(str1==str1.intern());
		
		String str2 = new StringBuilder("�����").append("���").toString();
		System.out.println(str2==str2.intern());*/
		
		//java -XX:PermSize=10M -XX:MaxPermSize=10M RuntimeConstantPoolOOM
		//jdk1.6��֮ǰ��������ᷢ��oom���������ڴ����
		//jdk1.7��ʼString.intern�������ٽ��ַ����������Ʊ��浽�����������������ַ��������ڶ��еĵ�ַ��
		List<String> list = new ArrayList<>();
		int i = 0;
		while(true) {
			list.add(String.valueOf(i++).intern());
		}
	}
}
