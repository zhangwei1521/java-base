package com.zhangwei.javabase.net.okhttp;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class OkHttpDemo1 {
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .build();
        Response response = client.newCall(request).execute();
        Headers headers = response.headers();
        for (int i=0;i<headers.size();i++){
            System.out.println(headers.name(i)+" : "+headers.value(i));
        }
        System.out.println();
        System.out.println(response.body().toString());
    }
}
