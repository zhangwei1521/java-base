package com.zhangwei.javabase.io;


import java.io.*;

public class IODemo5 {
    public static void main(String[] args) throws IOException {
        //testByteArrayInputStream();
        //testByteArrayOutputStream();
    }

    private static void testByteArrayInputStream(){
        String str = "abcdefghijklmn";
        byte [] source = str.getBytes();
        ByteArrayInputStream baIn1 = new ByteArrayInputStream(source);
        ByteArrayInputStream baIn2 = new ByteArrayInputStream(source,0,source.length);
        int c;
        while ((c = baIn2.read()) != -1){
            if(c=='h'){
                //reset后是从 h 后的下一字节开始读，即 i
                baIn2.mark(1000);
                System.out.print(Character.toUpperCase((char)c));
            }
            else {
                System.out.print((char)c);
            }
        }
        baIn2.reset();
        c = baIn2.read();
        System.out.println("\n"+(char)c);
        System.out.println("--------");
    }

    private static void testByteArrayOutputStream() throws IOException {
        ByteArrayOutputStream baout = new ByteArrayOutputStream(3);
        String str = "123456789abcdefghijklmn";
        byte [] buffer = str.getBytes();
        baout.write(buffer);
        System.out.println(baout.size());
        System.out.println(baout.toString());
        byte [] bytes = baout.toByteArray();
        for(int i=0;i<bytes.length;i++) System.out.print((char)bytes[i]);
        System.out.println();

        FileOutputStream fout = new FileOutputStream("/tem_file/file1");
        baout.writeTo(fout);
        fout.flush();
        fout.close();

        //reset 后可以重新从开始位置写入
        baout.reset();
        for(int i=0;i<3;i++) baout.write('X');
        System.out.println(baout.toString());
    }


}
