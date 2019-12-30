package com.zhangwei.javabase.thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NoStateObjDemo {

	public static void main(String[] args) {
		test01();
	}

	private static void test01() {
		NoStateObj1 obj1 = new NoStateObj1();
		String[] dateStrs = { "2018-12-31", "2018-12-30", "2018-12-29", "2018-12-28", "2018-12-27" };
		for (int i = 0; i < 5; i++) {
			int j = i;
			new Thread(() -> {
				Date date = obj1.test(dateStrs[j]);
				System.out.println(Thread.currentThread().getName() + " get : " + date.toString());
			}).start();
		}
	}

}

class NoStateObj1 {
	private SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

	public Date test(String dateStr) {
		try {
			return formater.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}