package com.zhangwei.javabase.design.adapter;

import java.util.List;

public class ServiceAdapter implements ClientPreferStyleService {

    private LocalStyleService localStyleService;

    public ServiceAdapter(LocalStyleService localStyleService){
        this.localStyleService = localStyleService;
    }

    @Override
    public List<Object> selectList() {
        return localStyleService.getList();
    }
}
