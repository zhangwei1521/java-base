package com.zhangwei.javabase.design.responsibilityChain;

/**
 * 责任链模式
 * 除了最后一个处理器，每个处理器都有一个后继处理器，这样的处理器链既可以用于对一个
 * 待处理对象进行多次处理，也可以用于选择处理器链中合适的处理器对该对象处理
 */
public class ResponsibilityChainDemo {
    public static void main(String[] args) {
        HttpRequestHandler httpRequestHandler = new HttpRequestHandler("HttpHandler");
        FtpRequestHandler ftpRequestHandler = new FtpRequestHandler("FtpHandler");
        GenerateRequestHandler defaultHandler = new GenerateRequestHandler("defaultHandler");

        defaultHandler.setNext(httpRequestHandler);
        httpRequestHandler.setNext(ftpRequestHandler);

        Request httpRequest = new Request("http","web page request");
        defaultHandler.handle(httpRequest);

        Request ftpRequest = new Request("ftp","download file request");
        defaultHandler.handle(ftpRequest);

        Request dnsRequest = new Request("dns","request domain service");
        defaultHandler.handle(dnsRequest);

    }
}
