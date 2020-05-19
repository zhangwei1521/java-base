package com.zhangwei.javabase.design.template;

public abstract class AbstractCalculator {

    public final void calculate(String exp){
        logAction(exp);
        doCalculate(exp);
    }

    private void logAction(String exp){
        System.out.print(exp+" = ");
    }

    protected abstract void doCalculate(String exp);
}
