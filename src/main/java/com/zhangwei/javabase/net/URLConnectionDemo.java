package com.zhangwei.javabase.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class URLConnectionDemo {
    public static void main(String[] args){
        testURLConnection();
    }

    private static void testURLConnection(){
        try {
            URL url = new URL("http://www.internic.net");
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
