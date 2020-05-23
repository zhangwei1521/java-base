package com.zhangwei.javabase.design.strategy;

/**
 * 策略模式
 * 策略模式简单来说就是把算法封装到单独的类中，并使用接口暴露算法入口
 * 在这个例子中，宠物管理员在执行clean时，需要根据宠物的类型来选择不同的clean算法。
 * 如果使用模板方法模式，则需要将clean方法声明为abstract，并为每种宠物创建一个管理员类，
 * 在具体的宠物园类中实现clean算法，但是这种做法的问题在于，一是子类泛滥，而是仍然可能存在
 * 代码重复，例如某一类宠物的clean是另外几种宠物clean算法的叠加。
 * 这里使用策略模式将clean算法封装到单独的实现类，并使用CleanKeeper接口暴露clean算法入口，
 * 在PetKeeper使用一个列表来保存CleanKeeper的实例，这样就解决了上面模板方法模式存在的问题。
 */
public class StrategyDemo {
    public static void main(String[] args) {
        PetKeeper dogKeeper = new PetKeeper("Nick");
        dogKeeper.addCleanKeeper(new WaterCleanKeeper());
        dogKeeper.work();

        PetKeeper lizardKeeper = new PetKeeper("Vivid");
        lizardKeeper.addCleanKeeper(new SandCleanKeeper());
        lizardKeeper.work();
    }
}
