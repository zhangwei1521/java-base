package com.zhangwei.javabase.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

public class SocketDemo1 {
    public static void main(String[] args) {
        testSocketConnect();
    }

    private static void testSocketConnect(){
        int c;
        try(Socket socket = new Socket("whois.internic.net",43);) {
            System.out.println("local port: "+socket.getLocalPort());
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            String baidu = "baidu.com\n";
            out.write(baidu.getBytes());
            while ((c=in.read()) != -1){
                System.out.print((char)c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
