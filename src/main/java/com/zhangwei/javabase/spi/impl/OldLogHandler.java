package com.zhangwei.javabase.spi.impl;

import com.zhangwei.javabase.spi.inteface.LogHandler;

public class OldLogHandler extends LogHandler {
    @Override
    public void handleLog(String log){
        System.out.println("common log message : "+log);
    }
}
