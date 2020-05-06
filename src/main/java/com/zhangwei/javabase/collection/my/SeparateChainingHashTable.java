package com.zhangwei.javabase.collection.my;

import java.util.List;
import java.util.LinkedList;

//分离链接哈希表
public class SeparateChainingHashTable<T> {
    private static final int DEFAULT_TABLE_SIZE = 101;
    private List<T> [] lists;
    private int currentSize;

    public SeparateChainingHashTable(){
        this(DEFAULT_TABLE_SIZE);
    }

    public SeparateChainingHashTable(int size){
        lists = new LinkedList[nextPrime(size)];
        for(int i=0; i<lists.length; i++){
            lists[i] = new LinkedList<>();
        }
    }

    public void insert(T t){
        List<T> whichList = lists[myhash(t)];
        if(!whichList.contains(t)){
            whichList.add(t);
            if(++currentSize > lists.length){
                rehash();
            }
        }
    }

    public void remove(T t){
        List<T> whichList = lists[myhash(t)];
        if(whichList.contains(t)){
            whichList.remove(t);
            currentSize--;
        }
    }

    public boolean contains(T t){
        List<T> whichList = lists[myhash(t)];
        return whichList.contains(t);
    }

    public void makeEmpty(){
        for(int i=0; i<lists.length; i++){
            lists[i].clear();
        }
        currentSize = 0;
    }

    public String toString(){
        StringBuffer stringBuffer = new StringBuffer();
        for(List<T> list : lists){
            if(list.size() > 0){
                for(T t : list){
                    stringBuffer.append(t).append(',');
                }
            }
        }
        String str;
        if(stringBuffer.length()>0){
            str = stringBuffer.substring(0,stringBuffer.length()-1);
        }
        else {
            str = stringBuffer.toString();
        }
        return str;
    }

    private void rehash(){
        List<T>[] oldLists = lists;
        lists = new LinkedList[nextPrime(lists.length * 2)];
        for(List<T> list : oldLists){
            if(list.size() > 0){
                for(T t : list){
                    insert(t);
                }
            }
        }
    }

    private int myhash(T t){
        int hashVal = t.hashCode();
        hashVal %= lists.length;
        if(hashVal < 0){
            hashVal += lists.length;
        }
        return hashVal;
    }

    //找到刚好比n大的素数
    private static int nextPrime(int n){
        int m = n;
        while (!isPrime(m)){
            m++;
        }
        return m;
    }

    //判断n是否是素数
    private static boolean isPrime(int n){
        if(n<=3){
            return n>1;
        }
        double m = Math.sqrt(n);
        for(int i=2; i<=m; i++){
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }
}
