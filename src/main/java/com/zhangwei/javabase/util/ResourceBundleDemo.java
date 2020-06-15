package com.zhangwei.javabase.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;

public class ResourceBundleDemo {
    public static void main(String[] args) {
        //test01();
        test02();
    }

    private static void test01() {
        Map<String,Object> map = new HashMap<>();
        map.put("ok","确定");
        map.put("cancel","取消");

        ResourceBundle rb = new ResourceBundle(){
            private Map<String,Object> localResource = new HashMap<>(map);

            @Override
            protected Object handleGetObject(String key) {
                return localResource.get(key);
            }

            @Override
            public Enumeration<String> getKeys() {
                return Collections.enumeration(localResource.keySet());
            }
        };

        System.out.println(rb.getString("ok"));
    }

    private static void test02(){
        String localResource = "i18n/ui_text_zh.properties";
        try {
            //直接使用 InputStream 来构造 PropertyResourceBundle 对象，要求文件必须使用ISO-8859-1编码
            /*PropertyResourceBundle prb = new PropertyResourceBundle(ResourceBundleDemo.class
                    .getClassLoader().getResourceAsStream(localResource));*/
            PropertyResourceBundle prb = new PropertyResourceBundle(
                    new InputStreamReader(
                            ResourceBundleDemo.class.getClassLoader().getResourceAsStream(localResource),
                            Charset.forName("utf-8")
                    )
            );
            String value = prb.getString("cancel");
            System.out.println(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
