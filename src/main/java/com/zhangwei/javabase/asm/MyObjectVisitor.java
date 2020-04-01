package com.zhangwei.javabase.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class MyObjectVisitor extends ClassVisitor {
    String superClassName;
    public MyObjectVisitor(int op, ClassWriter classWriter){
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
        if(name.equals("go")){
            wmv = new MyObjectEnhancedMethodVisitor(Opcodes.ASM5,mv);
        }
        else if(name.equals("<init>")){
            //如果是构造方法，处理子类中父类的构造函数调用
            wmv = new MyObjectEnhancedConstructorMethodVisitor(Opcodes.ASM5, mv,superClassName);
        }
        return wmv;
    }
}
