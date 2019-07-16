package com.zhangwei.javabase.lambda;

public class HeighTemp {
    private int htemp;

    public int getHtemp(){return htemp;}
    public HeighTemp(int htemp){this.htemp = htemp;}

    public boolean lessTemp(HeighTemp heighTemp){
        if(this.htemp < heighTemp.htemp){
            return true;
        }
        else {
            return false;
        }
    }
}
