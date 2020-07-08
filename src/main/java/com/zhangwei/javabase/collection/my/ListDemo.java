package com.zhangwei.javabase.collection.my;

import com.zhangwei.javabase.util.MyArrayUtil;

public class ListDemo {
    public static void main(String[] args) {
        test01();
    }

    private static void test01(){
        List<Integer> list = new ArrayList<>(1);
        System.out.println("size : "+list.size());
        list.add(1);
        System.out.println("size : "+list.size());
        list.add(1);
        System.out.println("size : "+list.size());
        //list.add(null);
        list.add(3);
        System.out.println("size : "+list.size());
        //list.get(-1);
        // can not use int here, because jvm will do translation from Integer to int,
        // if return null, the translating will throw NullPointerException
        Integer item = list.get(2);
        System.out.println("item at 2(index) : "+item);
        //list.get(3);
        boolean c0 = list.contains(0);
        boolean c1 = list.contains(1);
        boolean cnull = list.contains(null);
        System.out.println("contains 0 : "+c0);
        System.out.println("contains 1 : "+c1);
        System.out.println("contains null : "+cnull);
        int fidx0 = list.firstIndexOf(0);
        int fidx1 = list.firstIndexOf(1);
        int fidxNull = list.firstIndexOf(null);
        System.out.println("first index of 0 : "+fidx0);
        System.out.println("first index of 1 : "+fidx1);
        System.out.println("first index of null : "+fidxNull);
        int[] aidx = list.allIndexOf(1);
        System.out.println("all index of 1 : "+ MyArrayUtil.toString(aidx));
        Integer del = list.remove(2);
        System.out.println("del : "+del);
        System.out.println("size : "+list.size());
        int delCount = list.removeAll(1);
        System.out.println("delCount : "+delCount);
        System.out.println("size : "+list.size());
        list.add(5);
        Integer old = list.set(0,10);
        System.out.println("old : "+old);
        System.out.println("new : "+list.get(0));
        list.add(10);
        list.replaceAll(10,20);
        Integer[] array = list.toArray(new Integer[list.size()]);
        System.out.println("array : "+MyArrayUtil.toString(array));
        List<Integer> subList = list.subList(0,1);
        System.out.println("sublist : "+MyArrayUtil.toString(subList.toArray(new Integer[subList.size()])));
    }
}

interface List<T> {

    boolean add(T t);

    T get(int index);

    boolean contains(T t);

    int firstIndexOf(T t);

    int[] allIndexOf(T t);

    int size();

    T remove(int index);

    int removeAll(T t);

    T set(int index,T newItem);

    int replaceAll(T oldItem,T newItem);

    T[] toArray(T[] newArray);

    List<T> subList(int begin,int end);
}

class ArrayList<T> implements List<T> {

    private Object[] array;

    private int size;

    public ArrayList(){
        array = new Object[16];
    }

    public ArrayList(int initialCapacity){
        if(initialCapacity <= 0 || initialCapacity > Integer.MAX_VALUE){
            throw new RuntimeException("index is invalid");
        }
        array = new Object[initialCapacity];
    }

    @Override
    public boolean add(T t) {
        if(size()==array.length){
            array = createNewArrayWithData();
        }
        array[size++] = t;
        return true;
    }

    private Object[] createNewArrayWithData() {
        Object[] newArray = new Object[array.length * 2];
        for(int i=0; i < array.length; i++){
            newArray[i] = array[i];
        }
        return newArray;
    }

    @Override
    public T get(int index) {
        if(index < 0 || index >= size()){
            throw new RuntimeException("index is invalid");
        }
        if(array[index] == null){
            return null;
        }
        return (T)array[index];
    }

    @Override
    public boolean contains(T t) {
        for(int i=0; i<size(); i++){
            if(t instanceof String){
                if((array[i] == null && t == null) ||
                        (array[i] != null && t != null && array[i].equals(t))){
                    return true;
                }
            }
            if(array[i]==t){
                return true;
            }
        }
        return false;
    }

