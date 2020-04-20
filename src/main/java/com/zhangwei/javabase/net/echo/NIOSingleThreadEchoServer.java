package com.zhangwei.javabase.net.echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

/**
 * 使用NIO单线程
 * 500请求：time :
 * 第一次： 0 s 734405000 ns
 * 第二次： 0 s 755522800 ns
 * 第三次： 0 s 726482200 ns
 */
public class NIOSingleThreadEchoServer {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(9999));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0){
            Set<SelectionKey> keySet = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keySet.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                if(key.isAcceptable()){
                    ServerSocketChannel acceptServerSocketChannel = (ServerSocketChannel)key.channel();
                    SocketChannel socketChannel = acceptServerSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }
                else if(key.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int count = 0;
                    StringBuffer strBuf = new StringBuffer();
                    while ((count = socketChannel.read(buffer))>0){
                        String str = new String(Arrays.copyOf(buffer.array(),buffer.position()), StandardCharsets.UTF_8.toString());
                        strBuf.append(str);
                        buffer.clear();
                    }
                    String msg = strBuf.toString();
                    //打印消息
                    System.out.println(socketChannel.getRemoteAddress()+" : "+msg);
                    //回写消息
                    byte[] bytes = msg.getBytes();
                    socketChannel.write(ByteBuffer.wrap(bytes));
                    socketChannel.close();
                    key.cancel();
                }
            }
        }
    }
}
