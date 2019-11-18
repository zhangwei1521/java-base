package com.zhangwei.javabase.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class DatagramSocketDemo1 {
    public static void main(String[] args) {
        contact();
    }

    private static void contact(){
        try (DatagramSocket socket = new DatagramSocket(9998)){
            System.out.println(socket.getLocalAddress());
            System.out.println(socket.getLocalPort());
            System.out.println(socket.getInetAddress());
            System.out.println(socket.getPort());
            while (true){
                byte[] buffer = new byte[1024];
                int len = System.in.read(buffer);
                DatagramPacket packet = new DatagramPacket(buffer,len, InetAddress.getLocalHost(),9997);
                socket.send(packet);
                if(new String(buffer,0,len, StandardCharsets.UTF_8.toString()).equals("-1\n")){
                    break;
                }
                buffer = new byte[1024];
                DatagramPacket receivePack = new DatagramPacket(buffer,buffer.length);
                socket.receive(receivePack);
                String message = new String(receivePack.getData(),0,receivePack.getLength(), StandardCharsets.UTF_8.toString());
                if(message.equals("-1\n")){
                    break;
                }
                System.out.println(message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
