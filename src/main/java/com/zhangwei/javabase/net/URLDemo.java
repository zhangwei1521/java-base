package com.zhangwei.javabase.net;

import com.zhangwei.javabase.io.IODemo7;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.Date;

public class URLDemo {
    public static void main(String[] args) throws IOException {
        //testLocalURL();
        testURLConnection();
        //testDownload();
    }

    private static void testLocalURL(){
        //class.getResource默认从class对应的class文件所在位置加载，"/"表示当前类所在 classpath 根路径
        URL url1 = IODemo7.class.getResource("/");
        URL url2 = IODemo7.class.getResource("");
        System.out.println(url1);
        System.out.println(url2);
        //class.getClassLoader().getResource默认就从当前类所在 classpath 根路径处加载，使用"/"做参数则返回null
        URL url3 = IODemo7.class.getClassLoader().getResource("/");
        URL url4 = IODemo7.class.getClassLoader().getResource("");
        System.out.println(url3);
        System.out.println(url4);

        System.out.println("----------------");

        URL url = Thread.currentThread().getClass().getResource("/database.properties");
        System.out.println(url);
        System.out.println(url.getFile());
        System.out.println(url.getFile().substring(1));
    }

    private static void testURLConnection(){
        try {
            URL url = new URL("http://www.internic.net");
            //url = Thread.currentThread().getClass().getResource("/database.properties");
            URLConnection urlConnection = url.openConnection();
            System.out.println(new Date(urlConnection.getDate()));
            System.out.println(urlConnection.getContentType());
            System.out.println(urlConnection.getLastModified());
            long len = urlConnection.getContentLengthLong();
            int c;
            if(len>0){
                InputStream in = urlConnection.getInputStream();
                while ((c=in.read()) != -1){
                    System.out.print((char)c);
                }
                in.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void testDownload() throws IOException {
        String urlStr = "http://localhost/code_lab/react2.7z";
        urlStr = "https://docs.oracle.com/javase/8/javafx/JFXST.pdf";
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Connection","Keep-alive");
        conn.setDoInput(true);
        conn.connect();
        if(conn.getResponseCode()==HttpURLConnection.HTTP_OK){
            BufferedInputStream bufferedIn = new BufferedInputStream(conn.getInputStream());
            ReadableByteChannel channel = Channels.newChannel(bufferedIn);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            ByteBuffer pool = ByteBuffer.allocate(1024*1024);
            int offset = 0;
            int pos = 0;
            String fileName = urlStr.substring(urlStr.lastIndexOf("/")+1);
            String localDir = "d:/tem_file/";
            RandomAccessFile targetFile = new RandomAccessFile(localDir+fileName,"rw");
            FileChannel fileChannel = targetFile.getChannel();
            while (channel.read(buffer)>0){
                int length = buffer.position();
                if(offset+length>pool.capacity()){
                    pool.flip();
                    fileChannel.write(pool,pos);
                    pos += offset;
                    pool.clear();
                    offset = 0;
                }
                pool.position(offset);
                buffer.flip();
                pool.put(buffer);
                offset += length;
                buffer.clear();
            }
            if(pool.position()>0){
                pool.flip();
                fileChannel.write(pool,pos);
                pool.clear();
            }
            fileChannel.close();
            targetFile.close();
            channel.close();
            conn.disconnect();
            System.out.println("end");
        }
    }
}
