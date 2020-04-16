package com.zhangwei.javabase.net.httpclient;

import org.apache.commons.io.FileUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class HttpClientDemo {
    public static void main(String[] args) throws Exception {
        //test01();
        //test02();
        //test03();
        test04();
    }

    //测试无参数GET请求
    private static void test01() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            String content = EntityUtils.toString(response.getEntity(),"UTF-8");
            FileUtils.writeStringToFile(new File("d:\\tem_file\\baidu.html"),content,"UTF-8");
            System.out.println("success");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(response != null){
                response.close();
            }
            httpClient.close();
        }
    }

    //测试带参数GET请求
    private static void test02() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        URI uri = new URIBuilder("http://www.dogedoge.com").setParameter("q","java").build();
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == 200){
                String content = EntityUtils.toString(response.getEntity(),"UTF-8");
                FileUtils.writeStringToFile(new File("d:\\tem_file\\dogedoge-java.html"),content,"UTF-8");
                System.out.println("success");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(response != null){
                response.close();
            }
            httpClient.close();
        }
    }

    //测试无参数POST请求
    private static void test03() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8082/hello");
        httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.92 Safari/537.36");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            if(response.getStatusLine().getStatusCode() == 200){
                String content = EntityUtils.toString(response.getEntity(),"UTF-8");
                System.out.println("receive : "+content);
                System.out.println("success");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(response != null){
                response.close();
            }
            httpClient.close();
        }
    }

    //测试带参数POST请求
    private static void test04() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8082/createUser");
        httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.92 Safari/537.36");
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("username","zhangwei"));
        parameters.add(new BasicNameValuePair("password","root"));
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters);
        httpPost.setEntity(formEntity);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            if(response.getStatusLine().getStatusCode() == 200){
                String content = EntityUtils.toString(response.getEntity(),"UTF-8");
                System.out.println("receive : "+content);
                System.out.println("success");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(response != null){
                response.close();
            }
            httpClient.close();
        }
    }
}
