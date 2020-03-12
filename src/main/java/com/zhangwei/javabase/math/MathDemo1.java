package com.zhangwei.javabase.math;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MathDemo1 {

    public static void main(String[] args) {
        //test01();
        test02();
    }

    /**
     * 测试左移
     */
    private static void test01() {
        int a = 49;
        System.out.println(a << 8);
        System.out.println("wwwwww");
    }

    private static void test02(){
        double d1 = 55.8;
        double d2 = 196.8;
        System.out.println("55.8 + 196.8 = "+(d1+d2));

        DecimalFormat dft = new DecimalFormat("0.0");
        System.out.println("55.8 + 196.8 = "+dft.format(d1+d2));

        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        System.out.println("55.8 + 196.8 = "+(b1.add(b2)));
    }
}
