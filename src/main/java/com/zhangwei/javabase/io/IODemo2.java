package com.zhangwei.javabase.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class IODemo2 {
    public static void main(String[] args) throws IOException {
        //testFileInputStream();
        testFileOutputStream();
    }

    private static void testFileInputStream() throws IOException {
        File f1 = new File("/tem_file/file1");
        if(f1.exists()){
            FileInputStream fin1 = new FileInputStream(f1);
            System.out.println("available bytes: "+fin1.available());
            int result = fin1.read();
            System.out.printf("read : %c, left length : %d \n",(char)result,fin1.available());
            byte[] buffer = new byte[9];
            result = fin1.read(buffer);
            System.out.printf("read : %s, left length : %d \n", new String(buffer,0,buffer.length),fin1.available());
            // FileInputStream 不支持 mark 和 reset 方法
            if(fin1.markSupported()){
                System.out.println("mark and skip");
                fin1.mark(10);
                fin1.skip(6);
            }
            else {
                fin1.skip(2);
            }
            result = fin1.read(buffer,5,4);
            System.out.printf("read : %s, left length : %d \n",new String(buffer,0,buffer.length),fin1.available());
            if(fin1.markSupported()){
                fin1.reset();
            }
            result = fin1.read();
            System.out.printf("read : %c, left length : %d \n",(char)result,fin1.available());            System.out.println("-----");
            fin1.close();
        }
    }

    private static void testFileOutputStream() throws IOException{
        try ( FileOutputStream fout1 = new FileOutputStream("/tem_file/file1",false); ){
            //直接写数字则需要写入的是字符的 urf8 码点
            //fout1.write(1);
            //byte[] content = {2,3,4,5,6,7,8,9};
            fout1.write('1');
            fout1.write(50);
            byte[] content = {'3','4','5','6','7','8','9'};
            fout1.write(content);
            //这里 content 不能重复使用
            byte[] newContent = {'a','b','c','d','e','f','g'};
            fout1.write(newContent,0,newContent.length);
        } catch (IOException e){
            throw e;
        }
        String str = "hijklmn";
        byte [] buffer = str.getBytes();
        //
        File file1 = new File("/tem_file/file1");
        if(file1.exists()){
            FileOutputStream fout1 = new FileOutputStream(file1,true);
            fout1.write(buffer);
            fout1.close();
        }
        else {
            FileOutputStream fout1 = new FileOutputStream(file1,false);
            fout1.write(buffer,0,buffer.length);
            fout1.close();
        }
    }


}
