package com.zhangwei.javabase.string;

import java.util.StringTokenizer;

public class StringTokenizerDemo {
    public static void main(String[] args) {
        //test01();
        test02();
    }

    private static void test01(){
        StringTokenizer stringTokenizer = new StringTokenizer("file1.txt,file2.txt;file3.txt    file4\nfile5 file6", ",; \t\n");
        while (stringTokenizer.hasMoreTokens()){
            System.out.println(stringTokenizer.nextToken());
        }
    }

    private static void test02() {
        StringTokenizer stringTokenizer = new StringTokenizer("file1.txt,file2.txt;file3.txt\tfile4\nfile5 file6", ",; \t\n",true);
        while (stringTokenizer.hasMoreTokens()){
            System.out.println(stringTokenizer.nextToken());
        }
    }

}
