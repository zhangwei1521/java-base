package com.zhangwei.javabase.util;

public class LanguageGrammerDemo1 {
    public static void main(String[] args) {
        String s1 = "she";
        switch (s1){
            case "he":
                System.out.println("man");
                break;
            case "she":
                System.out.println("woman");
                System.out.println("girl");
                break;
            default:
                break;
        }

    }
}
