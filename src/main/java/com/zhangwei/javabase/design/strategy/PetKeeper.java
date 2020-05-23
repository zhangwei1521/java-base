package com.zhangwei.javabase.design.strategy;

import java.util.LinkedList;
import java.util.List;

public class PetKeeper {

    private String name;

    private List<CleanKeeper> cleanKeeperList = new LinkedList<>();

    public PetKeeper(String name){
        this.name = name;
    }

    public void addCleanKeeper(CleanKeeper cleanKeeper){
        this.cleanKeeperList.add(cleanKeeper);
    }

    public void work(){
        feed();
        walk();
        clean();
    }

    private void feed() {
        System.out.println("feed at 8:00 AM");
    }

    private void walk() {
        System.out.println("walk at 12:00 AM");
    }

    private void clean() {
        System.out.println("clean at 12:00 AM");
        for(CleanKeeper cleanKeeper : cleanKeeperList){
            cleanKeeper.clean();
        }
    }
}
