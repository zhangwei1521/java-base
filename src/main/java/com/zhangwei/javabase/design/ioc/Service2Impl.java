package com.zhangwei.javabase.design.ioc;

@Bean
public class Service2Impl implements Service2 {
    @Override
    public void doCreate(Object obj) {
        System.out.println("create object success: "+obj.toString());
    }
}
