package com.zhangwei.javabase.design.observer;

import java.util.LinkedList;
import java.util.List;

public class Newspaper implements Observable {

    private List<String> latestNews = new LinkedList<>();

    private List<Observer> subscribers = new LinkedList<>();

    private String name;

    public Newspaper(String name){
        this.name = name;
    }

    @Override
    public void addObserver(Observer observer) {
        subscribers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        subscribers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer subscriber : subscribers){
            subscriber.receiveNotice(this,latestNews);
        }
    }

    public void publishNews(String news){
        latestNews.add(news);
        if(latestNews.size()==3){
            notifyObservers();
            latestNews.clear();
        }
    }

    @Override
    public String toString(){
        return name;
    }
}
