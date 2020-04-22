package com.zhangwei.javabase.util;

public class MyArrayUtil {
    public static String toString(Object[] array){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        if(array==null){
            stringBuffer.append("]");
        }
        else {
            for(int i=0;i < array.length;i++){
                stringBuffer.append(array[i]);
                stringBuffer.append(", ");
            }
            stringBuffer.replace(stringBuffer.length()-2,stringBuffer.length(),"");
            stringBuffer.append("]");
        }
        return stringBuffer.toString();
    }

    public static String toString(int[] array){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        if(array==null){
            stringBuffer.append("]");
        }
        else {
            for(int i=0;i < array.length;i++){
                stringBuffer.append(array[i]);
                stringBuffer.append(", ");
            }
            stringBuffer.replace(stringBuffer.length()-2,stringBuffer.length(),"");
            stringBuffer.append("]");
        }
        return stringBuffer.toString();
    }
}