    @Override
    public int firstIndexOf(T t) {
        for(int i=0; i<size(); i++){
            if(t instanceof String){
                if((array[i] == null && t == null) ||
                        (array[i] != null && t != null && array[i].equals(t))){
                    return i;
                }
            }
            if(array[i]==t){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int[] allIndexOf(T t) {
        int[] indexArray = new int[size()];
        for(int i=0; i<size(); i++){
            indexArray[i] = Integer.MAX_VALUE;
        }
        int index = 0;
        for(int i=0; i<size(); i++){
            if(t instanceof String){
                if((array[i] == null && t == null) ||
                        (array[i] != null && t != null && array[i].equals(t))){
                    indexArray[index++]=i;
                }
            }
            if(array[i]==t){
                indexArray[index++]=i;
            }
        }
        int lastIndex = -1;
        for(int i=0; i<size(); i++){
            if(indexArray[i] == Integer.MAX_VALUE){
                lastIndex = i-1;
                break;
            }
        }
        if(lastIndex == -1){
            return null;
        }
        int[] result = new int[lastIndex+1];
        for(int i=0; i<=lastIndex; i++){
            result[i] = indexArray[i];
        }
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T remove(int index) {
        if(index < 0 || index >= size()){
            throw new RuntimeException("index is invalid");
        }
        T t = (T)array[index];
        for(int i=index; i<size()-1;i++){
            array[i] = array[i+1];
        }
        size--;
        return t;
    }

    @Override
    public int removeAll(T t) {
        class Temp{}
        int count = 0;
        Object[] newArray = new Object[size()];
        for(int i=0;i<size();i++){
            newArray[i] = Temp.class;
        }
        boolean isNull = t==null ? true : false;
        boolean isString = t instanceof String ? true : false;
        int j = 0;
        for(int i=0;i<size();i++){
            if(isNull){
                if(array[i]==null){
                    count++;
                }
                else {
                    newArray[j++] = array[i];
                }
            }
            else {
                if(isString && t.equals(array[i])){
                    count++;
                }
                else if(t == array[i]){
                    count++;
                }
                else {
                    newArray[j++] = array[i];
                }
            }
        }
        int lastIndex = -1;
        for(int i=0;i<size();i++){
            if(newArray[i] == Temp.class){
                lastIndex = i-1;
                break;
            }
        }
        if(lastIndex == -1){
            size = 0;
        }
        else {
            array = new Object[lastIndex+1];
            for(int i=0; i<=lastIndex; i++){
                array[i] = newArray[i];
            }
            size = lastIndex+1;
        }
        return count;
    }

    @Override
    public T set(int index, T newItem) {
        if(index < 0 || index >= size()){
            throw new RuntimeException("index is invalid");
        }
        T oldItem = (T)array[index];
        array[index] = newItem;
        return oldItem;
    }

    @Override
    public int replaceAll(T oldItem, T newItem) {
        int count = 0;
        boolean isNull = oldItem==null ? true : false;
        boolean isString = oldItem instanceof String ? true : false;
        for(int i=0; i<size(); i++){
            if(isNull){
                if(array[i] == null){
                    array[i] = newItem;
                    count++;
                }
            }
            else if(isString){
                if(oldItem.equals(array[i])){
                    array[i] = newItem;
                    count++;
                }
            }
            else {
                if(array[i] == oldItem){
                    array[i] = newItem;
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public T[] toArray(T[] newArray) {
        if(newArray==null){
            return null;
        }
        int len = newArray.length > size() ? size() : newArray.length;
        for(int i=0;i<len;i++){
            newArray[i] = (T)array[i];
        }
        //Object[] cannot be cast to Integer[]
        return newArray;
    }

    @Override
    public List<T> subList(int begin, int end) {
        if(begin < 0 || begin > size() || end < 0 || end > size() || begin >= end){
            throw new RuntimeException("invalid begin or end index");
        }
        List<T> subList = new ArrayList<>(end-begin);
        for(int i=begin;i<end;i++){
            subList.add((T)array[i]);
        }
        return subList;
    }
}
