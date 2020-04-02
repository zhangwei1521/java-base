package com.zhangwei.javabase.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class URLDemo {
    public static void main(String[] args){
        //testLocalURL();
        testURLConnection();
    }

    private static void testLocalURL(){
        URL url = Thread.currentThread().getClass().getResource("/database.properties");
        System.out.println(url);
        System.out.println(url.getFile());
        System.out.println(url.getFile().substring(1));
    }

    private static void testURLConnection(){
        try {
            URL url = new URL("http://www.internic.net");
            url = Thread.currentThread().getClass().getResource("/database.properties");
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
}
