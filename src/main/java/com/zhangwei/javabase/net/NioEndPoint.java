package com.zhangwei.javabase.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

public class NioEndPoint {
    private ServerSocketChannel serverSocketChannel;

    public void init() throws IOException {
        if (serverSocketChannel != null) {
            return;
        }
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9999),100);
        serverSocketChannel.configureBlocking(true);
    }

    public void start() throws IOException {
        Poller poller = new Poller();
        Thread pollerThread = new Thread(poller);
        pollerThread.start();
        new Thread(()->{
            while (true){
                try {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    poller.register(socketChannel);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public class Poller implements Runnable{
        private Selector selector;

        public Poller() throws IOException {
            selector = Selector.open();
        }

        @Override
        public void run() {
            while (true){
                try {
                    if(selector.select(1000)>0){
                        Set<SelectionKey> set = selector.selectedKeys();
                        Iterator<SelectionKey> iterator = set.iterator();
                        while (iterator.hasNext()){
                            SelectionKey sk = iterator.next();
                            iterator.remove();
                            SocketChannel socketChannel = (SocketChannel)sk.channel();
                            new Thread(()->{
                                StringBuffer strBuf = new StringBuffer();
                                int count = 0;
                                ByteBuffer buffer = ByteBuffer.allocate(1024);
                                try {
                                    while ((count = socketChannel.read(buffer)) != -1){
                                        if(count > 0){
                                            String str = new String(Arrays.copyOf(buffer.array(),buffer.position()), StandardCharsets.UTF_8.toString());
                                            strBuf.append(str);
                                            buffer.flip();
                                            socketChannel.write(buffer);
                                            buffer.clear();
                                        }
                                    }
                                    String msg = strBuf.toString();
                                    //打印消息
                                    System.out.println(socketChannel.getRemoteAddress()+" : "+msg);
                                    socketChannel.close();
                                } catch (Exception e){
                                    e.printStackTrace();
                                }
                            }).start();
                            sk.cancel();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void register(SocketChannel socketChannel) throws IOException {
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
        }
    }

    public static void main(String[] args) throws IOException {
        NioEndPoint nioEndPoint = new NioEndPoint();
        nioEndPoint.init();
        nioEndPoint.start();
    }
}