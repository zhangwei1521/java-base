package com.zhangwei.javabase.design.ioc;

import java.util.HashMap;
import java.util.Map;

public class SimpleContainer {
    private Map<Class,Object> beanMap;

    public SimpleContainer(){
        beanMap = new HashMap<>();
        Service2 service2 = new Service2Impl();
        beanMap.put(Service2.class,service2);
        beanMap.put(Service1.class, new Service1Impl(service2));
    }

    public <T> T getBean(Class<T> obj){
        Object bean = beanMap.get(obj);
        return (T)bean;
    }
}
