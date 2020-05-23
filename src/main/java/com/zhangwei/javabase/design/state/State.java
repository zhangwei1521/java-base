package com.zhangwei.javabase.design.state;

public interface State {

    void pull(ColorBoard colorBoard);

    void push(ColorBoard colorBoard);

    String getName();
}
