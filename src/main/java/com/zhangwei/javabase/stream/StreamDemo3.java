package com.zhangwei.javabase.stream;

import com.zhangwei.javabase.string.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamDemo3 {
    public static void main(String[] args) {
        test01();
    }

    //filter测试
    private static void test01(){
        List<String> strList = new ArrayList<>();
        //strList.add("123");
        strList = strList.stream().filter(item-> !StringUtils.equals(item,"123")).collect(Collectors.toList());
        System.out.println(strList.size());
    }
}

class Parent {
    static int call(){
        return 0;
    }
}

class Child extends Parent{
    static int hal(){
        return call();
    }
}