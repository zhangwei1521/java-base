package com.zhangwei.javabase.date;

import java.util.Calendar;
import java.util.Date;

public class OldDateTimeDemo {
    public static void main(String[] args) {
        test01();
        //test02();
        //test03();
    }

    //Date中的大部分API都不建议再使用了
    private static void test01() {
        Date d1 = new Date();
        System.out.println(d1);
        System.out.println(d1.getTime());
        System.out.println(System.currentTimeMillis());
        System.out.println(""+(d1.getYear()+1900)+(d1.getMonth()+1)+d1.getDate()+" "+d1.getHours()+d1.getMinutes()+d1.getSeconds());
    }

    private static void test02() {
        Calendar c1 = Calendar.getInstance();
        System.out.println(c1);
        c1.set(2019,0,8,8,0,0);
        Calendar c2 = Calendar.getInstance();
        c2.set(2019,0,8,18,0,0);
        System.out.println("c2: "+c2.getTime());
        System.out.println("c1: "+c1.getTime());
        double hours = (c2.getTime().getTime()-c1.getTime().getTime())/(1000*60*60);
        System.out.println("hours: "+hours);
        System.out.println("days: "+hours/24);
    }

    //月份为0~11，设置date和month超出时按日历向后推进
    private static void test03(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,2021);
        calendar.set(Calendar.MONTH,12);
        calendar.set(Calendar.DAY_OF_MONTH,32);
        String dateStr = calendar.get(Calendar.YEAR)+"年"+(calendar.get(Calendar.MONTH)+1)+"月"+calendar.get(Calendar.DAY_OF_MONTH)+"日";
        System.out.println(dateStr);
    }
}
