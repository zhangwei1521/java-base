package com.zhangwei.javabase.manage;

public interface NameServiceMBean extends ServiceMBean{

    void notifyAttributeChange(String attribute,String type,Object oldValue,Object newValue);
}
