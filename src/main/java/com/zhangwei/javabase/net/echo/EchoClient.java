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
        String msg = buildMessage(Arrays.copyOfRange(args,1,args.length));
        //echo(msg);
        int threadQuantity = Integer.valueOf(args[0]);
        multiEcho(threadQuantity,msg);
    }

    private static String buildMessage(String[] args){

        StringBuffer str = new StringBuffer();
        for (String arg : args) {
            str.append(arg).append(" ");
        }
        /*str = new StringBuffer();
        for(int i=0;i<1000;i++){
            str.append(i).append(",");
        }*/
        return str.toString();
    }

    private static void echo(String message) throws IOException {
        try(Socket socket = new Socket("127.0.0.1",9999);
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream())
        {
            byte[] data = message.getBytes();
            output.write(data);
            output.flush();
            int len;
            int receiveTotal = 0;
            while (receiveTotal < data.length){
                if((len = input.read(data,receiveTotal,data.length-receiveTotal)) != -1){
                    receiveTotal += len;
                }
                else {
                    throw new RuntimeException("connection closed");
                }
            }
            System.out.println(new String(data, StandardCharsets.UTF_8.toString()));
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
