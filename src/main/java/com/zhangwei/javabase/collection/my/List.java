package com.zhangwei.javabase.collection.my;

public interface List<T> {

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
