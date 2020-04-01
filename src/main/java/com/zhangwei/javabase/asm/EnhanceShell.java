package com.zhangwei.javabase.asm;

public class EnhanceShell{
    public static void start(){
    }
    public static void end(long start){
        System.out.println("method done with time : "+(System.currentTimeMillis()-start));
    }
}
