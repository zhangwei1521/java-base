package com.zhangwei.javabase.jvm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import java.lang.invoke.MethodHandles;

/**
 * 测试MethodHandle
 */
public class JvmDemo8 {

    static class Class8A{
        public void println(String s){
            System.out.println(s);
        }
    }

    public static void main(String[] args) throws Throwable{
        Object obj = System.currentTimeMillis() % 2 == 0 ? System.out : new Class8A();
        test01(obj);
    }

    private static void test01(Object obj) throws Throwable{
        MethodType mt = MethodType.methodType(void.class,String.class);
        getPrintlnMN(obj,"println",mt).invokeExact("test MethodHandle");
    }

    private static MethodHandle getPrintlnMN(Object receiver,String methodName,MethodType mt) throws Throwable{

        return MethodHandles.lookup().findVirtual(receiver.getClass(),methodName,mt).bindTo(receiver);
    }
}
