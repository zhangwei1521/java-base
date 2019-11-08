package com.zhangwei.javabase.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class IODemo6 {
    public static void main(String[] args) {
        test01();
    }

    private static void test01(){
        //File file = new File("/tem_file/demo1.txt");
        File file = new File("");
        try {
            FileInputStream inputSteam = new FileInputStream(file);
            int i = inputSteam.read();
            while (i!=-1){
                System.out.print(i+" ");
                //  101 109 111 106 105 13 10 101 109 111 106 105
                //System.out.print(((char)i));
                //  emoji
                //  emoji
                //Thread.sleep(5000);
                i=inputSteam.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
