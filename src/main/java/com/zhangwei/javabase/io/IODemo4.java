package com.zhangwei.javabase.io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class IODemo4 {
    public static void main(String[] args) throws IOException {
        while (true){
            System.out.println("please input message to send:");
            byte [] buffer = new byte[1024];
            int len;
            StringBuffer str;
            while ((len = System.in.read(buffer)) != -1){
                str = new StringBuffer();
                str.append(new String(buffer,0,len, StandardCharsets.UTF_8.toString()));
                if(str.toString().equals("-1")){
                    break;
                }
                System.out.println(str.toString());
            }

        }
    }
}
