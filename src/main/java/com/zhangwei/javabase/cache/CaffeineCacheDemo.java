package com.zhangwei.javabase.cache;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.github.benmanes.caffeine.cache.Expiry;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.junit.Assert;

public class CaffeineCacheDemo {
    public static void main(String[] args) {
        //test01();
        //test02();
        //test03();
        //test04();
        //test05();
        test06();
    }

    private static void test01(){
        Cache<String,Object> cache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .maximumSize(100)
                .build();
        String key1 = "A";
        Object val1 = cache.getIfPresent(key1);
        Assert.assertNull(val1);
        cache.put(key1,"value1");
        val1 = cache.getIfPresent(key1);
        Assert.assertNotNull(val1);
        cache.invalidate(key1);
        Assert.assertNull(cache.getIfPresent(key1));
        Assert.assertNotNull(val1);

        String key2 = "B";
        Object val2 = cache.get(key2,key->"value2");
        System.out.println("val2 : "+val2);
        Object val21 = cache.getIfPresent(key2);
        System.out.println("val21 : "+val21);
    }

    private static void test02(){
        LoadingCache<String,Object> lcache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .maximumSize(100)
                .build(key->"data for "+key);
        Object val3 = lcache.get("key3");
        System.out.println("val3 : "+val3);
        Map<String, Object> lmap = lcache.getAll(Arrays.asList("A","B","C"));
        for (Map.Entry<String,Object> entry : lmap.entrySet()){
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
    }

    private static void test03(){
        AsyncLoadingCache<String,Object> alcache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .maximumSize(100)
                .buildAsync(key->"data for "+key);
        alcache.get("key1").thenAccept(val-> System.out.println(val));
        alcache.getAll(Arrays.asList("A","B","C")).thenAccept(map-> {
            for (Map.Entry<String,Object> entry : map.entrySet()){
                System.out.println(entry.getKey()+" : "+entry.getValue());
            }
        });
    }

    private static void test04(){
        LoadingCache<String,Object> lcache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .maximumSize(1)
                .build(key->"data for "+key);
        Assert.assertEquals(0,lcache.estimatedSize());
        lcache.get("A");
        Assert.assertEquals(1,lcache.estimatedSize());
        lcache.get("B");
        System.out.println("lcache size : "+lcache.estimatedSize());
        lcache.cleanUp();
        System.out.println("lcache size : "+lcache.estimatedSize());

        System.out.println("==========================");

        lcache = Caffeine.newBuilder()
                .maximumWeight(10)
                .weigher((k,v)->5)
                .build(key->"data for "+key);
        lcache.get("A");
        lcache.get("B");
        System.out.println("lcache size : "+lcache.estimatedSize());
        lcache.get("C");
        System.out.println("lcache size : "+lcache.estimatedSize());
        lcache.cleanUp();
        System.out.println("lcache size : "+lcache.estimatedSize());
    }

    private static void test05(){
        LoadingCache<String,Object> cache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .weakKeys()
                .weakValues()
                .build(key->"data for "+key);
        /*cache = Caffeine.newBuilder().expireAfter(new Expiry<String, Object>() {
            @Override
            public long expireAfterCreate(String key, Object value, long currentTime) {
                return 10000;
            }
            @Override
            public long expireAfterUpdate(String key, Object value, long currentTime, long currentDuration) {
                return 10000;
            }
            @Override
            public long expireAfterRead(String key, Object value, long currentTime, long currentDuration) {
                return 10000;
            }
        }).build(key->"data for "+key);*/
        cache.get("A");
        cache.cleanUp();
        System.out.println("lcache size : "+cache.estimatedSize());
        try {
            Thread.sleep(1100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("lcache size : "+cache.estimatedSize());
        cache.cleanUp();
        System.out.println("lcache size : "+cache.estimatedSize());
    }

    private static void test06(){
        LoadingCache<String,Object> cache = Caffeine.newBuilder()
                .refreshAfterWrite(1, TimeUnit.SECONDS)
                .weakKeys()
                .weakValues()
                .recordStats()
                .build(key->"data for "+key+" : "+System.currentTimeMillis());
        String key1 = "A";
        Object val1 = new Object();
        cache.put(key1,val1);
        val1 = null;
        System.gc();
        Object val2 = cache.getIfPresent(key1);
        Assert.assertNull(val2);
        System.out.println(cache.get("key1"));
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(cache.get("key1"));
        System.out.println(cache.get("key1"));
        System.out.println(cache.stats().hitCount());
        System.out.println(cache.stats().missCount());
    }
}
