package com.zhangwei.javabase.grammar;

public class Sowar {
    public static void main(String[] args) {
        Sowar sowar = new Sowar();
        sowar.go();
    }

    public void go(){
        //方法重载，静态分派
        Horse wh = new WhiteHorse();
        Horse bh = new BlackHorse();
        this.ride(wh);
        this.ride(bh);
        this.ride(new WhiteHorse());
        this.ride(new BlackHorse());
        System.out.println("=============");
        //方法重写，动态分派
        wh.neigh();
        bh.neigh();
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

    class Horse{
        public void neigh(){
            System.out.println("Horse ...");
        }
    }

    class WhiteHorse extends Horse{
        public void neigh(){
            System.out.println("WhiteHorse ...");
        }
    }

    class BlackHorse extends Horse{
        public void neigh(){
            System.out.println("BlackHorse ...");
        }
    }
}
