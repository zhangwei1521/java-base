package com.zhangwei.javabase.grammar;

import com.zhangwei.javabase.grammar.pkg1.Demo1Exmaple;

public class Demo1 {

    public static void main(String[] args) {
        //test01();
        //test02();
        //test03();
        test04();
    }

    //null 可以进行强制类型转换
    private static void test01(){
        if(null==null){
            System.out.println("null == null");
        }
        Object obj = (Object)null;
    }


    //测试if条件语句
    private static void test02(){
        int a = 14,b=11;
        if(a < 10)
            System.out.println("a < 10");
        else if(b==11)
            if(a > 15){
                System.out.println("a > 15");
            }
            else if(a > 10) {
                System.out.println("15 >= a > 10");
            }
        if(a==14)
            System.out.println("a==14");
    }

    //测试switch
    private static void test03(){
        String s1 = "she";
        switch (s1){
            case "he":
                System.out.println("man");
                break;
            case "she":
                System.out.println("woman");
                System.out.println("girl");
                break;
            default:
                break;
        }
    }

    //测试protected方法
    private static void test04(){
        //不能跨包访问
        //Demo1Exmaple.hello();

        //允许同包访问
        Demo1Exmaple2.hello();

        //(跨包)子类中可以访问父类的protected方法
        Demo1Exmaple3.hello();
    }
}

class Demo1Exmaple2{
    protected static void hello(){
        System.out.println("Demo1Exmaple2 hello");
    }
}

class Demo1Exmaple3 extends Demo1Exmaple{
    protected static void hello(){
        System.out.println("Demo1Exmaple3 hello");
        //不能通过super访问父类静态方法，需要通过父类类名来访问
        //super.hello();
        //(跨包)子类中可以访问父类的protected方法
        Demo1Exmaple.hello();
    }
}

