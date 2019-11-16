package com.zhangwei.javabase.serialization;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private static User singletonInstance = new User();
    private String name;
    private int age;

    private User(){
    }

    public static User getInstance(){
        return singletonInstance;
    }

    private Object readResolve(){
        return singletonInstance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
