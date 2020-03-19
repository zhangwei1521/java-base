package com.zhangwei.javabase.reflection;

import java.lang.reflect.Method;

public class ReflectionDemo {
    public static void main(String[] args) {
        test01();
    }

    private static void test01(){
        Method[] methods = Parent.class.getDeclaredMethods();
        for(Method me : methods){
            System.out.println(me);
        }
        System.out.println("=================");
        methods = Child.class.getDeclaredMethods();
        for(Method me : methods){
            if(me.isBridge()){
                continue;
            }
            System.out.println(me);
        }
        System.out.println("=================");
        methods = Child.class.getMethods();
        for(Method me : methods){
            if(me.isBridge()){
                continue;
            }
            System.out.println(me);
        }
    }
}

class Parent{
    public void say(){
        System.out.println("hello");
    }
    Object getLang(){
        return null;
    }
    int getAge(){
        return 55;
    }
}

interface Actor{
    void act();
}

class Child extends Parent implements Actor{
    public void say(){
        System.out.println("hi");
    }
    String getLang(){
        return "hi";
    }
    int getAge(){
        return 25;
    }

    @Override
    public void act() {
        System.out.println("act");
    }
}
