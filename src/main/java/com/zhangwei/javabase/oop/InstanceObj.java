package com.zhangwei.javabase.oop;

public class InstanceObj extends AbstractObj implements MethodTemplate {

    @Override
    public int method2() {
        return 2;
    }

    @Override
    public void method4() {

    }

    @Override
    public int method5(){
        return 1;
    }

    /*
    //不能继承final方法
    @Override
    public final int method6(){
        return 0;
    }*/
}
