package com.zhangwei.javabase.asm;

import com.zhangwei.javabase.annotation.MyMeghodAnno;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class MyObjectFactory{
    public static MyObject newInstance(){
        Class myObjectClass = MyObject.class;
        try {
            Method method1 = myObjectClass.getMethod("go");
            MyMeghodAnno anno = method1.getDeclaredAnnotation(MyMeghodAnno.class);
            if(anno.logging()==true){
                return newChildClassObj();
            }
            else {
                return (MyObject)myObjectClass.getConstructor().newInstance();
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("no such method");
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | IOException e){
            e.printStackTrace();
        }
        return null;
    }

    static MyObject newChildClassObj() throws IOException {
        //使用ASM库生成子类
        ClassReader classReader = new ClassReader("com.zhangwei.javabase.asm.MyObject");
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        ClassVisitor classVisitor = new MyObjectVisitor(Opcodes.ASM5,classWriter);
        classReader.accept(classVisitor,ClassReader.SKIP_DEBUG);
        MyObjectClassLodaer classLodaer = new MyObjectClassLodaer();
        Class enhancedMyObjectClass = classLodaer.defineClassFromBytes("com.zhangwei.javabase.asm.MyObject$EnhancedByASM",classWriter.toByteArray());
        try {
            return (MyObject)enhancedMyObjectClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
