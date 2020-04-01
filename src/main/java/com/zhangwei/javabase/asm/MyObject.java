package com.zhangwei.javabase.asm;

import com.zhangwei.javabase.annotation.MyClassAnno;
import com.zhangwei.javabase.annotation.MyFieldAnno;
import com.zhangwei.javabase.annotation.MyMeghodAnno;

@MyClassAnno(str = "newStr",val = 512)
public class MyObject{
    @MyFieldAnno("子项")
    private Object item;

    @MyMeghodAnno(value = "查询",logging = true)
    public void go(){
        System.out.println("method1 doing...");
    }
}