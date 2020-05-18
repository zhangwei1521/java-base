package com.zhangwei.javabase.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormater {
    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String todayStr = dateFormat.format(now);
        System.out.println(todayStr);

        dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        todayStr = dateFormat.format(now);
        System.out.println(todayStr);

        Calendar calendar = Calendar.getInstance();
        String dateStr = calendar.get(Calendar.YEAR)+"年"+(calendar.get(Calendar.MONTH)+1)+"月"+calendar.get(Calendar.DAY_OF_MONTH)+"日";
        System.out.println(dateStr);
    }
}
