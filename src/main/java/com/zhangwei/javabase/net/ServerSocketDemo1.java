package com.zhangwei.javabase.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ServerSocketDemo1 {

    public static void main(String[] args) {
        dealRequest();
    }

    private static void dealRequest(){
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            int count = 0;
            while (count++<5){
                System.out.println("no request,waiting...");
                Socket socket = serverSocket.accept();
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();
                while (true){
                    int len;
                    byte[] buffer = new byte[1024];
                    StringBuffer str = new StringBuffer();
                    len=in.read(buffer);
                    str.append(new String(buffer,0,len, StandardCharsets.UTF_8.toString()));
                    if(str.toString().equals("-1\n")){
                        break;
                    }
                    System.out.println("get request message from "+socket.getInetAddress()+" : "+str.toString());
                    System.out.println("please input message to response:");
                    len = System.in.read(buffer);
                    out.write(new String(buffer,0,len, StandardCharsets.UTF_8.toString()).getBytes(StandardCharsets.UTF_8.toString()));
                }
                in.close();
                out.close();
                socket.close();
            }

            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
