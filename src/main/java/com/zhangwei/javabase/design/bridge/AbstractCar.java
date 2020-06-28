package com.zhangwei.javabase.design.bridge;

public abstract class AbstractCar {
    protected CarEngine engine;

    protected AbstractCar(CarEngine engine){
        this.engine = engine;
    }

    public abstract void work();
}
