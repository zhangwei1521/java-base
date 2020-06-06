package com.zhangwei.javabase.manage;

public interface ServiceMBean {
    long getUptime();

    String getBaseInfo();

    boolean update(String s);
}
