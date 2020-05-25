package com.zhangwei.javabase.collection.my;

import java.util.Iterator;
import java.util.Spliterator;

public class MyCollection<T> implements Iterable<T> {
    private Object[] array;
    private int index;

    public MyCollection(){
        array = new Object[10];
    }

    public void add(T el){
        if(index >= 10){
            throw new RuntimeException("this collection is full");
        }
        array[index++] = el;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int idx = 0;
            @Override
            public boolean hasNext() {
                if(idx < index)
                    return true;
                else
                    return false;
            }

            @Override
            public T next() {
                return (T)array[idx++];
            }
        };
    }

    public static void main(String[] args) {
        MyCollection<Integer> myCollection = new MyCollection<>();
        for(int i=20;i>10;i--){
            myCollection.add(i);
        }
        Iterator<Integer> iterator = myCollection.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next()+"\t");
        }
        System.out.println();

        myCollection.forEach(item-> System.out.printf("%d\t",item));
        System.out.println();

        Spliterator<Integer> spliterator = myCollection.spliterator();
        spliterator.forEachRemaining(item->System.out.printf("%d\t",item));
    }
}
