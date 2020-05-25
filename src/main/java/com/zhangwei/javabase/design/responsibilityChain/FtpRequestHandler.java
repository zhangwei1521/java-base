package com.zhangwei.javabase.design.responsibilityChain;

public class FtpRequestHandler extends AbstractHandler {

    public FtpRequestHandler(String name){
        super(name);
    }
    @Override
    protected boolean support(Request request) {
        if("ftp".equals(request.getType())){
            return true;
        }
        return false;
    }

    @Override
    protected void doHandle(Request request) {
        System.out.println("receive ftp request content : "+request.getContent());
    }
}
