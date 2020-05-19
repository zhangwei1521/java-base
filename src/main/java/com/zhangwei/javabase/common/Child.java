package com.zhangwei.javabase.common;

public class Child extends Parent {

    public static String title;

    private String name;

    static {
        title = "child";
        System.out.println(title);
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
