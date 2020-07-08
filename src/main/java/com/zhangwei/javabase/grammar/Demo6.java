package com.zhangwei.javabase.grammar;

public class Demo6 {
    public static void main(String[] args) {
        test01();
    }
    //静态内部类和非静态内部类
    private static void test01(){
        OuterClass.StaticInnerClass staticInnerClass = new OuterClass.StaticInnerClass();
        staticInnerClass.staticPrint();
        staticInnerClass.print();

        OuterClass outerClass = new OuterClass();
        OuterClass.InnerClass innerClass = outerClass.new InnerClass();
        innerClass.print();
    }
}

class OuterClass{
    private static int a=1;
    private int b=2;

    protected static class StaticInnerClass{
        private static int c=3;
        private int d=4;

        protected static void staticPrint(){
            System.out.println("StaticInnerClass.staticPrint[a]="+a);
            //静态内部类的静态方法不能访问外部类的非静态成员变量和非静态方法
            //System.out.println("StaticInnerClass.staticPrint[b]="+b);
        }

        protected void print(){
            System.out.println("StaticInnerClass.print[a]="+a);
            //静态内部类的非静态方法也不能访问外部类的非静态成员变量和非静态方法
            //因为这里的静态内部类实例对象【没有关联】任何外部类对象
            //System.out.println("StaticInnerClass.print[b]="+b);
        }
    }

    protected class InnerClass{
        //非静态内部类不能包含任何静态变量
        //private static int e;
        private int f=6;
        private int b=7;

        //非静态内部类不能包含任何静态方法
        /*protected static void staticPrint(){
            System.out.println("InnerClass.staticPrint[a]="+a);
            //静态内部类的静态方法不能访问外部类的非静态成员变量和非静态方法
            //System.out.println("InnerClass.staticPrint[b]="+b);
        }*/

        protected void print(){
            System.out.println("InnerClass.print[a]="+a);
            System.out.println("InnerClass.print[b]="+b);
            System.out.println("InnerClass.print[b1]="+OuterClass.this.b);
        }
    }
}