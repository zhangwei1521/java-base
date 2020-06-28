package com.zhangwei.javabase.design.bridge;

public class ToyotaCar extends AbstractCar {

    public ToyotaCar(CarEngine engine){
        super(engine);
    }

    @Override
    public void work() {
        this.engine.start();
        System.out.println("ToyotaCar is running");
    }
}
