package com.zhangwei.javabase.date;

import java.util.Calendar;

public class CalendarDemo {
    public static void main(String[] args) {
        //test01();
        test02();
    }

    public static void test01(){
        Calendar c1 = Calendar.getInstance();
        c1.set(2019,0,8,8,0,0);
        Calendar c2 = Calendar.getInstance();
        c2.set(2019,0,8,18,0,0);
        System.out.println("c2: "+c2.getTime());
        System.out.println("c1: "+c1.getTime());
        double hours = (c2.getTime().getTime()-c1.getTime().getTime())/(1000*60*60);
        System.out.println("hours: "+hours);
        System.out.println("days: "+hours/24);
    }

    private static void test02(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,2021);
        calendar.set(Calendar.MONTH,12);
        calendar.set(Calendar.DAY_OF_MONTH,32);
        String dateStr = calendar.get(Calendar.YEAR)+"年"+(calendar.get(Calendar.MONTH)+1)+"月"+calendar.get(Calendar.DAY_OF_MONTH)+"日";
        System.out.println(dateStr);
    }
}
