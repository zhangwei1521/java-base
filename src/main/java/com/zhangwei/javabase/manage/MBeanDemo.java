package com.zhangwei.javabase.manage;

import javax.management.*;
import java.lang.management.ManagementFactory;

//jvm参数
//-Dcom.sun.management.jmxremote.port=9999 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false
public class MBeanDemo {
    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        NameService nameService = new NameService("john");
        IdService idService = new IdService();

        NameServiceMBeanAgent nameServiceAgent = new NameServiceMBeanAgent(nameService);
        IdServiceMBeanAgent idServiceMBeanAgent = new IdServiceMBeanAgent(idService);

        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName nameServiceMBeanName = new ObjectName("com.zhangwei.javabase.mbean:id=nameServiceMBean");
        mBeanServer.registerMBean(nameService,nameServiceMBeanName);
        ObjectName idServiceMBeanName = new ObjectName("com.zhangwei.javabase.mbean:id=idServiceMBean");
        mBeanServer.registerMBean(idService,idServiceMBeanName);

        nameService.getName();
        idService.getNewId();
        while (true);
    }
}
