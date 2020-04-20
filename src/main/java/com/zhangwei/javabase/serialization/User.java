package com.zhangwei.javabase.serialization;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private static User singletonInstance = new User();
    private String name;
    private String pass;
    private int age;

    private User(){
    }

    public static User getInstance(){
        return singletonInstance;
    }

    //多次反序列化返回单例需要提供这个方法
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

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPass() {
        return pass;
    }
}
