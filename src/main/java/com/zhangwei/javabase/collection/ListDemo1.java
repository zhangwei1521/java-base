package com.zhangwei.javabase.collection;

import java.util.ArrayList;
import java.util.List;

public class ListDemo1 {
    public static void main(String[] args) {
        List<String> yearList = new ArrayList<>();
        yearList.add("2017");
        yearList.add("2018");
        yearList.add("2018");
        if(yearList.contains("2018")){
            System.out.println("yes...");
        }
    }
}
