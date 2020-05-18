package com.zhangwei.javabase.jvm;

public class Parent {
    static {
        title = "parent1";
    }
    public static String title = "parent0";

    private String name;

    static {
        System.out.println(title);
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
