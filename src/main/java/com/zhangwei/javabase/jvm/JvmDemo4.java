package com.zhangwei.javabase.jvm;

import java.io.IOException;
import java.io.InputStream;

/**
 * 测试类加载
 */
public class JvmDemo4 {
    public static void main(String[] args) throws Exception{
        MyClassLoader myClassLoader = new MyClassLoader();
        Object obj = myClassLoader.loadClass("com.zhangwei.javabase.jvm.JvmDemo4").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj.getClass()==JvmDemo4.class);
        System.out.println(obj instanceof com.zhangwei.javabase.jvm.JvmDemo4);
        //输出：
        //class com.zhangwei.javabase.jvm.JvmDemo4
        //false
        //使用自定义的类加载器加载类创建的对象，打印加载的类名仍是当前类，但是由于当前类已经被AppClassLoader加载，
        //所以使用instance运算符检查显示两个类型并不相同
    }
}

class MyClassLoader extends ClassLoader{
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
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
