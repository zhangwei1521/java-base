package com.zhangwei.javabase.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.CharBuffer;

public class IODemo6 {

    public static void main(String[] args) {
        //testFileReader();
        testFileWriter();
        //testPrintWriter();
    }

    private static void testFileReader(){
        try (FileReader fileReader = new FileReader("/tem_file/file1");
        ) {
            System.out.println("is ready ? "+fileReader.ready());
            System.out.println("encoding : "+fileReader.getEncoding());
            System.out.println("support mark ? "+fileReader.markSupported());//false

            char c = (char)fileReader.read();
            System.out.println("first char : "+c);

            fileReader.skip(2);
            c = (char)fileReader.read();
            System.out.println("forth char : "+c);

            char[] buf = new char[5];
            int count = fileReader.read(buf,0,buf.length);
            System.out.println(String.valueOf(buf,0,count));

            CharBuffer cbuffer = CharBuffer.allocate(10);
            count = fileReader.read(cbuffer);
            System.out.println(cbuffer.toString());

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void testFileWriter(){
        //FileWriter使用默认编码 utf-8
        try (FileWriter fileWriter = new FileWriter("/tem_file/file3-2")){
            System.out.println("encoding : "+fileWriter.getEncoding());
            fileWriter.write(49);
            fileWriter.write("2345".toCharArray());
            fileWriter.write("6789");
            fileWriter.write("10abcd",2,4);
            fileWriter.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
        //append 必须要在 FileWriter 为append模式下才能起到追加的作用
        try (FileWriter fileWriter = new FileWriter("/tem_file/file3-2",true)){
            fileWriter.append("efg");
            fileWriter.append("中文");
            fileWriter.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void testPrintWriter(){
        try (PrintWriter writer = new PrintWriter("/tem_file/file3-3","GBK");){
            writer.write("中国\n");
            writer.append("美国\n");
            writer.print("日本\n");
            writer.printf("this year is %s","2019");
            writer.format("this month is %d",5);
        } catch (IOException e){
            e.printStackTrace();
        }
    }


}
