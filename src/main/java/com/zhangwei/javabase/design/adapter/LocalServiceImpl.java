package com.zhangwei.javabase.design.adapter;

import java.util.List;

public class LocalServiceImpl implements LocalStyleService {
    @Override
    public List<Object> getList() {
        System.out.println("getList return null");
        return null;
    }
}
