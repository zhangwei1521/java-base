package com.zhangwei.javabase.manage;

import javax.management.AttributeChangeNotification;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import java.util.concurrent.atomic.AtomicLong;

public class NameService extends NotificationBroadcasterSupport implements NameServiceMBean{

    private NameServiceMBean nameServiceMBean;

    public void setNameServiceMBean(NameServiceMBean nameServiceMBean){
        this.nameServiceMBean = nameServiceMBean;
    }

    private AtomicLong id;
    private String baseName;

    public NameService(String baseName){
        this.id = new AtomicLong(1);
        this.baseName = baseName;
    }

    public void setBaseName(String baseName){
        if(baseName.equals(this.baseName)){
            return;
        }
        String oldBaseName = this.baseName;
        this.baseName = baseName;
        this.id = new AtomicLong(1);
        notifyAttributeChange("baseName","String",oldBaseName,baseName);
    }

    public String getBaseName(){
        return baseName;
    }

    public long getId(){
        return id.get();
    }

    public String getName(){
        return baseName+"-"+id.getAndIncrement();
    }

    @Override
    public long getUptime() {
        return nameServiceMBean.getUptime();
    }

    @Override
    public String getBaseInfo() {
        return nameServiceMBean.getBaseInfo();
    }

    @Override
    public boolean update(String s) {
        return nameServiceMBean.update(s);
    }

    @Override
    public void notifyAttributeChange(String attribute, String type, Object oldValue, Object newValue) {
        Notification notification = new AttributeChangeNotification(
                this,
                1,
                System.currentTimeMillis(),
                "attribute change triggered by setBaseName",
                attribute,
                type,
                oldValue,
                newValue);
        sendNotification(notification);
    }
}
