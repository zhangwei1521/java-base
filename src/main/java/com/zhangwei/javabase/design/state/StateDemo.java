package com.zhangwei.javabase.design.state;

/**
 * 状态模式
 * 解释：对象的行为(方法)随着对象的内部状态变化而调整，看起来就像是对象的类型发生了动态变化
 * 这个例子中，ColorBoard的push和pull方法都根据其当前内部状态来决定方法如何修改内部状态，
 * 并且将状态的修改委托给了当前状态实例，由状态实例来维护状态变化的逻辑，这样做的一个好处
 * 在于如果需要增加新的状态，则不需要修改ColorBoard
 */
public class StateDemo {
    public static void main(String[] args) {
        ColorBoard colorBoard = new ColorBoard();
        System.out.println("now color : "+colorBoard.getCurrentColor());
        testPull(colorBoard);
        System.out.println("==============");
        testPush(colorBoard);
    }

    private static void testPull(ColorBoard colorBoard) {
        System.out.println("pull : ");
        for(int i=0;i<3;i++){
            System.out.print(colorBoard.getCurrentColor()+"----->");
            colorBoard.pull();
            System.out.println(colorBoard.getCurrentColor());
        }
    }

    private static void testPush(ColorBoard colorBoard) {
        System.out.println("push : ");
        for(int i=0;i<3;i++){
            System.out.print(colorBoard.getCurrentColor()+"----->");
            colorBoard.push();
            System.out.println(colorBoard.getCurrentColor());
        }
    }
}
