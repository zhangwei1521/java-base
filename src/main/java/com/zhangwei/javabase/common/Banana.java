package com.zhangwei.javabase.common;

public class Banana extends Fruit {
    public Banana(double price){
        this.name = "banana";
        this.shape = "camber";
        this.price = price;
    }

    public static Banana getInstance(){
        return new Banana(5.99);
    }
}
