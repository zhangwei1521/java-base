package com.zhangwei.javabase.design.responsibilityChain;

public class GenerateRequestHandler extends AbstractHandler {

    public GenerateRequestHandler(String name){
        super(name);
    }

    @Override
    protected void doHandle(Request request) {
        return;
    }

    @Override
    protected boolean support(Request request) {
        return false;
    }
}
