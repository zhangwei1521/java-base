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
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        System.out.println(now);
        System.out.println(dateFormat.format(now));
    }
}
