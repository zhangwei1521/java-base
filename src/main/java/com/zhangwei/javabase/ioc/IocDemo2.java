package com.zhangwei.javabase.ioc;

public class IocDemo2 {
    public static void main(String[] args) {
        NewIocContainer container = new NewIocContainer();
        Service1 service1 = container.getBean(Service1.class);
        service1.doService();
    }
}
