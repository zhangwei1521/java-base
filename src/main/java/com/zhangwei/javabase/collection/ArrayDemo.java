package com.zhangwei.javabase.collection;

import java.util.Arrays;
import java.util.Spliterator;
import java.util.stream.Stream;

public class ArrayDemo {
    public static void main(String[] args) {
        //test01();
        //test02();
        test03();
    }

    private static void test01(){
        Object[] array = new Object[10];
        //数组设置元素前默认保存了null
        if(array[0]==null){
            System.out.println("array keep null when no element set");
        }
    }

    private static void test02(){
        //允许创建长度为0的数组
        Object[] array = new Object[0];
        System.out.println(array.length);
    }

    private static void test03(){
        Object[] array = new Object[10];
        for(int i=10;i>0;i--){
            array[10-i] = i;
        }
        System.out.println(Arrays.toString(array));

        Arrays.sort(array);
        System.out.println(Arrays.toString(array));

        array = Arrays.copyOf(array,array.length*2);
        array[10] = 10;
        System.out.println(Arrays.deepToString(array));

        int index = Arrays.binarySearch(array,5);
        System.out.println(index);

        Arrays.fill(array,11,array.length,12);
        System.out.println(Arrays.toString(array));

        Arrays.setAll(array,i->i);
        System.out.println(Arrays.toString(array));

        Stream<Object> stream = Arrays.stream(array);
        stream.forEach(item-> System.out.print(item));
        System.out.println();

        Spliterator<Object> spliterator1 = Arrays.spliterator(array);
        System.out.println(spliterator1.estimateSize());
        Spliterator<Object> spliterator2 = spliterator1.trySplit();
        System.out.println(spliterator1.estimateSize());
        while (spliterator1.tryAdvance(item-> System.out.print(item+"\t")));
    }

}
