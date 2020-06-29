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
        //UnRealNoStateObj obj1 = new UnRealNoStateObj();
		String[] dateStrs = { "2018-12-31", "2018-12-30", "2018-12-29", "2018-12-28", "2018-12-27" };
		for (int i = 0; i < 5; i++) {
			int j = i;
			new Thread(() -> {
				Date date = obj1.parse(dateStrs[j]);
				System.out.println(Thread.currentThread().getName() + " get : " + date.toString());
			}).start();
		}
	}

}

class NoStateObj1 {
	public Date parse(String dateStr) {
		//SimpleDateFormat不是线程安全的
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return formater.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}

class UnRealNoStateObj {
    public Date parse(String dateStr) {
        return UnSafeFormater.INSTANCE.parse(dateStr);
    }
}

enum  UnSafeFormater {
    INSTANCE;

    private UnSafeFormater(){}

    private SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

    public Date parse(String dateStr) {
        try {
            return formater.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}