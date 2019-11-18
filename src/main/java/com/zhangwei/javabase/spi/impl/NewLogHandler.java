package com.zhangwei.javabase.spi.impl;

import com.zhangwei.javabase.spi.inteface.LogHandler;

public class NewLogHandler extends LogHandler {
    @Override
    public void handleLog(String log){
        System.err.println("error log message : "+log);
    }
}
