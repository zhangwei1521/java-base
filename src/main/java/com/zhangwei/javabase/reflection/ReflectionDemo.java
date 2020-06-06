package com.zhangwei.javabase.reflection;

import com.zhangwei.javabase.util.MyArrayUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionDemo {
    public static void main(String[] args) {
        //test01();
        //test02();
        test03();
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

    private static void test02() {
        Class clazz = ReflectionDemo.class;
        System.out.println(clazz.getName());
        System.out.println(clazz.getSimpleName());
        System.out.println(clazz.getCanonicalName());
        System.out.println(clazz.getTypeName());
    }

    private static void test03() {
        Class clazz = Child.class;
        try {
            //getField只返回public修饰的字段，其他的字段需要使用getDeclaredField
            //Field ageField = clazz.getField("age");
            //Field nameField = clazz.getField("name");
            //Field addrField = clazz.getField("addr");
            Field emailField = clazz.getField("email");

            Field ageField = clazz.getDeclaredField("age");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
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

    private int age;
    String name;
    protected String addr;
    public String email;

    @Override
    public void act() {
        System.out.println("act");
    }
}
