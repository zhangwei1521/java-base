package com.zhangwei.javabase.annotation;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnnotationDemo {
    public static void main(String[] args) throws IOException {
        //test01();
        test02();
    }

    public static void test01(){
        Class myObjectClass = MyObject.class;
        Field[] myObjFields = myObjectClass.getDeclaredFields();
        Method[] myObjMethods = myObjectClass.getDeclaredMethods();

        List<Annotation> annotationList = new ArrayList<>();
        annotationList.addAll(Arrays.asList(myObjectClass.getDeclaredAnnotations()));
        for (Field field : myObjFields){
            annotationList.addAll(Arrays.asList(field.getDeclaredAnnotations()));
        }
        for (Method method : myObjMethods){
            annotationList.addAll(Arrays.asList(method.getDeclaredAnnotations()));
        }
        for(Annotation anno : annotationList){
            System.out.println(anno.annotationType());
            if(anno instanceof MyClassAnno){
                System.out.println("str : "+((MyClassAnno)anno).str());
                System.out.println("val : "+((MyClassAnno)anno).val());
            }
            if(anno instanceof MyFieldAnno){
                System.out.println("value : "+((MyFieldAnno)anno).value());
            }
            if(anno instanceof MyMeghodAnno){
                System.out.println("value : "+((MyMeghodAnno)anno).value());
                System.out.println("logging : "+((MyMeghodAnno)anno).logging());
            }
        }
    }

    public static void test02() {
        MyObject myObj = MyObjectFactory.newInstance();
        myObj.method1();
    }
}

class MyObjectFactory{
    public static MyObject newInstance(){
        Class myObjectClass = MyObject.class;
        try {
            Method method1 = myObjectClass.getMethod("method1");
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
        ClassReader classReader = new ClassReader("com.zhangwei.javabase.annotation.MyObject");
        ClassWriter classWriter = new ClassWriter(classReader,ClassWriter.COMPUTE_MAXS);
        ClassVisitor classVisitor = new MyClassVisitor(Opcodes.ASM5,classWriter);
        classReader.accept(classVisitor,ClassReader.SKIP_DEBUG);
        MyObjectClassLodaer classLodaer = new MyObjectClassLodaer();
        Class enhancedMyObjectClass = classLodaer.defineClassFromBytes("com.zhangwei.javabase.annotation.MyObject$EnhancedByASM",classWriter.toByteArray());
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

class MyObjectClassLodaer extends ClassLoader{
    public Class defineClassFromBytes(String className,byte[] bytes){
        return defineClass(className,bytes,0,bytes.length);
    }
}

class MyClassVisitor extends ClassVisitor{
    String superClassName;
    public MyClassVisitor(int op,ClassWriter classWriter){
        super(op,classWriter);
    }

    @Override
    public void visit(int version, int access, String name, String signature,
                      String superName, String[] interfaces) {
        superClassName = name;
        String enhanceClassName = name+"$EnhancedByASM";
        super.visit(version,access,enhanceClassName,signature,superClassName,interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc,
                                     String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access,name,desc,signature,exceptions);
        MethodVisitor wmv = mv;
        if(name.equals("method1")){
            wmv = new MyObjectEnhancedMethodVisitor(Opcodes.ASM5,mv);
        }
        else if(name.equals("<init>")){
            //如果是构造方法，处理子类中父类的构造函数调用
            wmv = new MyObjectEnhancedConstructorMethodVisitor(Opcodes.ASM5, mv,superClassName);
        }
        return wmv;
    }
}

class MyObjectEnhancedMethodVisitor extends MethodVisitor{
    public MyObjectEnhancedMethodVisitor(int api,MethodVisitor methodVisitor) {
        super(api,methodVisitor);
    }

    @Override
    public void visitCode() {
        visitMethodInsn(Opcodes.INVOKESTATIC,"java/lang/System","currentTimeMillis","()J",false);
        visitVarInsn(Opcodes.LSTORE,1);
    }

    @Override
    public void visitInsn(int opcode) {
        if(opcode>=Opcodes.IRETURN && opcode<=Opcodes.RETURN){
            visitVarInsn(Opcodes.LLOAD,1);
            visitMethodInsn(Opcodes.INVOKESTATIC, "com/zhangwei/javabase/annotation/EnhanceShell", "end", "(J)V", false);
        }
        super.visitInsn(opcode);
    }
}

class MyObjectEnhancedConstructorMethodVisitor extends MethodVisitor{
    private String superClassName1;
    public MyObjectEnhancedConstructorMethodVisitor(int api,MethodVisitor methodVisitor,String superClassName) {
        super(api,methodVisitor);
        superClassName1 = superClassName;
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        if (opcode==Opcodes.INVOKESPECIAL && name.equals("<init>")){
            owner = superClassName1;
        }
        super.visitMethodInsn(opcode, owner, name, desc, itf);
    }
}



