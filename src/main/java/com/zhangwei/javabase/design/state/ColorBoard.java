package com.zhangwei.javabase.design.state;

public class ColorBoard {
    private State state = ColorState.RED;

    public void setState(State state){
        this.state = state;
    }

    public void pull(){
        state.pull(this);
    }

    public void push(){
        state.push(this);
    }

    public String getCurrentColor(){
        return state.getName();
    }
}
