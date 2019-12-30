package com.zhangwei.javabase.io;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PipedInputStream;

public class IODemo8 {
    public static void main(String[] args) {
        try {
            test01();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static ByteArrayInputStream getArrayInput(){
        String str = "source of inputStream";
        byte[] source = str.getBytes();
        ByteArrayInputStream input = new ByteArrayInputStream(source);
        return input;
    }

    private static ByteArrayOutputStream getArrayOutput(){
        String str = "source of inputStream";
        byte[] source = str.getBytes();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        output.write(1);
        output.toByteArray();
        return output;
    }

    private static FileInputStream getFileInput(){
        File file = new File("/tem_file/SQL_pro.pdf");
        if(file.exists()){
            try {
                return new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
        else {
            return null;
        }
    }

    private static void test01() throws IOException {
        System.out.println("========test FileInputStream========");
        long start = System.currentTimeMillis();
        System.out.println("start: "+start);
        FileInputStream fileInputStream = getFileInput();
        int c;
        byte[] buffer = new byte[1024];
        while ((c=fileInputStream.read(buffer))!=-1){
            //使用字节数组比一次只读一个字节要快得多
            ;
        }
        fileInputStream.close();
        long end = System.currentTimeMillis();
        System.out.println("end:   "+end);
        System.out.println("time:  "+(end-start));

        System.out.println("========test BufferedInputStream========");
        start = System.currentTimeMillis();
        System.out.println("start: "+start);
        BufferedInputStream bufInput = new BufferedInputStream(getFileInput());
        while ((c=bufInput.read(buffer))!=-1){
            //使用缓冲区读取要比直接从文件中读取快
            ;
        }
        bufInput.close();
        end = System.currentTimeMillis();
        System.out.println("end:   "+end);
        System.out.println("time:  "+(end-start));
        PipedInputStream is;
    }

    private static void test02(){

    }

    private static void test03(){

    }
}
