package com.zhangwei.javabase.asm;

class MyObjectClassLodaer extends ClassLoader{
    public Class defineClassFromBytes(String className,byte[] bytes){
        return defineClass(className,bytes,0,bytes.length);
    }
}
