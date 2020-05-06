package com.zhangwei.javabase.collection.my;

public class User {
    private String name;
    private int age;

    public int hashCode(){
        return name.hashCode();
    }

    public boolean equals(Object rhs){
        return rhs instanceof User && name.equals(((User) rhs).name);
    }
}
