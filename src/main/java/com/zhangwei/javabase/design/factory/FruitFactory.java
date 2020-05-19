package com.zhangwei.javabase.design.factory;

import com.zhangwei.javabase.common.Apple;
import com.zhangwei.javabase.common.Banana;
import com.zhangwei.javabase.common.Fruit;

public class FruitFactory {

    public static final String APPLE = "apple";
    public static final String BANANA = "banana";

    public Fruit getFruit(String name){
        if(APPLE.equals(name)){
            return new Apple(9.99);
        }
        else if(BANANA.equals(name)){
            return new Banana(5.99);
        }
        else {
            return null;
        }
    }
}
