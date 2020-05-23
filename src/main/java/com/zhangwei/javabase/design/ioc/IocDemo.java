package com.zhangwei.javabase.design.ioc;

/**
 * 基本的IOC原理和一个基于注解和类路径扫描的IOC容器实现
 */
public class IocDemo {
    public static void main(String[] args) {
        //test01();
        test02();
    }

    private static void test01(){
        //简单的IOC容器
        SimpleContainer container = new SimpleContainer();
        Service1 service1 = container.getBean(Service1.class);
        service1.doService();
    }

    private static void test02(){
        //自动扫描注解的IOC容器
        NewIocContainer container = new NewIocContainer();
        Service1 service1 = container.getBean(Service1.class);
        service1.doService();
    }
}
