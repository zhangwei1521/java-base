package com.zhangwei.javabase.asm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class MyObjectEnhancedMethodVisitor extends MethodVisitor {
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
            visitMethodInsn(Opcodes.INVOKESTATIC, "com/zhangwei/javabase/asm/EnhanceShell", "end", "(J)V", false);
        }
        super.visitInsn(opcode);
    }
}
