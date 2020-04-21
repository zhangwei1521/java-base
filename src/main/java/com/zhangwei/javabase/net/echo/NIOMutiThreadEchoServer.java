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
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 500并发
 * 0 s 921919500 ns
 * 0 s 718786000 ns
 * 0 s 593775400 ns
 */
public class NIOMutiThreadEchoServer {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(9999),500);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            if(selector.selectNow() < 0){
                continue;
            }
            Set<SelectionKey> keySet = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keySet.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                if(!key.isValid()){
                    continue;
                }
                if(key.isAcceptable()){
                    ServerSocketChannel acceptServerSocketChannel = (ServerSocketChannel)key.channel();
                    SocketChannel socketChannel = acceptServerSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    SelectionKey readKey = socketChannel.register(selector,SelectionKey.OP_READ);
                    readKey.attach(Processor.getInstance());
                }
                else if(key.isReadable()){
                    Processor processor = (Processor)key.attachment();
                    processor.process(key);
                }
            }
        }
    }
}

class Processor{

    private static final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    //private Processor(){}

    private static Processor instance = new Processor();

    public static Processor getInstance(){
        return instance;
    }

    public void process(SelectionKey key) {
        executor.submit(()->{
            SocketChannel socketChannel = (SocketChannel) key.channel();
            try {
                synchronized (socketChannel){
                    if(!socketChannel.isOpen()){
                        return null;
                    }
                    StringBuffer strBuf = new StringBuffer();
                    int count = 0;
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
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
                    key.cancel();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        });

    }
}

