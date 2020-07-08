package com.zhangwei.javabase.grammar;

public class Demo3 {

    public static void main(String[] args) {
        test01();
    }

    //测试对象的继承模型
    private static void test01(){
        //构造子类实例，不会构造其父类对象，但是会调用父类构造方法，
        //父类的成员变量和方法保存在子类实例对象的特殊分区中
        Child child1 = new Child();
        System.out.println("========");
        //不同子类实例对象都创建自己的父类成员变量
        Child child2 = new Child();
    }
}

class Parent{
    static {
        System.out.println("Parent classLoad init....");
    }

    public int a = 1;
    protected int b = 2;
    int c = 3;
    private int d = 4;
    //除了private，其他修饰变量都可被继承，不同子类实例对象都创建自己的父类成员变量
    Object obj1 = new Object();
    private Object obj2 = new Object();

    {
        System.out.println("Parent createObject init....");
        System.out.println("obj1 : "+obj1);
        System.out.println("obj2 : "+obj2);
    }

    Parent(){
        //super();
        System.out.println("Parent()");
        System.out.println("a : "+a);
        System.out.println("b  : "+b);
        System.out.println("c : "+c);
        System.out.println("d  : "+d);
        System.out.println("object instance : "+this);
        System.out.println("object class : "+this.getClass());
        System.out.println("Parent()");
    }

    protected int getD(){
        //return d;
        //尽管在子类中调用这个方法，this是子类实例，但是这里还是访问的父类的成员变量d
        return this.d;
    }

    Object getObj2(){
        return obj2;
    }
}

class Child extends Parent{
    static {
        System.out.println("Child classLoad init....");
    }
    //这里的字段名和父类中的字段名相同，但是编译后这个字段名可以被区分开
    //可以认为这里声明的字段和从父类那里获得的同名字段保存在不同的分区中
    public int a = 11;
    protected int b = 12;
    int c = 13;
    private int d = 14;

    {
        System.out.println("Child createObject init....");
        System.out.println("obj1 : "+this.obj1);
        System.out.println("obj2 : "+this.getObj2());
    }

    Child(){
        //如果没有显示调用super或this，就会触发一次super隐式调用
        //super和this不能同时使用
        //super();
        this(1111);
        //super();
    }

    Child(int a){
        //隐式调用super
        //隐式初始化非静态成员变量
        System.out.println("Child()");
        System.out.println("a : "+a);
        System.out.println("b  : "+b);
        System.out.println("c : "+c);
        System.out.println("d  : "+d);
        System.out.println("super.a : "+super.a);
        System.out.println("super.b  : "+super.b);
        System.out.println("super.c : "+super.c);
        //System.out.println("super.d : "+super.d);
        System.out.println("super.d : "+getD());

        System.out.println("object instance : "+this);
        System.out.println("object class : "+this.getClass());
        System.out.println("Child()");
    }
}




