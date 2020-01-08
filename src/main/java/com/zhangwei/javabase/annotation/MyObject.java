package com.zhangwei.javabase.annotation;

@MyClassAnno(str = "newStr",val = 512)
public class MyObject{
    @MyFieldAnno("子项")
    private Object item;

    @MyMeghodAnno(value = "查询",logging = false)
    public void method1(){
        System.out.println("method1 doing...");
    }
}