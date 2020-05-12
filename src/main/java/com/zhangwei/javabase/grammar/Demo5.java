package com.zhangwei.javabase.grammar;

public class Demo5 {
    public static void main(String[] args) {
        Demo5 demo5 = new Demo5();
        demo5.go();
    }

    public void go(){
        //方法重载，静态分派，编译期就确定了调用的方法
        Horse wh = new WhiteHorse();
        Horse bh = new BlackHorse();
        this.ride(wh);
        this.ride(bh);
        this.ride(new WhiteHorse());
        this.ride(new BlackHorse());
        System.out.println("=============");
        //方法重写，动态分派，运行期确定调用的方法
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
