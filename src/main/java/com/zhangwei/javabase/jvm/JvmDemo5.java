package com.zhangwei.javabase.jvm;

/**
 * 测试类加载
 */
public class JvmDemo5 {
    public static void main(String[] args) {
        //test01();
        test02();
    }

    private static void test01() {
        //创建子类对象会加载父类
        Child child = new Child();
    }

    private static void test02() {
        //加载子类就会加载父类
        System.out.println(Child.title);
    }


}
