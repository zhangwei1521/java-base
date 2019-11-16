package com.zhangwei.javabase.pack1;

public class Demo1 {
    public static void main(String[] args) {
        if(null==null || ((String)null).length()<1){
            System.out.println("null is empty");
        }
        //null 可以进行强制类型转换
        String str = (String)null;
        if(str==null){
            System.out.println("str is null");
        }
    }
}
