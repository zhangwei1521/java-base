package com.zhangwei.javabase.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketDemo2 {

    public static void main(String[] args) {
        contactServer();
    }

    private static void contactServer(){
        try {
            Socket socket = new Socket("127.0.0.1",9999);
            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();

            byte [] buffer = new byte[1024];
            int len;
            StringBuffer str;
            System.out.println("please input message to send: ");
            while ((len = System.in.read(buffer)) != -1){
                str = new StringBuffer();
                str.append(new String(buffer,0,len, StandardCharsets.UTF_8.toString()));
                out.write(str.toString().getBytes(StandardCharsets.UTF_8.toString()));
                if(str.toString().equals("-1\n")){
                    break;
                }
                len = in.read(buffer);
                str = new StringBuffer();
                str.append(new String(buffer,0,len, StandardCharsets.UTF_8.toString()));
                System.out.println("message from server: "+str.toString());
                System.out.println("please input message to send: ");
            }

            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
