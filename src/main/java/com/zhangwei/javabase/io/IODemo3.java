package com.zhangwei.javabase.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.CharArrayWriter;
import java.io.Console;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.CharBuffer;

public class IODemo3 {

    public static void main(String[] args) {
        //testFileReader();
        //testFileWriter();
        //testCharArrayWriter();
        //testPrintWriter();
        testConsole();
    }

    private static void testFileReader(){
        try (FileReader fileReader = new FileReader("/tem_file/file1.txt");
        ) {
            //fileReader.skip(2);
            char c = (char)fileReader.read();
            System.out.println("first char : "+c);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void testFileWriter(){
        try (FileWriter fileWriter = new FileWriter("/tem_file/file1.txt")){
            fileWriter.append("输出");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void testCharArrayWriter(){
        String data = "abcde";
        char [] chars = new char[data.length()];
        data.getChars(0,data.length(),chars,0);
        CharArrayWriter writer = new CharArrayWriter(4);
        writer.write(chars,0,chars.length);

    }
    private static void testBufferedReader(){
        try (BufferedReader reader = new BufferedReader(new FileReader("/tem_file/file1.txt"),9999);
             BufferedWriter writer = new BufferedWriter(new FileWriter("/tem_file/file.txt"))
        ){

        } catch (IOException e){

        }
    }

    private static void testPrintWriter(){
        try (PrintWriter writer = new PrintWriter("/tem_file/file2.txt","GBK");){
            writer.write("中国\n");
            writer.append("美国\n");
            writer.print("日本\n");
            writer.printf("this year is %s","2019");
            writer.format("this month is %d",5);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void testConsole(){
        Console console = System.console();
        if(console == null){
            return;
        }
        String name = console.readLine("please input your %s: ","name");
        char[] password = console.readPassword("please input your %s: ","password");
        if(name.equals("zhangwei") && new String(password).equals("123")){
            console.printf("login success! welcome %s",name);
        }
    }
}
