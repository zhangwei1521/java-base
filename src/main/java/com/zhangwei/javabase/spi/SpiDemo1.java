package com.zhangwei.javabase.spi;

import com.zhangwei.javabase.spi.inteface.LogHandler;
import com.zhangwei.javabase.spi.inteface.LogWriter;

import java.util.ServiceLoader;

public class SpiDemo1 {
    public static void main(String[] args) {
        //test01();
        test02();
    }

    private static void test01(){
        ServiceLoader<LogWriter> logWriterList = ServiceLoader.load(LogWriter.class);
        for(LogWriter logWriter : logWriterList){
            logWriter.log("SpiDemo1.....test01...");
        }
    }

    private static void test02(){
        ServiceLoader<LogHandler> logWriterList = ServiceLoader.load(LogHandler.class);
        for(LogHandler logHandler : logWriterList){
            logHandler.handleLog("SpiDemo1.....test02...");
            System.out.println(logHandler.getClass().getClassLoader());
        }
    }
}
