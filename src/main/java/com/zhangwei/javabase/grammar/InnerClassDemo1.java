package com.zhangwei.javabase.grammar;

public class InnerClassDemo1 {
    class ChildClass{
        private int age;
        public ChildClass(int age){
            this.age = age;
        }
        public int getAge(){
            return age;
        }
    }
}
