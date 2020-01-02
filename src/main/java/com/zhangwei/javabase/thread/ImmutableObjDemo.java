package com.zhangwei.javabase.thread;

import com.sun.xml.internal.stream.util.ReadOnlyIterator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ImmutableObjDemo {
    public static void main(String[] args) {
        //test01();
        test02();
    }

    private static HashMap<String,Object> prepareData(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("name","zhangwei");
        map.put("age",25);
        map.put("tools",new String[]{"java","js","linux"});
        return map;
    }

    private static void test01(){
        HashMap<String,Object> map = prepareData();
        ImmutableObj1 obj1 = new ImmutableObj1(map);
        ImmutableObj1 obj2 = obj1.update("age",26);

        System.out.println(obj1.get("tools"));
        System.out.println(obj2.get("tools"));
    }

    private static void test02() {
        HashMap<String, Object> map = prepareData();
        ImmutableObj1 obj1 = new ImmutableObj1(map);
        Iterator<Map.Entry<String,Object>> iterator = obj1.iterator();
        while (iterator.hasNext()){
            Map.Entry<String,Object> entry = iterator.next();
            System.out.println(entry.getKey()+" : "+entry.getValue());
            if(entry.getKey().equals("age") && (Integer)entry.getValue()==25){
                // 不能删除：UnsupportedOperationException
                // iterator.remove();
            }
        }
    }
}

class ImmutableObj1{
    private final HashMap<String,Object> map;

    public ImmutableObj1(HashMap<String,Object> map){
        this.map = map;
    }

    public Object get(String key){
        return map.get(key);
    }

    public ImmutableObj1 update(String key,Object value){
        return new ImmutableObj1(copyMap(this,key,value));
    }

    public Set<Map.Entry<String,Object>> entrySet(){
        final Set<Map.Entry<String,Object>> entries = Collections.unmodifiableSet(map.entrySet());
        // 不能修改: UnsupportedOperationException
        // entries.clear();
        return entries;
    }

    public Iterator<Map.Entry<String,Object>> iterator(){
        final Set<Map.Entry<String,Object>> entries = entrySet();
        return new ReadOnlyIterator(entries.iterator());
    }

    private HashMap<String,Object> copyMap(ImmutableObj1 prototype,String key,Object value){
        HashMap<String,Object> newMap = (HashMap<String,Object>)prototype.map.clone();
        newMap.put(key,value);
        return newMap;
    }
}
