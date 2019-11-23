package com.zhangwei.javabase.jvm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * 测试MethodHandle
 */
public class JvmDemo9 {

    public static void main(String[] args) throws Throwable {
        Object printer = System.currentTimeMillis()%2==0 ? new MyPrinter() : System.out;
        testPrint(printer);
    }

    private static void testPrint(Object printer) throws Throwable{
        //这里需要调用printer对象的print方法，动态方法可以直接调用，但是java不允许调用参数对象的类型里没有的方法
        //这里传统做法有两种：一种是强制类型转换，另一种是采用反射
        //jdk1.7新增MethodHandle提供了另一种方案

        //MethodType表示方法类型抽象，包括返回值类型、参数（列表）类型
        MethodType methodType = MethodType.methodType(void.class,String.class);
        //MethodHandles.Lookup表示一个方法查找器
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        //MethodHandle表示一个方法的引用，这里findVirtual表示查找一个实例方法，第一个参数表示在运行时的printer的实际类型中去查找
        MethodHandle methodHandle = lookup.findVirtual(printer.getClass(),"print",methodType);//.bindTo(printer);
        //调用方法有两种，invokeExact只需要传入参数，但是需要在上面获取MethodHandle时绑定实际执行对象，
        //invoke调用方法需要传入方法所在对象和参数
        //methodHandle.invokeExact("hello methodHandle invokeExact");
        methodHandle.invoke(printer,"hello methodHandle invoke");
    }
}

class MyPrinter{
    public void print(String msg) {
        System.err.println(msg);
    }
}
