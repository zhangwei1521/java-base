package com.zhangwei.javabase.jvm;

import java.io.IOException;
import java.io.InputStream;

/**
 * 测试自定义类加载器
 */
public class JvmDemo7 {
    public static void main(String[] args) throws Exception{
        NewClassLoader newClassLoader = new NewClassLoader();
        Object obj = newClassLoader.loadClass("com.zhangwei.javabase.jvm.JvmDemo7").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj.getClass()==JvmDemo7.class);
        System.out.println(obj instanceof com.zhangwei.javabase.jvm.JvmDemo7);
        //输出：
        //class com.zhangwei.javabase.jvm.JvmDemo7
        //true
        //true
        //不重写loadClass方法，只重写findClass，已经加载的类不会重复加载
    }
}

class NewClassLoader extends ClassLoader{
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            String fileName = name.substring(name.lastIndexOf('.')+1)+".class";
            InputStream is = getClass().getResourceAsStream(fileName);
            if(is==null){
                return super.loadClass(name);
            }
            byte[] b = new byte[is.available()];
            is.read(b);
            Class clazz = defineClass(name,b,0,b.length);
            return clazz;
        } catch (IOException e){
            throw new ClassNotFoundException(name);
        }
    }
}
