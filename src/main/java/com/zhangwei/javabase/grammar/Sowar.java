package com.zhangwei.javabase.grammar;

public class Sowar {
    public static void main(String[] args) {
        Sowar sowar = new Sowar();
        sowar.go();
    }

    public void go(){
        this.ride(new WhiteHorse());
        this.ride(new BlackHorse());
        Horse wh = new WhiteHorse();
        Horse bh = new BlackHorse();
        this.ride(wh);
        this.ride(bh);
    }

    public void ride(Horse horse){
        System.out.println("ride horse");
    }

    public void ride(WhiteHorse whiteHorse){
        System.out.println("ride whiteHorse");
    }

    public void ride(BlackHorse blackHorse){
        System.out.println("ride blackHorse");
    }

    class Horse{}

    class WhiteHorse extends Horse{}

    class BlackHorse extends Horse{}
}
