package com.zhangwei.javabase.ioc;

public class IocDemo1 {
    public static void main(String[] args) {
        SimpleContainer container = new SimpleContainer();
        Service1 service1 = container.getBean(Service1.class);
        service1.doService();
    }
}
