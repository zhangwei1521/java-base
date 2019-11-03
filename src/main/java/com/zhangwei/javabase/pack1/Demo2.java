package com.zhangwei.javabase.pack1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Demo2 {
    public static void main(String[] args) {
       testDateFormat();
    }

    private static void testCalendar(){
        Calendar c1 = Calendar.getInstance();
        c1.set(2019,8,8,8,0,0);
        Calendar c2 = Calendar.getInstance();
        c2.set(2019,8,8,18,0,0);
        System.out.println("c2: "+c2.getTime());
        System.out.println("c1: "+c1.getTime());
        double hours = (c2.getTime().getTime()-c1.getTime().getTime())/(1000*60*60*24.0);
        System.out.println("hours: "+hours);
        System.out.println(5.0/7.0);
    }

    private static void testDateFormat(){
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        Date now = new Date();
        System.out.println(dateFormat.format(now));
    }
}
