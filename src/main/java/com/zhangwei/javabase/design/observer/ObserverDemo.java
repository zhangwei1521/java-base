package com.zhangwei.javabase.design.observer;

/**
 * 观察者模式
 * 多个观察者对象观察一个被观察者，被观察者在特定状态发生变化的时候通知观察者
 * 观察者模式和监听器模式比较相像，不同在于触发方式不同，监听器模式中是事件广播器
 * 直接提供API给客户端发送事件，广播器再将事件通知到各个监听器；观察者模式则是
 * 被观察者的内部状态发生特定变化时通知各个观察者。
 */
public class ObserverDemo {
    public static void main(String[] args) {
        Newspaper bbc = new Newspaper("bbc");

        NewsSubscriber tom = new NewsSubscriber("tom");
        tom.observe(bbc);

        NewsSubscriber john = new NewsSubscriber("john");
        john.observe(bbc);

        bbc.publishNews("weather : snow is coming");
        bbc.publishNews("sport : wwc will delay");
        bbc.publishNews("science : co2 cause extreme clime");
        bbc.publishNews("history : nerver before this terrible weather");
    }
}
