package com.zhangwei.javabase.spi.impl;

import com.zhangwei.javabase.spi.inteface.LogWriter;

public class SimpleLogWriter implements LogWriter {
    @Override
    public void log(String message) {
        System.out.println(message);
    }
}
