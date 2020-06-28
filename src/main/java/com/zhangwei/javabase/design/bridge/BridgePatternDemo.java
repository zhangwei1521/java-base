package com.zhangwei.javabase.design.bridge;

/**
 * 桥接模式
 * GOF定义：将抽象体(Abstraction)与实现体(Implementation)脱耦，使得二者可以独立地变化。
 * 桥接模式是一种结构型模式。
 * 在这个例子中，如果把CarEngine作为Car的一种特性，即为每种CarEngine和品牌设计单独的实现类，
 * 就会导致Car子类泛滥，这里把CarEngine抽象出来，就实现了Car和CarEngine的解耦，可以使得它们
 * 独立变化。
 * 桥接模式和策略模式的异同：
 * 桥接模式模式中抽象体聚合实现体接口，策略模式中，Context也使用聚合的方式引用Startegy接口;
 * 但是策略模式只是抽象算法，只实现了Startegy接口的独立变化，而桥接模式则允许抽象体和实现体
 * 都可以独立的变化。
 * 在语意上，桥接模式强调实现体(Implementor)接口仅提供基本操作，而抽象体(Abstraction)则基于
 * 这些基本操作定义更高层次的操作。而策略模式强调Strategy抽象接口的提供的是一种算法，一般是无状态、
 * 无数据的，而Context则简单调用这些算法完成其操作。
 * 桥接模式把本质上并不内聚的两种体系区别开来，通过继承、聚合的方式组合类和对象以形成更大的结构；
 * 而策略在解耦上仅仅是某一个算法的层次，没有到体系这一层次。
 * 策略模式的结构是包容在桥接模式结构中的，桥接中必然存在着策略模式，Abstraction与Implementor之间
 * 可以认为是策略模式，但是桥接模式一般Implementor将提供一系列的成体系的操作，而且Implementor是
 * 具有状态和数据的静态结构。
 */
public class BridgePatternDemo {
    public static void main(String[] args) {
        DongfengCar dongfengCarWithBenzEngine = new DongfengCar(new BenzEngine());
        dongfengCarWithBenzEngine.work();
        DongfengCar dongfengCarWithBWMEngine = new DongfengCar(new BWMEngine());
        dongfengCarWithBWMEngine.work();

        ToyotaCar toyotaCarWithBenzEngine = new ToyotaCar(new BenzEngine());
        toyotaCarWithBenzEngine.work();
        ToyotaCar toyotaCarWithBWMEngine = new ToyotaCar(new BWMEngine());
        toyotaCarWithBWMEngine.work();

    }
}
