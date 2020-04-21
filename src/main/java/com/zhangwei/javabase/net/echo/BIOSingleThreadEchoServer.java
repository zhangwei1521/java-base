package com.zhangwei.javabase.net.echo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * BIO单线程
 * 500请求：time :
 * 第一次： 0 s 750032800 ns
 * 第二次： 0 s 625044200 ns
 * 第三次： 0 s 953166600 ns
 */
public class BIOSingleThreadEchoServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        while (true) {
            Socket socket = serverSocket.accept();
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            int len;
            byte[] buffer = new byte[1024];
            StringBuffer strBuf = new StringBuffer();
            //这里接收到客户端的数据就直接返回，当客户端关闭socket时read就返回-1
            while ((len=input.read(buffer)) != -1){
                strBuf.append(new String(buffer,0,len, StandardCharsets.UTF_8.toString()));
                output.write(buffer,0,len);
            }
            String msg = strBuf.toString();
            System.out.println(socket.getRemoteSocketAddress()+" : "+msg);
            input.close();
            output.close();
            socket.close();
        }
    }
}
