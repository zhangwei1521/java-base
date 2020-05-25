package com.zhangwei.javabase.design.builder;

import java.util.Date;

/**
 * builder模式
 * 使用 builder 模式主要是为了更加灵活的构造对象
 * 如果某类对象的属性较多，构造对象实例时又需要灵活的设置部分属性的值，则使用构造方法就会很繁琐，
 * 使用set方法也会导致代码的不清晰，这时使用builder模式就会方便很多
 */
public class BuilderDemo {
    public static void main(String[] args) {
        Product product = new Product.ProductBuilder(12345)
                .name("apple")
                .price(9.99)
                .produceDate(new Date())
                .build();
        System.out.println(product);
    }
}
