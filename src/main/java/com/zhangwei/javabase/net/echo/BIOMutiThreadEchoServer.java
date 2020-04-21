package com.zhangwei.javabase.net.echo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * BIO多线程
 * 这种处理时间非常短的场景下，请求数量不多时，多线程效率比多线程还要低，
 * 但是请求增加后，多线程的优势就显现出来了
 * 500请求：time :
 * 第一次： 0 s 890667200 ns
 * 第二次： 0 s 703154300 ns
 * 第三次： 0 s 671905600 ns
 */
public class BIOMutiThreadEchoServer {
    public static void main(String[] args) throws IOException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor)Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        executorService.prestartAllCoreThreads();
        ServerSocket serverSocket = new ServerSocket(9999);
        while (true) {
            Socket socket = serverSocket.accept();
            executorService.submit(()->{
                try (InputStream input = socket.getInputStream();
                     OutputStream output = socket.getOutputStream();
                     ){
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
                } catch (IOException e){
                    e.printStackTrace();
                }
            });
        }
    }
}
