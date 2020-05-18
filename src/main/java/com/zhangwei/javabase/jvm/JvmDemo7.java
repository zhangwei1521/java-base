package com.zhangwei.javabase.jvm;

/**
 *测试栈溢出
 * java.lang.StackOverflowError
 */
public class JvmDemo7 {
    private int count = 0;

    public void stackLeak() {
        count++;
        stackLeak();
    }

    // -Xss128k
    public static void main(String[] args) {
        JvmDemo7 demo = new JvmDemo7();
        try {
            demo.stackLeak();
        } catch(Throwable e) {
            System.out.println("stack length: "+demo.count);
            throw e;
        }

    }
}


