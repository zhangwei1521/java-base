package com.zhangwei.javabase.manage;

import java.util.concurrent.atomic.AtomicLong;

public class IdService implements IdServiceMBean {

    private IdServiceMBean idServiceMBean;

    public void setIdServiceMBean(IdServiceMBean idServiceMBean){
        this.idServiceMBean = idServiceMBean;
    }

    private AtomicLong id = new AtomicLong(1);

    public long getId(){
        return id.get();
    }

    public long getNewId(){
        return id.getAndIncrement();
    }

    @Override
    public long getUptime() {
        return idServiceMBean.getUptime();
    }

    @Override
    public String getBaseInfo() {
        return idServiceMBean.getBaseInfo();
    }

    @Override
    public boolean update(String s) {
        return idServiceMBean.update(s);
    }
}
