package com.zhangwei.javabase.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MyClassVisitor extends ClassVisitor {
    public MyClassVisitor(int api, ClassVisitor cv) {
        super(api,cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature,
                      String superName, String[] interfaces){
        cv.visit(version,access,name,signature,superName,interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc,
                                     String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access,name,desc,signature,exceptions);
        if(name != null && name.equals("go")){
            mv = new MyMethodVisitor(Opcodes.ASM5,mv);
        }
        return mv;
    }
}
