package com.zhangwei.javabase.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 多线程服务端:每个连接都有一个线程去处理
 */
public class ServerSocketDemo2 {
    public static void main(String[] args) {
        dealRequest();
    }

    private static void dealRequest(){
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(9999);
            while (true){
                System.out.println("no request,waiting...");
                Socket socket = serverSocket.accept();
                System.out.println("connection accept...");
                new Thread(()->{
                    try {
                        InputStream in = socket.getInputStream();
                        OutputStream out = socket.getOutputStream();
                        while (true){
                            int len;
                            byte[] buffer = new byte[1024];
                            StringBuffer str = new StringBuffer();
                            len=in.read(buffer);
                            str.append(new String(buffer,0,len, StandardCharsets.UTF_8.toString()));
                            if(str.toString().equals("-1\r\n")){
                                out.write("-1".getBytes());
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
                        System.out.println("socket closed!");
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
