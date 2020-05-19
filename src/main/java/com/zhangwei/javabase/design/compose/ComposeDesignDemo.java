package com.zhangwei.javabase.design.compose;

/**
 * 组合模式
 * 将对象组合成树形结构，呈现部分-整体的层次关系，以使客户端可以一致的使用单个对象和组合对象
 *
 * 组合模式要求组合对象的对外方法应该递归的访问其构成对象的对应方法，以使客户端在使用组合对象
 * 时完整的调用了构成其的所有单个对象，执行模型看起来和外观模式差不多，不同在于组合模式的对外
 * 方法和其构成对象的对外发放签名是相同的，而外观模式则不要求这一点
 */
public class ComposeDesignDemo {
    public static void main(String[] args) {
        /**
         * Manager的add和remove方法也可以在Employee接口中声明，在OrdinaryEmployee中
         * 可以对其做空实现或运行时抛出异常，这样在使用时Manager就可以直接使用Employee
         * 接口替换，这称为透明的组合模式
         */
        Manager e1 = new Manager("jack","root");
        Manager e2 = new Manager("kent","chiefA");
        Manager e3 = new Manager("lice","chiefB");

        Employee e4 = new OrdinaryEmployee("mimi","soldier");
        Employee e5 = new OrdinaryEmployee("nana","soldier");
        Employee e6 = new OrdinaryEmployee("peter","soldier");
        Employee e7 = new OrdinaryEmployee("roni","soldier");
        Employee e8 = new OrdinaryEmployee("tide","soldier");

        e1.add(e2);
        e1.add(e3);
        e2.add(e4);
        e2.add(e5);
        e3.add(e6);
        e3.add(e7);
        e3.add(e8);

        System.out.println("e8 : "+e8.getFullInfo());
        System.out.println("e4 : "+e4.getFullInfo());

        System.out.println("e1 : "+e1.getFullInfo());
        System.out.println("e2 : "+e2.getFullInfo());
        System.out.println("e3 : "+e3.getFullInfo());
    }
}
