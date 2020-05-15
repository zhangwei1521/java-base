package com.zhangwei.javabase.grammar;

public class Demo1 {

    public static void main(String[] args) {
        //test01();
        //test02();
        //test03();
        //test04();
        test05();
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

    //测试instanceof
    private static void test05(){
        String str = null;
        //false
        boolean isString = str instanceof String ? true : false;
        if(isString){
            System.out.println("java could deduce the declare type of null var");
        }
        else {
            System.out.println("java could not deduce the declare type of null var");
        }
    }

    private static void test06(){
        Boolean b = true;
        //不能修改Boolean对象内部值
        modify(b);
        System.out.println("===>"+b);
    }

    //没有修改Boolean对象内部值的方法
    private static void modify(Boolean b){
        System.out.println(b);
        b = !b;
        System.out.println(b);
    }

    //测试final
    private static void test07(){
        class Example{
            //使用final修饰，就不要手动设置初始值为null
            //private final String str = null;
            private final String str;
            Example(){
                str = "hello";
            }
        }
    }
}



