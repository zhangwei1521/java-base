package com.zhangwei.javabase.design.bridge;

public class DongfengCar extends AbstractCar {

    public DongfengCar(CarEngine engine){
        super(engine);
    }

    @Override
    public void work() {
        this.engine.start();
        System.out.println("DongfengCar is running");
    }
}
