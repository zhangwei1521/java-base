package com.zhangwei.javabase.jvm;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 测试JDK动态代理
 */
public class JvmDemo10 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MyLogWriter myLogWriter = new MyLogWriter();
        LogWriter proxyLogWriter = (LogWriter)new LogProxyProvider().bind(myLogWriter);
        System.out.println(proxyLogWriter);
        proxyLogWriter.writeLog("IO error happened");
    }

    interface LogWriter{
        boolean writeLog(String message);
    }

    static class MyLogWriter implements LogWriter{
        @Override
        public boolean writeLog(String message) {
            System.out.println("error log : "+message);
            return true;
        }
    }

    static class LogProxyProvider implements InvocationHandler{

        Object originObject;

        public Object bind(Object originObject) {
            this.originObject = originObject;
            return Proxy.newProxyInstance(originObject.getClass().getClassLoader(),
                    originObject.getClass().getInterfaces(), this);
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if(method.getName().equals("writeLog")) {
                System.out.println(proxy);
                System.out.println("warning....");
            }

            return method.invoke(originObject, args);
        }

    }
}
