package com.zhangwei.javabase.grammar;

public class Demo1 {

    public static void main(String[] args) {
        //test01();
        //test02();
        test03();
    }

    private static void test01(){
        if(null==null){
            System.out.println("null == null");
        }
        //null 可以进行强制类型转换
        Object obj = (Object)null;
    }



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
}