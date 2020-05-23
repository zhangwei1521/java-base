package com.zhangwei.javabase.io;

import java.io.Console;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class IODemo4 {
    public static void main(String[] args) throws IOException {
        //testSystemIn();
        testConsole();
    }

    public static void testSystemIn() throws IOException {
        byte [] buffer = new byte[1024];
        int len;
        StringBuffer str;
        //这里 read 在输入回车后才会返回，并且会读到回车
        do{
            System.out.println("please input message to send : (input -1 to exit)");
            len = System.in.read(buffer);
            str = new StringBuffer();
            str.append(new String(buffer,0,len-1, StandardCharsets.UTF_8.toString()));
            if(!str.toString().equals("-1")){
                System.out.println("your input is : \n\t"+str.toString());
            }
        }while (!str.toString().equals("-1"));
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
