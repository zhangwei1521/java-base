package com.zhangwei.javabase.design.responsibilityChain;

public class HttpRequestHandler extends AbstractHandler {

    public HttpRequestHandler(String name){
        super(name);
    }

    @Override
    protected boolean support(Request request) {
        if("http".equals(request.getType())){
            return true;
        }
        return false;
    }

    @Override
    protected void doHandle(Request request) {
        System.out.println("receive http request content : "+request.getContent());
    }
}
