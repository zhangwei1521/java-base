package com.zhangwei.javabase.design.singleton;

import com.zhangwei.javabase.serialization.SerializationDemo;

/**
 * 单例模式
 * 单例模式要点：私有化构造函数，使用静态变量引用单例对象
 * 三种初始化单例对象方式：
 *      1、在加载单例类文件时就初始化单例对象；
 *      2、在搜查使用时才去初始化单例对象，使用锁保证多线程不会同时初始化单例对象
 *      3、使用静态内部类持有单例对象
 *  后两种方式是延迟初始化到使用时，有利于系统启动时的性能优化
 *  反序列化的单例模式可参考
 @see com.zhangwei.javabase.serialization.SerializationDemo
 */
public class SingletonDemo {
    public static void main(String[] args) {
        Singleton1 singleton1 = Singleton1.getInstance();
        Singleton2 singleton2 = Singleton2.getInstance1();
        singleton2 = Singleton2.getInstance2();
        Singleton3 singleton3 = Singleton3.getInstance();
    }
}
