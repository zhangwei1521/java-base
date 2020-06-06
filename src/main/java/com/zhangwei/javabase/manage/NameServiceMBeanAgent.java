package com.zhangwei.javabase.manage;

import javax.management.AttributeChangeNotification;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import java.util.concurrent.atomic.AtomicInteger;

public class NameServiceMBeanAgent extends NotificationBroadcasterSupport implements NameServiceMBean {
    private long start;
    private NameService nameService;
    private AtomicInteger sequence = new AtomicInteger(0);

    public NameServiceMBeanAgent(NameService nameService){
        this.nameService = nameService;
        start = System.currentTimeMillis();
        this.nameService.setNameServiceMBean(this);
    }

    @Override
    public long getUptime() {
        return System.currentTimeMillis()-start;
    }

    @Override
    public String getBaseInfo(){
        return "{id: "+nameService.getId()+", basename: "+nameService.getBaseName()+"}";
    }

    @Override
    public boolean update(String s){
        nameService.setBaseName(s);
        return true;
    }

    @Override
    public void notifyAttributeChange(String attribute, String type, Object oldValue, Object newValue) {

    }
}
