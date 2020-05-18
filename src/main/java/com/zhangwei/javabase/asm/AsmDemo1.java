package com.zhangwei.javabase.asm;

import com.zhangwei.javabase.common.MyObject;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

public class AsmDemo1 {
    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        //test01();
        test02();
    }

    public static void test01() throws IOException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //这里先创建MyObject对象，就会加载这个类，即使后面重写覆盖了这个类的class文件，本次运行也不会重新加载这个类
        /*MyObject myObj = new MyObject();
        myObj.go();*/

        String className = "com.zhangwei.javabase.common.MyObject";
        ClassReader classReader = new ClassReader(className);
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        ClassVisitor classVisitor = new MyClassVisitor(Opcodes.ASM5,classWriter);
        classReader.accept(classVisitor,ClassReader.SKIP_DEBUG);
        byte[] newObjBytes = classWriter.toByteArray();

        MyObjectClassLodaer myLoader = new MyObjectClassLodaer();
        Class newObjClass = myLoader.defineClassFromBytes(className,newObjBytes);
        // 这里不能将newObjClass.newInstance()返回的实例强转为MyObject，因为强转时会先用系统类加载器去加载MyObject，但是
        // 这里的newObjClass是使用自定义的类加载器加载的，所以强转会失败
        // 这里本想用自定义的类加载器去加载MyObject解决上面的强转失败问题，但是还是失败了，因为这里加载的类名在前面已经加载过了，
        // 所以会报错：重复加载
        //loadMyObject(myLoader);
        //((MyObject)newObjClass.newInstance()).go();
        // 所以这里要调用go方法，只能通过反射来做
        Object newObj = newObjClass.newInstance();
        newObjClass.getMethod("go").invoke(newObj);

        /*File file = new File("target/classes/com/zhangwei/javabase/asm/MyObject.class");
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(newObjBytes);
        fout.close();

        //这里创建实例，才去加载class文件，所以加载的是已被改写的class文件
        MyObject myObj = new MyObject();
        myObj.go();*/
    }

    public static void test02() {
        MyObject myObj = MyObjectFactory.newInstance();
        myObj.go();
    }

    private static Class loadMyObject(MyObjectClassLodaer myLoader) throws IOException {
        File file = new File("target/classes/com/zhangwei/javabase/asm/MyObject.class");
        FileInputStream fin = new FileInputStream(file);
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = fin.read(buf)) != -1){
            bout.write(buf,0,len);
        }
        byte[] objBytes = bout.toByteArray();
        Class objClass = myLoader.defineClassFromBytes("com.zhangwei.javabase.common.MyObject",objBytes);
        return objClass;
    }
}

