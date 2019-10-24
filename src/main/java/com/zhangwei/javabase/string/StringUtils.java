package com.zhangwei.javabase.string;

public class StringUtils {
    public static boolean isEmpty(String str){
        boolean result = false;
        if(str==null || str.length()==0){
            result = true;
        }
        return result;
    }

    public static boolean isNotEmpty(String str){
        boolean result = true;
        if(str==null || str.length()==0){
            result = false;
        }
        return result;
    }

    public static boolean equals(String str1, String str2){
        boolean result = false;
        if(str1==null && str2==null){
            result = true;
        }
        if(str1!=null && str2!=null && str1.equals(str2)){
            result = true;
        }
        return result;
    }
}
