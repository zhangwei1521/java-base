package com.zhangwei.javabase.jvm;

import java.lang.ref.WeakReference;

/**
 * 测试弱引用
 */
public class JvmDemo3 {
    public static void main(String[] args) {
        ClassA a = new ClassA();
        ClassB b = new ClassB();
        a.classB=b;
        b.classA=a;
        WeakReference<ClassA> weakA = new WeakReference<>(a);
        WeakReference<ClassB> weakB = new WeakReference<>(b);

        System.out.println(weakA.get());
        System.out.println(weakB.get());

        a=null;
        b=null;
        //如果 JVM(HotSpot) 采用 引用计数(RC) 来分析对象是否可以回收，并且可以处理循环引用问题，这里就可以实时回收，下面将输出null
        //实际上，JVM(HotSpot) 采用的是可达性分析算法来决定对象是否可以回收，而且不能实时进行，所以下面还是输出了两个对象的信息
        System.out.println(weakA.get());
        System.out.println(weakB.get());

        // System.gc();并不能保证执行后就开始进行GC，但是大多数时候执行后可以开始GC
        System.gc();

        //可以看到弱引用不影响GC
        System.out.println(weakA.get());
        System.out.println(weakB.get());
    }
}

class ClassA{
    ClassB classB;
}

class ClassB{
    ClassA classA;
}
