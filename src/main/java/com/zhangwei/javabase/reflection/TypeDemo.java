package com.zhangwei.javabase.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public class TypeDemo {
    public static void main(String[] args) throws NoSuchFieldException {
        Field f1 = ClassA.class.getDeclaredField("map");
        Type type = f1.getGenericType();
        System.out.println(f1.getGenericType());
        System.out.println(f1.getGenericType() instanceof ParameterizedType);
    }
}

class ClassA<K,V>{
    Map<K,V> map;
}

class SubClassA<T> extends ClassA<T,T>{

}
