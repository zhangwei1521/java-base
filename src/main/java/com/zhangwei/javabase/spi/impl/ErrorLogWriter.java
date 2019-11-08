package com.zhangwei.javabase.spi.impl;

import com.zhangwei.javabase.spi.inteface.LogWriter;

public class ErrorLogWriter implements LogWriter {
    private String beforeMsg;

    public ErrorLogWriter(){}

    public ErrorLogWriter(String msg){
        this.beforeMsg=msg;
    }

    @Override
    public void log(String message) {
        System.err.println(message);
    }
}
