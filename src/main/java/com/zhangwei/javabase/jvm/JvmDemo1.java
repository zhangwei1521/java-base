package com.zhangwei.javabase.jvm;

/**
 * 测试设置堆内存参数
 *      -Xmx100M	//设置堆最大内存为100MB
 * 		-Xms50M		//设置堆初始内存为50MB
 * 		-Xmn30M		//设置堆中新生代内存为30MB
 * 		-Xss128k	//设置每个用户线程的虚拟机栈大小为128KB
 * 		-XX:NewRatio=2			//设置新生代和老年代空间占比为1:2
 * 		-XX:SurvivorRatio=8		//设置Survivor块和Eden块空间占比为1:8
 * 		-XX:+UseSerialGC		//设置新生代使用Serial收集器
 * 	    -verbose:gc -XX:+PrintGCDetails     //在程序运行时打印垃圾回收信息
 */
public class JvmDemo1 {
    //-verbose:gc -XX:+PrintGCDetails -Xmx20M -Xms20M -Xmn10M -XX:SurvivorRatio=8 -XX:+UseSerialGC
    public static void main(String[] args) {
        //2M
        byte[] bArr1 = new byte[2*1024*1024];

    }
}
