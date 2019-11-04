package com.zhangwei.javabase.oop;

public abstract class AbstractObj implements MethodTemplate {
    @Override
    public void method1() {

    }

    @Override
    public int method2() {
        return 0;
    }

    @Override
    public boolean method3(String val) {
        return false;
    }

    public abstract void method4();

    public int method5(){
        return 1;
    }
}
