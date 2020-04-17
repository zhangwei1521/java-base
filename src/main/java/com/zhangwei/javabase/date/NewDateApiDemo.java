package com.zhangwei.javabase.date;


import java.time.*;
import java.time.format.DateTimeFormatter;

public class NewDateApiDemo {
    public static void main(String[] args) {
        //test01();
        //test02();
        //test03();
        //test04();
        //test05();
        //test06();
        test07();
    }

    //Instant
    private static void test01(){
        Instant now = Instant.now();
        System.out.println(now);

        Instant instant1 = Instant.ofEpochSecond(10);
        System.out.println(instant1);

        Instant instant2 = Instant.ofEpochSecond(1,10);
        System.out.println(instant2);

        Instant instant3 = Instant.ofEpochMilli(10000);
        System.out.println(instant3);

    }

    //LocalDate
    private static void test02(){
        LocalDate now = LocalDate.now();
        System.out.println(now);

        LocalDate date1 = LocalDate.of(2018,12,1);
        System.out.println(date1);

        LocalDate date2 = LocalDate.ofYearDay(2018,32);
        System.out.println(date2);

        LocalDate date3 = LocalDate.ofEpochDay(0);
        System.out.println(date3);
    }

    //LocalTime
    private static void test03(){
        LocalTime now = LocalTime.now();
        System.out.println(now);

        LocalTime time1 = LocalTime.of(0,1);
        System.out.println(time1);

        LocalTime time2 = LocalTime.ofSecondOfDay(61);
        System.out.println(time2);
    }

    //LocalDateTime
    private static void test04(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
    }

    //ZonedDateTime
    private static void test05(){
        ZonedDateTime now1 = ZonedDateTime.now();
        System.out.println(now1);

        ZonedDateTime now2 = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
        System.out.println(now2);

        ZonedDateTime now3 = ZonedDateTime.ofInstant(Instant.now(),ZoneId.of("America/Los_Angeles"));
        System.out.println(now3);

        ZonedDateTime now4 = ZonedDateTime.now(ZoneId.of("GMT"));
        System.out.println(now4);
    }

    //DateTimeFormatter
    private static void test06(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));

        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String dateTimeStr = "2020年04月15日 23:59:59";
        LocalDateTime time1 = LocalDateTime.parse(dateTimeStr,dtf2);
        System.out.println(time1);
    }

    //Period
    private static void test07(){
        LocalDate date1 = LocalDate.of(2020,4,15);
        LocalDate date2 = LocalDate.of(2020,3,12);
        //LocalDate date3 = LocalDate.of(2020,2,12);
        Period period1 = Period.between(date2,date1);
        System.out.println(period1.getDays()+" days");
        System.out.println(period1.getMonths()+" months");

        Period period2 = Period.between(date1,date2);
        System.out.println(period2.getDays()+" days");
        System.out.println(period2.getMonths()+" months");

        System.out.println("=======");

        LocalTime time1 = LocalTime.of(1,1);
        LocalTime time2 = LocalTime.of(2,2);
        Duration duration = Duration.between(time1,time2);
        System.out.println(duration.getSeconds());
        System.out.println(duration.getNano());
    }
}
