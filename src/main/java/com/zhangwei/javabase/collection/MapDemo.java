package com.zhangwei.javabase.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapDemo {

	public static void main(String[] args) {
		//testHashMap();
		testTreeMap();
	}

	private static void testHashMap(){
		Map<String,String> map = new HashMap<>();
		putContent(map);
		for (String key : map.keySet()){
			System.out.println(key);
		}
	}

	private static void testTreeMap(){
		Map<String,String> map = new TreeMap<>();
		putContent(map);
		for (String key : map.keySet()){
			System.out.println(key);
		}
	}

	private static void putContent(Map<String,String> map){
		map.put("zhangsan","152");
		map.put("lisi","185");
		map.put("wangwu","163");
		map.put("mazhi","135");
	}

}
