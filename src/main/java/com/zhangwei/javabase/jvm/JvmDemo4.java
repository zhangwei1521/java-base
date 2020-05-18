package com.zhangwei.javabase.jvm;

import com.zhangwei.javabase.string.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 测试类加载器
 */
public class JvmDemo4 {
    public static void main(String[] args) throws Exception{
        //test01();
        //test02();
        //test03();
        //test04();
        test05();
    }

    private static void test01() throws Exception {
        MyClassLoader myClassLoader = new MyClassLoader();
        Object obj = myClassLoader.loadClass("com.zhangwei.javabase.jvm.JvmDemo4").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj.getClass()==JvmDemo4.class);
        System.out.println(obj instanceof com.zhangwei.javabase.jvm.JvmDemo4);
        //输出：
        //class com.zhangwei.javabase.jvm.JvmDemo4
        //false
        //false
        //使用自定义的类加载器加载类创建的对象，打印加载的类名仍是当前类，但是由于当前类已经被AppClassLoader加载，
        //所以使用instance运算符检查显示两个类型并不相同
    }

    public static void test02() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader myClassLoader = new MyClassLoader();
        Object obj1 = myClassLoader.loadClass("com.zhangwei.javabase.jvm.JvmDemo4").newInstance();
        //这里重复加载就直接抛出错误：java.lang.LinkageError ： attempted  duplicate class definition
        /*Object obj2 = myClassLoader.loadClass("com.zhangwei.javabase.jvm.JvmDemo4").newInstance();
        System.out.println(obj1.getClass());
        System.out.println(obj2.getClass());
        System.out.println(obj1.getClass()==obj2.getClass());*/

        //不重写loadClass方法，只重写findClass，已经加载的类不会重复加载
        NewClassLoader newClassLoader = new NewClassLoader();
        Object obj3 = newClassLoader.loadClass("com.zhangwei.javabase.jvm.JvmDemo4").newInstance();
        Object obj4 = newClassLoader.loadClass("com.zhangwei.javabase.jvm.JvmDemo4").newInstance();
        System.out.println(obj3.getClass());
        System.out.println(obj4.getClass());
        System.out.println(obj3.getClass()==obj4.getClass());
    }

    private static void test03()  {
        MyClassLoader myClassLoader = new MyClassLoader();

        ClassLoader classLoader1 = JvmDemo4.class.getClassLoader();

        //虽然Class定义中有classLoader这个Field，但是不能用反射获得这个字段
        //Field classLoaderField = JvmDemo4.class.getClass().getDeclaredField("classLoader");
        //classLoaderField.set(JvmDemo4.class,myClassLoader);

        ClassLoader classLoader2 = JvmDemo4.class.getClassLoader();

        //默认就是应用类加载器
        ClassLoader classLoader3 = Thread.currentThread().getContextClassLoader();

        Thread.currentThread().setContextClassLoader(myClassLoader);
        ClassLoader classLoader4 = Thread.currentThread().getContextClassLoader();

        //classLoader1===classLoader2===classLoader3
        //classLoader4===myClassLoader
        System.out.println(myClassLoader);
        System.out.println(classLoader1);
        System.out.println(classLoader2);
        System.out.println(classLoader3);
        System.out.println(classLoader4);
    }

    private static void test04(){
        //应用类加载器
        ClassLoader classLoader1 = JvmDemo4.class.getClassLoader();
        //系统类加载器，返回null
        ClassLoader classLoader2 = JvmDemo4.class.getClass().getClassLoader();
        //扩展类加载器
        ClassLoader classLoader3 = classLoader1.getParent();
        //系统类加载器，返回null
        ClassLoader classLoader4 = classLoader3.getParent();
        //应用类加载器
        ClassLoader classLoader5 = ClassLoader.getSystemClassLoader();

        System.out.println(classLoader1);
        System.out.println(classLoader2);
        System.out.println(classLoader3);
        System.out.println(classLoader4);
        System.out.println(classLoader5);
    }

    private static void test05(){
        try {
            URL[] urls={new URL("http://127.0.0.1/java-base-1.jar")};
            URLClassLoader classLoader = new URLClassLoader(urls);
            StringUtils stringUtils = (StringUtils)classLoader.loadClass("com.zhangwei.javabase.string.StringUtils").newInstance();
            System.out.println(stringUtils.isEmpty("12"));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
