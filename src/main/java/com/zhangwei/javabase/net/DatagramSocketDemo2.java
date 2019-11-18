package com.zhangwei.javabase.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class DatagramSocketDemo2 {
    public static void main(String[] args) {
        contact();
    }

    private static void contact() {
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
        try (DatagramSocket socket = new DatagramSocket(9997);){
            while (true){
                socket.receive(packet);
                String message = new String(packet.getData(),0,packet.getLength(), StandardCharsets.UTF_8.toString());
                if(message.equals("-1\n")){
                    break;
                }
                System.out.println(message);
                buffer = new byte[1024];
                int len = System.in.read(buffer);
                DatagramPacket sendPack = new DatagramPacket(buffer,len, packet.getAddress(),packet.getPort());
                socket.send(sendPack);
                if(new String(buffer,0,len, StandardCharsets.UTF_8.toString()).equals("-1\n")){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
