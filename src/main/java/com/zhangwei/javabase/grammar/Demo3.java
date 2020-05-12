package com.zhangwei.javabase.grammar;

import com.zhangwei.javabase.grammar.pkg1.DemoExmaple;

public class Demo3 {

    private static class Parent{
        //虽然name是private，但是子类调用继承的getName还是可以获得此字段
        //这里的解释是，子类实际上是有这个字段的，但是不能在子类的方法中访问
        private String name = "parent0";
        Parent(){
            String name = this.getClass().getName();
            //this.name = "parent1";
            System.out.println("Parent() : "+name);
        }

        public String getName(){
            return this.name;
        }
    }

    private static class Child extends Parent{
        //这里的字段名和父类中的字段名相同，但是编译后这个字段名可以被区分开
        //可以认为这里声明的字段和从父类那里获得的同名字段保存在不同的分区中
        String name = "child0";
        Child(){
            String name = this.getClass().getName();
            System.out.println("Child() : "+name);
        }
    }

    public static void main(String[] args) {
        //test01();
        test02();
    }

    private static void test01(){
        //构造子类实例，不会构造其父类对象，但是会调用父类构造方法
        //首先执行Parent的无参构造方法，但是class对象是Child的class对象
        //这里打印出的是同一个class对象，即Child的class对象
        Child child = new Child();
        System.out.println("========");
        System.out.println(child.getName());
        System.out.println(child.name);
    }

    //测试protected方法
    private static void test02(){
        //不能跨包访问
        //DemoExmaple.hello();

        //允许同包访问
        DemoExmaple2.hello();

        //(跨包)子类中可以访问父类的protected方法
        DemoExmaple3.hello();
    }

}

class DemoExmaple2 {
    protected static void hello(){
        System.out.println("Demo1Exmaple2 hello");
    }
}

class DemoExmaple3 extends DemoExmaple {
    protected static void hello(){
        System.out.println("Demo1Exmaple3 hello");
        //不能通过super访问父类静态方法，需要通过父类类名来访问
        //super.hello();
        //(跨包)子类中可以访问父类的protected方法
        DemoExmaple.hello();
    }
}