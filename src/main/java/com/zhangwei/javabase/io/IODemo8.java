package com.zhangwei.javabase.io;

import java.io.*;

public class IODemo8 {
    public static void main(String[] args) throws IOException {
        useFileInputStream();
        useBufferedInputStream();
        useBufferedReader();
    }

    private static void useFileInputStream() throws IOException {
        long start,end;
        int c;
        byte[] buffer = new byte[1024];
        FileInputStream fileInputStream;

        System.out.println("========Use FileInputStream========");
        //每次读取单个字节非常慢
        /*
        System.out.println("without byte array");
        start = System.currentTimeMillis();
        fileInputStream = getFileInput();
        while ((c=fileInputStream.read())!=-1){

        }
        fileInputStream.close();
        end = System.currentTimeMillis();
        System.out.println("time:  "+(end-start));
        */
        System.out.println("with byte array");
        start = System.currentTimeMillis();
        fileInputStream = getFileInput();
        while ((c=fileInputStream.read(buffer))!=-1){
            //使用字节数组比一次只读一个字节要快
        }
        fileInputStream.close();
        end = System.currentTimeMillis();
        System.out.println("time:  "+(end-start));

    }

    private static void useBufferedInputStream() throws IOException {
        long start,end;
        int c;
        byte[] buffer = new byte[1024];
        FileInputStream fileInputStream;

        System.out.println("========Use BufferedInputStream========");
        start = System.currentTimeMillis();
        fileInputStream = getFileInput();
        BufferedInputStream bufInput = new BufferedInputStream(fileInputStream);
        while ((c=bufInput.read(buffer))!=-1){
            //使用缓冲区读取要比直接从文件流中读取快
            //BufferedInputStream重写了方法: read(byte b[], int off, int len)
        }
        bufInput.close();
        end = System.currentTimeMillis();
        System.out.println("time:  "+(end-start));

    }

    private static void useBufferedReader() throws IOException {
        long start,end;
        int c;
        char[] buffer = new char[1024];

        System.out.println("========Use BufferedReader========");
        start = System.currentTimeMillis();
        BufferedReader reader = new BufferedReader(new FileReader("/tem_file/rain.mp3"),8096);
        while ((c=reader.read(buffer))!=-1){
            //Reader需要解析字符，所以要比InputStream慢很多
        }
        reader.close();
        end = System.currentTimeMillis();
        System.out.println("time:  "+(end-start));
    }

    private static FileInputStream getFileInput(){
        File file = new File("/tem_file/rain.mp3");
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


}
