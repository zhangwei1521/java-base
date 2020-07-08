package com.zhangwei.javabase.grammar;

import com.zhangwei.javabase.grammar.pkg1.DemoExample;

public class Demo7 {
    public static void main(String[] args) {
        test01();
    }

    private static void test01() {
        //跨包只有public可以访问
        System.out.println(DemoExample.a);
        //System.out.println(DemoExample.b);

        //同包内只有private不可访问
        System.out.println(DemoExample2.a);
        System.out.println(DemoExample2.b);
        System.out.println(DemoExample2.c);
        //System.out.println(DemoExample2.d);

        //跨包子类可继承public、protected修饰的变量
        System.out.println(DemoExample3.getX());
    }
}

class DemoExample2 {
    public static int a = 11;
    protected static int b = 12;
    static int c = 13;
    private static int d = 14;

}

class DemoExample3 extends DemoExample {
    //跨包子类可继承public、protected修饰的变量
    static int getX(){
        //return a;
        return b;

        //return c;
        //return d;
    }
}