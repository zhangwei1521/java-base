package com.zhangwei.javabase.manage;

public class IdServiceMBeanAgent implements IdServiceMBean {
    private long start;
    private IdService idService;

    public IdServiceMBeanAgent(IdService idService){
        this.idService = idService;
        start = System.currentTimeMillis();
        this.idService.setIdServiceMBean(this);
    }

    @Override
    public long getUptime() {
        return System.currentTimeMillis()-start;
    }

    @Override
    public String getBaseInfo(){
        return idService.getId()+"";
    }

    @Override
    public boolean update(String s){
        return false;
    }
}
