package com.zhangwei.javabase.collection;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.TreeMap;

public class MapDemo {

    public static void main(String[] args) {
        testHashMap();
        //testTreeMap();
        testHashtable();
    }

    private static void testHashMap() {
        //HashMap的key没有确定的排序规则
        Map<String, String> map = new HashMap<>();
        putContent(map);
        for (String key : map.keySet()) {
            System.out.println(key);
        }
    }

    private static void testTreeMap() {
        //TreeMap的key默认按字母序升序排序
        Map<String, String> map = new TreeMap<>();
        putContent(map);
        for (String key : map.keySet()) {
            System.out.println(key);
        }
    }

    private static void testHashtable() {
        //Hashtable的key按保存顺序排序
        Map<String, String> map = new Hashtable();
        putContent(map);
        for (String key : map.keySet()) {
            System.out.println(key);
        }
    }

    private static void putContent(Map<String, String> map) {
        map.put("dd", "152");
        map.put("eE", "185");
        map.put("lisi", "185");
        map.put("wangwu", "163");
        map.put("mazhi", "135");
    }

}
