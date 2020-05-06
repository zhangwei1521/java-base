package com.zhangwei.javabase.grammar;

public class Demo2 {
    public static void main(String[] args) {
        //test01();
        //test02();
        //test03();
        //test04();
        test05();
    }

    //负数取余
    private static void test01() {
        System.out.println(-5 % 4);
    }

    // |=运算符
    private static void test02(){
        boolean a = true;
        boolean b = false;
        a |= b;
        System.out.println(a);
        b |= a;
        System.out.println(b);
        b = false;
        b &= a;
        System.out.println(b);

        System.out.println("===============");
        int i = 1;
        int j = 2;
        //i |= j;
        j |= i;
        System.out.println(j);
    }

    //移位运算
    private static void test03(){
        int a = 2;
        int b = a >> 1;
        int c = a >>> 1;
        int d = -2;
        int e = d >> 1;
        int f = d >>> 1;
        System.out.println(a+" : "+Integer.toBinaryString(a));
        System.out.println(b+" : "+Integer.toBinaryString(b));
        System.out.println(c+" : "+Integer.toBinaryString(c));
        System.out.println(d+" : "+Integer.toBinaryString(d));
        System.out.println(e+" : "+Integer.toBinaryString(e));
        //11111111111111111111111111111110
        //11111111111111111111111111111111
        //1111111111111111111111111111111
        System.out.println(f+" : "+Integer.toBinaryString(f));
    }

    private static void test04(){
        System.out.println("max : "+Integer.toBinaryString(1<<30));
        int cap = (1 << 30)-2;
        System.out.println("cap : "+Integer.toBinaryString(cap));
        //这个算法用于将cap最左侧的1扩充到右边的所有位，即得到大于cap的最小2的n次幂值减一
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        System.out.println("n   : "+Integer.toBinaryString(n));
        int MAXIMUM_CAPACITY = 1 << 30;
        int t = (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
        System.out.println("t   : "+Integer.toBinaryString(t));
    }

    private static void test05(){
        String s1 = "zhangsan";
        int h;
        int hashVal = (h = s1.hashCode()) ^ (h >>> 16);
        //System.out.println(Integer.toBinaryString('a'));
        System.out.println(Integer.toBinaryString(h));
        System.out.println(Integer.toBinaryString(h >>> 16));
        System.out.println(Integer.toBinaryString(hashVal));
        System.out.println(hashVal);
        System.out.println(hashVal & 15);
    }
}
