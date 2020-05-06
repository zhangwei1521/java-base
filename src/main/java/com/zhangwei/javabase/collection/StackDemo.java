package com.zhangwei.javabase.collection;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class StackDemo {
    public static void main(String[] args) {
        //test01();
        test02();
        //test03();
    }

    private static void test01(){
        //default capacity is 10, when size==capacity,next put will do capacity *= 2;
        Stack<Integer> stack = new Stack<>();
        System.out.println("stack size : "+stack.size());
        System.out.println("stack capacity : "+stack.capacity());
        for(int i=0;i<11;i++){
            stack.push(i);
        }
        System.out.println("stack size : "+stack.size());
        System.out.println("stack capacity : "+stack.capacity());
    }

    //构造后缀表达式
    private static void test02(){
        String exp = "a + b * c - ( d + e / f )";
        String[] arr = exp.split(" ");
        StringBuffer strbuffer = new StringBuffer();
        Stack<Character> stack = new Stack<>();
        List<Character> operators = Arrays.asList('+','-','*','/','(',')');
        for (String str : arr){
            char c0 = str.charAt(0);
            if(!operators.contains(c0)){
                strbuffer.append(c0).append(' ');
            }
            else {
                while (!stack.isEmpty() && !comparePriority(c0, stack.peek())) {
                    Character operator = stack.pop();
                    if(operator == '(') {
                        break;
                    }
                    strbuffer.append(operator).append(' ');
                }
                if(c0 != ')') {
                    stack.push(c0);
                }
            }
        }
        while (!stack.isEmpty()){
            strbuffer.append(stack.pop()).append(' ');
        }
        System.out.println(strbuffer.toString());
    }

    //如果 a 优先级 > b 优先级，则返回 true
    private static boolean comparePriority(Character a,Character b) {
        if(b==null) {
            return true;
        }
        if(a=='(') {
            return true;
        }
        if((a=='*' || a=='/') && (b=='+' || b=='-')) {
            return true;
        }
        if(a!=')' && b=='(') {
            return true;
        }

        return false;
    }

}
