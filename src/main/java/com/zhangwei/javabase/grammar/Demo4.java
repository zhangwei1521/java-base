package com.zhangwei.javabase.grammar;


public class Demo4 {
    public static void main(String[] args) {
        //test01();
        test02();
    }

    private static void test01(){
        byte a = -128, b = 127;
        System.out.println(a+"\t"+b);
        System.out.println(Byte.MIN_VALUE+"\t"+Byte.MAX_VALUE);
        short c = -32768, d = 32767;
        System.out.println(c+"\t"+d);
        System.out.println(Short.MIN_VALUE+"\t"+Short.MAX_VALUE);
        int e = -2147483648, f = 2147483647;
        System.out.println(e+"\t"+f);
        System.out.println(Integer.MIN_VALUE+"\t"+Integer.MAX_VALUE);
        long g = -(1l<<63), h = (1l<<63)-1;
        System.out.println(g+"\t"+h);
        System.out.println(Long.MIN_VALUE+"\t"+Long.MAX_VALUE);

        System.out.println(Float.MIN_EXPONENT+"\t"+Float.MAX_EXPONENT);
        System.out.println(Float.MIN_VALUE+"\t"+ Float.MAX_VALUE);
        System.out.println(Float.MIN_NORMAL);

        System.out.println(Double.MIN_VALUE+"\t"+ Double.MAX_VALUE);
    }

    private static void test02(){
        byte b = 50;
        //类型提升导致编译报错
        //b = b * 2;
        byte c = 2;
        //仍然报错
        //在没有long、float、double类型变量参与运算时，byte、short、char都会提升为int型的值
        //b = b * c;
    }
}

