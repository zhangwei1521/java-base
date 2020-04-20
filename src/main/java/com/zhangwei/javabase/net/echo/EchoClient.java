package com.zhangwei.javabase.net.echo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class EchoClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        if(args==null || args.length<2){
            System.err.println("usage : run with thread quantity and message to echo");
            System.exit(0);
        }
        int threadQuantity = Integer.valueOf(args[0]);
        String msg = buildMessage(Arrays.copyOfRange(args,1,args.length));
        //echo(msg);
        multiEcho(threadQuantity,msg);
    }

    private static String buildMessage(String[] args){

        StringBuffer str = new StringBuffer();
        for (String arg : args) {
            str.append(arg).append(" ");
        }
        return str.toString();
    }

    private static void echo(String message) throws IOException {
        try(Socket socket = new Socket("127.0.0.1",9999);
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream())
        {
            output.write(message.getBytes());
            output.flush();
            byte[] buffer = new byte[1024];
            int len;
            StringBuffer strBuf = new StringBuffer();
            while ((len = input.read(buffer)) != -1){
                strBuf.append(new String(buffer,0,len, StandardCharsets.UTF_8.toString()));
            }
            System.out.println(strBuf.toString());
        }
    }

    private static void multiEcho(int count,String message) throws InterruptedException {
        LocalTime start = LocalTime.now();
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for(int i=0; i<count;i++){
            final int j= i;
            new Thread(()-> {
                try {
                    echo(j+"-"+message);
                    countDownLatch.countDown();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        countDownLatch.await();
        Duration duration = Duration.between(start,LocalTime.now());
        System.out.println("time : "+duration.getSeconds()+" s "+duration.getNano()+" ns");
    }
}
