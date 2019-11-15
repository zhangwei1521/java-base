package com.zhangwei.javabase.jvm;

public class JvmDemo8 {
    static class Animal{
        public void run(){
            System.out.println("animal run....");
        }
    }
    static class Dog extends Animal{
        public void run(){
            System.out.println("Dog run....");
        }
    }

    public static void main(String[] args) {
        JvmDemo8 demo8 = new JvmDemo8();
        Animal animal = new Animal();
        Animal dog = new Dog();
        demo8.testRun(dog);
        demo8.testRun(animal);
    }

    public void testRun(Animal animal){
        System.out.println("hi,annimal");
        animal.run();
    }

    public void testRun(Dog dog){
        System.out.println("hi,dog");
        dog.run();
    }
}


