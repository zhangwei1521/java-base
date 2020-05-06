package com.zhangwei.javabase.collection.my;

import java.util.*;
import java.util.List;

public class TreeDemo {
    public static void main(String[] args) {
        //test01();
        test02();
    }

    //根据后缀表达式构造二叉表达式树
    private static void test01() {
        //普通表达式： a + b * c - ( d + e / f )
        //后缀表达式： a b c * + d e f / + -
        String postfixExp = "a b c * + d e f / + -";
        ExpressionsTree expressionsTree = new ExpressionsTree(postfixExp);
        System.out.println(expressionsTree.getInOrderExp());
        System.out.println(expressionsTree.getPostOrderExp());
        System.out.println(expressionsTree.getPreOrderExp());
    }

    private static void test02(){
        BinarySearchTree<Integer> searchTree = new BinarySearchTree<>();
        searchTree.insert(5);
        searchTree.insert(7);
        searchTree.insert(3);
        searchTree.insert(2);
        searchTree.insert(8);
        searchTree.insert(4);
        searchTree.insert(6);
        searchTree.printTree();
        System.out.println("is empty ? "+searchTree.isEmpty());
        System.out.println("max : "+searchTree.findMax());
        System.out.println("min : "+searchTree.findMin());
        System.out.println("contains 1 ? "+searchTree.contains(1));
        System.out.println("contains 7 ? "+searchTree.contains(7));
        searchTree.remove(5);
        searchTree.printTree();
    }
}

//表达式树
class ExpressionsTree{
    private static class Node{
        Object element;
        Node left;
        Node right;
    }

    private Node root;

    //使用后缀表达式构造表达式数
    ExpressionsTree(String postfixExp){
        Stack<Node> stack = new Stack<>();
        String[] arr = postfixExp.split(" ");
        List<Character> operators = Arrays.asList('+','-','*','/','(',')');
        for (String str : arr){
            char c0 = str.charAt(0);
            Node node = new Node();
            node.element = c0;
            if(operators.contains(c0)){
                node.right = stack.pop();
                node.left = stack.pop();
            }
            stack.push(node);
        }
        root = stack.pop();
    }

    //中序遍历，返回普通表达式
    String getInOrderExp(){
        return getInOrderExp(root).replaceAll(" +"," ").trim();
    }

    private String getInOrderExp(Node node){
        StringBuilder expBuilder = new StringBuilder();
        if(node != null){
            expBuilder.append(getInOrderExp(node.left)).append(' ');
            expBuilder.append(node.element).append(' ');

            boolean flag = false;
            if(((Character) node.element == '-') && node.right != null &&
                    (((Character) node.right.element == '-') ||
                            ((Character) node.right.element == '+')))
            {
                flag = true;
                expBuilder.append("( ");
            }

            expBuilder.append(getInOrderExp(node.right)).append(' ');

            if(flag){
                expBuilder.append(')');
            }
        }
        return expBuilder.toString();
    }

    //后序遍历，返回后缀表达式
    String getPostOrderExp(){
        return getPostOrderExp(root).replaceAll(" +"," ").trim();
    }

    private String getPostOrderExp(Node node){
        StringBuilder expBuilder = new StringBuilder();
        if(node != null){
            expBuilder.append(getPostOrderExp(node.left)).append(' ');
            expBuilder.append(getPostOrderExp(node.right)).append(' ');
            expBuilder.append(node.element).append(' ');
        }
        return expBuilder.toString();
    }

    //先序遍历，返回前缀表达式
    String getPreOrderExp(){
        return getPreOrderExp(root).replaceAll(" +"," ").trim();
    }

    private String getPreOrderExp(Node node){
        StringBuilder expBuilder = new StringBuilder();
        if(node != null){
            expBuilder.append(node.element).append(' ');
            expBuilder.append(getPreOrderExp(node.left)).append(' ');
            expBuilder.append(getPreOrderExp(node.right)).append(' ');
        }
        return expBuilder.toString();
    }
}

//二叉查找树
class BinarySearchTree<T extends Comparable<? super T>>{
    private static class BinaryNode<T>{
        T element;
        BinaryNode<T> left;
        BinaryNode<T> right;

        BinaryNode(T element){
            this(element,null,null);
        }

        BinaryNode(T element,BinaryNode<T> left,BinaryNode<T> right){
            this.element = element;
            this.left = left;
            this.right = right;
        }
    }

    private BinaryNode<T> root;

    BinarySearchTree(){
        root = null;
    }

    void makeEmpty(){
        root = null;
    }

    boolean isEmpty(){
        return root == null;
    }

    boolean contains(T t){
        return contains(t,root);
    }

    // t不能为null
    private boolean contains(T t, BinaryNode<T> node){
        if(node == null){
            return false;
        }
        int result = t.compareTo(node.element);
        if(result < 0){
            return contains(t,node.left);
        }
        else if(result > 0){
            return contains(t,node.right);
        }
        else {
            return true;
        }
    }

    T findMin(){
        BinaryNode<T> node = findMinNode(root);
        if(node == null){
            throw new RuntimeException("the tree is empty");
        }
        return node.element;
    }

    private BinaryNode<T> findMinNode(BinaryNode<T> node){
        if(node == null){
            return null;
        }
        else if(node.left == null){
            return node;
        }
        else {
            return findMinNode(node.left);
        }
    }

    T findMax(){
        if(isEmpty()){
            throw new RuntimeException("the tree is empty");
        }
        return findMaxNode(root).element;
    }

    private BinaryNode<T> findMaxNode(BinaryNode<T> node) {
        if(node==null){
            return null;
        }
        while (node.right != null){
            node = node.right;
        }
        return node;
    }

    void insert(T t){
        root = insert(t,root);
    }

    private BinaryNode<T> insert(T t,BinaryNode<T> node){
        if(node == null){
            return new BinaryNode<>(t);
        }
        int result = t.compareTo(node.element);
        if(result < 0){
            node.left = insert(t,node.left);
        }
        else if(result > 0){
            node.right = insert(t,node.right);
        }
        else{
            ;
        }
        return node;
    }

    void remove(T t){
        root = remove(t,root);
    }

    private BinaryNode<T> remove(T t,BinaryNode<T> node){
        if(node == null){
            return null;
        }
        int result = t.compareTo(node.element);
        if(result < 0){
            node.left = remove(t,node.left);
        }
        else if(result > 0){
            node.right = remove(t,node.right);
        }
        else if(node.left != null && node.right != null){
            node.element = findMinNode(node.right).element;
            node.right = remove(node.element,node.right);
        }
        else {
            node = node.left == null ? node.right : node.left;
        }
        return node;
    }

    void printTree(){
        //使用队列来实现广度优先遍历(按层打印)
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        do {
            BinaryNode<T> node = queue.poll();
            if(node != null){
                System.out.print(node.element+"\t");
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
            else if(!queue.isEmpty()){
                queue.add(null);
                System.out.println();
            }
        } while (!queue.isEmpty());
        System.out.println();
    }
}