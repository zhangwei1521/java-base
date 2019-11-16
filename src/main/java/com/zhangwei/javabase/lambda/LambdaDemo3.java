package com.zhangwei.javabase.lambda;

public class LambdaDemo3 {
    static <T> int counter(T[] objs,MyFunc<T> myFunc,T obj){
        int count = 0;
        for(int i=0; i<objs.length;i++){
            if(myFunc.func(objs[i],obj)){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        HeighTemp[] heighTemps = {new HeighTemp(10),new HeighTemp(20),new HeighTemp(30),new HeighTemp(40),
                new HeighTemp(50),new HeighTemp(60)};
        int result = counter(heighTemps,HeighTemp::lessTemp,new HeighTemp(25));
        System.out.println("result: "+result);	// result: 2
    }
}
