package com.zhangwei.javabase.asm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class MyObjectEnhancedConstructorMethodVisitor extends MethodVisitor {
    private String superClassName1;
    public MyObjectEnhancedConstructorMethodVisitor(int api,MethodVisitor methodVisitor,String superClassName) {
        super(api,methodVisitor);
        superClassName1 = superClassName;
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        if (opcode== Opcodes.INVOKESPECIAL && name.equals("<init>")){
            owner = superClassName1;
        }
        super.visitMethodInsn(opcode, owner, name, desc, itf);
    }
}
