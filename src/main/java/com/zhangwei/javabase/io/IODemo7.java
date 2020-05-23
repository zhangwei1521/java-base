package com.zhangwei.javabase.io;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class IODemo7 {
    public static void main(String[] args) {
        testRandomAccessFile();
    }
    private static void testRandomAccessFile(){
        try {
            RandomAccessFile rf = new RandomAccessFile("/tem_file/testfile2","rwd");
            System.out.println("now at : "+rf.getFilePointer());
            int a = rf.readInt();
            System.out.println("now at : "+rf.getFilePointer());

            rf.seek(5);
            System.out.println("now at : "+rf.getFilePointer());

            char c = rf.readChar();
            System.out.println(a+" , "+c);

            System.out.println("now length : "+rf.length());
            rf.setLength(5);
            System.out.println("now length : "+rf.length());

            System.out.println("now at : "+rf.getFilePointer());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
