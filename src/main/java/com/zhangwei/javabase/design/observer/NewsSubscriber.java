package com.zhangwei.javabase.design.observer;

import java.util.Arrays;

public class NewsSubscriber implements Observer {

    private String name;

    public NewsSubscriber(String name){
        this.name = name;
    }

    @Override
    public void observe(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void receiveNotice(Observable observable, Object... args) {
        System.out.println(name +" receive notice from : "+observable);
        System.out.println("notice content : "+ Arrays.asList(args));
        System.out.println("==============");
    }
}
