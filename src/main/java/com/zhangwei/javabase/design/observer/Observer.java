package com.zhangwei.javabase.design.observer;

public interface Observer {

    void observe(Observable observable);

    void receiveNotice(Observable observable, Object... args);
}
