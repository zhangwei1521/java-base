package com.zhangwei.javabase.manage;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.util.Set;

public class MBeanClient {
    public static void main(String[] args) throws IOException, MalformedObjectNameException, InstanceNotFoundException {
        JMXServiceURL jmxServiceURL = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://:9999/jmxrmi");
        JMXConnector jmxConnector = JMXConnectorFactory.connect(jmxServiceURL,null);

        MBeanServerConnection mbsConnection = jmxConnector.getMBeanServerConnection();
        System.out.println("default domain : "+mbsConnection.getDefaultDomain());
        System.out.println("mbean count : "+mbsConnection.getMBeanCount());
        /*Set<ObjectName> objectNames = mbsConnection.queryNames(null,null);
        for (ObjectName objectName : objectNames) {
            System.out.println(objectName);
        }*/

        //注册监听器可以让客户端在MBean产生特定事件时收到通知，这要求MBean实现NotificationBroadcaster接口
        NotificationListener listener = new NameServiceAgentMBeanListener();
        ObjectName nameServiceMBeanName = new ObjectName("com.zhangwei.javabase.mbean:id=nameServiceMBean");
        mbsConnection.addNotificationListener(nameServiceMBeanName,listener,null,null);

        //通过MBean代理可以让客户端访问操作MBean的数据
        NameServiceMBean nameServiceMBeanProxy = JMX.newMBeanProxy(mbsConnection,nameServiceMBeanName, NameServiceMBean.class,true);
        System.out.println("baseInfo : "+nameServiceMBeanProxy.getBaseInfo());
        System.out.println("uptime : "+nameServiceMBeanProxy.getUptime());
        nameServiceMBeanProxy.update("mike");
        while (true);
    }

    public static class NameServiceAgentMBeanListener implements NotificationListener{

        @Override
        public void handleNotification(Notification notification, Object handback) {
            System.out.println("notification source : "+notification.getSource());
            System.out.println("notification type : "+notification.getType());
            System.out.println("notification message : "+notification.getMessage());
            if(notification instanceof AttributeChangeNotification){
                AttributeChangeNotification attributeChangeNotification = (AttributeChangeNotification)notification;
                System.out.println("attr name : "+attributeChangeNotification.getAttributeName());
                System.out.println("attr type : "+attributeChangeNotification.getAttributeType());
                System.out.println("old value : "+attributeChangeNotification.getOldValue());
                System.out.println("new value : "+attributeChangeNotification.getNewValue());
            }
        }
    }
}
