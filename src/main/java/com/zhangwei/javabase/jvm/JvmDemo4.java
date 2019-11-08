package com.zhangwei.javabase.jvm;

/**
 * 测试类加载
 */
public class JvmDemo4 {
    public static void main(String[] args) {
        Thread.currentThread().getContextClassLoader();
    }
}

class MyClassLoader extends ClassLoader{

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {

        return null;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        throw new ClassNotFoundException(name);
    }
}
