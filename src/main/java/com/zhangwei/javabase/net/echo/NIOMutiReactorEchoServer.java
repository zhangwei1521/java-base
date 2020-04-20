package com.zhangwei.javabase.net.echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 这种多Reactor模式，ServerSocketChannel支持的最大连接数还是受机器限制
 * 如果并发连接太多，反而造成性能不如单Reactor和单线程模式
 * 500并发
 * 1 s 502996400 ns
 */
public class NIOMutiReactorEchoServer {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(9999),500);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        DataReactor[] reactors = new DataReactor[Runtime.getRuntime().availableProcessors()];
        for(int i=0;i<reactors.length;i++){
            reactors[i] = new DataReactor();
        }

        while (selector.select() > 0) {
            Set<SelectionKey> keySet = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keySet.iterator();
            int i=0;
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                if(key.isAcceptable()){
                    ServerSocketChannel acceptServerSocketChannel = (ServerSocketChannel)key.channel();
                    SocketChannel socketChannel = acceptServerSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    DataReactor reactor = reactors[(i++)%reactors.length];
                    reactor.addChannel(socketChannel);
                    reactor.wakeup();
                }
            }
        }
    }
}

class DataReactor{

    private static final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private Selector selector;

    public DataReactor() throws IOException {
        this.selector = SelectorProvider.provider().openSelector();
        start();
    }

    public void start() {
        executor.submit(()->{
           while (true){
               /*if(this.selector.select(500) < 0){
                   continue;
               }*/
               if(selector.selectNow() < 0){
                   continue;
               }
               Set<SelectionKey> keySet = this.selector.selectedKeys();
               Iterator<SelectionKey> iterator = keySet.iterator();
               while (iterator.hasNext()) {
                   SelectionKey key = iterator.next();
                   iterator.remove();
                   if(key.isValid() && key.isReadable()){
                       SocketChannel socketChannel = (SocketChannel) key.channel();
                       synchronized (socketChannel){
                           if(!socketChannel.isOpen()){
                               return null;
                           }
                           StringBuffer strBuf = new StringBuffer();
                           int count = 0;
                           ByteBuffer buffer = ByteBuffer.allocate(1024);
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
        });
    }

    public void addChannel(SocketChannel socketChannel) throws ClosedChannelException {
        socketChannel.register(this.selector,SelectionKey.OP_READ);
    }

    public void wakeup() {
        this.selector.wakeup();
    }
}
