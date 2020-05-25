package com.zhangwei.javabase.design.factory;

import com.zhangwei.javabase.common.Banana;
import com.zhangwei.javabase.common.Fruit;

/**
 * 工厂方法模式
 * 工厂方法模式主要是用来生产固定规格的对象，即为客户端提供生产好的具有特定状态的对象
 * 普通工厂方法模式通常会为每个类或基类配置一个工厂类，所以可能造成工厂类泛滥
 * 静态工厂方法通过把工厂方法定义在目标类型中来避免这个问题
 */
public class ObjectFactoryDemo {
    public static void main(String[] args) {
        FruitFactory fruitFactory = new FruitFactory();
        Fruit apple = fruitFactory.getFruit(FruitFactory.APPLE);
        System.out.println(apple);

        //静态工厂方法
        Banana banana = Banana.getInstance();
        System.out.println(banana);
    }
}
