package com.zhangwei.javabase.design.template;

import java.util.*;

public class JavaCalculator extends AbstractCalculator {

    List<Character> operators = Arrays.asList('+','-','*','/','(',')');

    @Override
    protected void doCalculate(String exp) {
        String postExp = buildPostExp(exp);
        //System.out.println(postExp);
        String[] arr = postExp.split(" ");
        Stack<String> stack = new Stack<>();
        for (String str : arr) {
            char c0 = str.charAt(0);
            if (!operators.contains(c0)) {
                stack.push(str);
            } else {
                double val;
                double v2 = Double.valueOf(stack.pop());
                double v1 = Double.valueOf(stack.pop());
                switch (c0){
                    case '+':
                        val = v1 + v2;
                        stack.push(String.valueOf(val));
                        break;
                    case '-':
                        val = v1 - v2;
                        stack.push(String.valueOf(val));
                        break;
                    case '*':
                        val = v1 * v2;
                        stack.push(String.valueOf(val));
                        break;
                    case '/':
                        val = v1 / v2;
                        stack.push(String.valueOf(val));
                        break;
                    default:
                        break;
                }
            }
        }
        System.out.println(stack.pop());
    }

    private String buildPostExp(String exp){
        String[] arr = exp.split(" ");
        StringBuffer strbuffer = new StringBuffer();
        Queue<String> postExpQueue = new LinkedList<>();
        Stack<String> stack = new Stack<>();

        for (String str : arr){
            char c0 = str.charAt(0);
            if(!operators.contains(c0)){
                strbuffer.append(str).append(' ');
                postExpQueue.offer(str);
            }
            else {
                while (!stack.isEmpty() && !comparePriority(c0, stack.peek().charAt(0))) {
                    Character operator = stack.pop().charAt(0);
                    if(operator == '(') {
                        break;
                    }
                    strbuffer.append(operator).append(' ');
                    postExpQueue.offer(operator+"");
                }
                if(c0 != ')') {
                    stack.push(c0+"");
                }
            }
        }
        while (!stack.isEmpty()){
            strbuffer.append(stack.pop()).append(' ');
        }
        return strbuffer.toString();
        //return postExpQueue;
    }

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
