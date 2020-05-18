package com.zhangwei.javabase.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 测试JDK动态代理
 */
public class DynamicProxyDemo {

    public static void main(String[] args) {
        LogWriter myLogWriter = new MyLogWriter();
        System.out.println(myLogWriter);
        LogWriter proxyLogWriter = LogProxyFactory.getLogProxy(myLogWriter);
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

    static class LogProxyFactory {


        static class DefaultInvocationHandler implements InvocationHandler{
            Object originObject;

            DefaultInvocationHandler(Object originObject){
                this.originObject = originObject;
            }

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(method.getName().equals("writeLog")) {
                    System.out.println(proxy);
                    System.out.println(Proxy.isProxyClass(proxy.getClass()));
                    System.out.println(Proxy.getProxyClass(originObject.getClass().getClassLoader(),originObject.getClass().getInterfaces()));
                    System.out.println(proxy.getClass());
                    System.out.println("warning....");
                }

                return method.invoke(originObject, args);
            }
        }


        public static LogWriter getLogProxy(LogWriter originObject) {
            DefaultInvocationHandler handler = new DefaultInvocationHandler(originObject);
            return (LogWriter)Proxy.newProxyInstance(originObject.getClass().getClassLoader(),
                    originObject.getClass().getInterfaces(), handler);
        }


    }
}
