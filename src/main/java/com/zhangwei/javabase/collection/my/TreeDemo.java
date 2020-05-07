package com.zhangwei.javabase.collection.my;

import java.util.*;
import java.util.List;

public class TreeDemo {
    public static void main(String[] args) {
        //test01();
        //test02();
        //test03();
        test04();
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

    //测试二叉搜索树
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

    //测试AVL树
    private static void test03(){
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);

        tree.insert(16);
        tree.insert(15);
        tree.insert(14);
        tree.insert(13);
        tree.insert(12);
        tree.insert(11);
        tree.insert(10);
        tree.insert(8);
        tree.insert(9);

        tree.printTree();
    }

    //测试伸展树
    private static void test04(){
        //先看普通的二叉搜索树在极端情况下的形式
        BinarySearchTree<Integer> searchTree = new BinarySearchTree<>();
        for(int i=11;i>0;i--){
            searchTree.insert(i);
        }
        searchTree.printTree();
        System.out.println("==================");
        //使用相同数据插入伸展树，然后再调用contains看看调整的树结构
        StretchTree<Integer> stretchTree = new StretchTree<>();
        for(int i=11;i>0;i--){
            stretchTree.insert(i);
        }
        stretchTree.printTree();
        System.out.println("==================");
        for(int i=1;i<9;i++){
            stretchTree.contains(i);
        }
        stretchTree.printTree();
        System.out.println("==================");
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

class AvlTree<T extends Comparable<? super T>>{
    private static class AvlNode<T>{
        T element;
        AvlNode<T> left;
        AvlNode<T> right;
        int height;

        AvlNode(T t){
            this(t,null,null);
        }

        AvlNode(T t,AvlNode<T> l,AvlNode<T> r){
            this.element = t;
            this.left = l;
            this.right = r;
            this.height = 0;
        }
    }

    private AvlNode<T> root;

    AvlTree(){
        this.root = null;
    }

    void insert(T t){
        root = insert(t,root);
    }

    private AvlNode<T> insert(T t,AvlNode<T> n){
        if(n==null){
            return new AvlNode<>(t);
        }
        int result = t.compareTo(n.element);
        if(result < 0){
            n.left = insert(t,n.left);
            if(height(n.left) - height(n.right) == 2){
                if(t.compareTo(n.left.element) < 0){
                    n = rotateWithLeftChild(n);
                }
                else {
                    n = doubleRotateWithLeftChild(n);
                }
            }
        }
        else if(result > 0){
            n.right = insert(t,n.right);
            if(height(n.right) - height(n.left) == 2){
                if(t.compareTo(n.right.element) > 0){
                    n = rotateWithRightChild(n);
                }
                else {
                    n = doubleRotateWithRightChild(n);
                }
            }
        }
        n.height = Math.max(height(n.left),height(n.right))+1;
        return n;
    }

    private int height(AvlNode<T> n){
        return n==null ? -1 : n.height;
    }

    private AvlNode<T> rotateWithLeftChild(AvlNode<T> n){
        AvlNode<T> l = n.left;
        n.left = l.right;
        l.right = n;
        n.height = Math.max(height(n.left),height(n.right))+1;
        l.height = Math.max(height(l.left),n.height)+1;
        return l;
    }

    private AvlNode<T> rotateWithRightChild(AvlNode<T> n){
        AvlNode<T> r = n.right;
        n.right = r.left;
        r.left = n;
        n.height = Math.max(height(n.left),height(n.right))+1;
        r.height = Math.max(n.height,height(r.right))+1;
        return r;
    }

    private AvlNode<T> doubleRotateWithLeftChild(AvlNode<T> n){
        n.left = rotateWithRightChild(n.left);
        return rotateWithLeftChild(n);
    }

    private AvlNode<T> doubleRotateWithRightChild(AvlNode<T> n){
        n.right = rotateWithLeftChild(n.right);
        return rotateWithRightChild(n);
    }

    void printTree(){
        //使用队列来实现广度优先遍历(按层打印)
        Queue<AvlNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        do {
            AvlNode<T> node = queue.poll();
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

class StretchTree<T extends Comparable<? super T>>{
    private static class Node<T>{
        T element;
        Node<T> left;
        Node<T> right;

        Node(T element){
            this(element,null,null);
        }

        Node(T element, Node<T> left, Node<T> right){
            this.element = element;
            this.left = left;
            this.right = right;
        }
    }

    Node<T> root;

    StretchTree(){
        root = null;
    }

    void insert(T t){
        root = insert(t,root);
    }

    private Node<T> insert(T t,Node<T> node){
        if(node == null){
            return new Node<>(t);
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

    private class Box{
        boolean find;
    }

    boolean contains(T t){
        Box box = new Box();
        root = contains(t,root,box);
        return box.find;
    }

    private Node<T> contains(T t, Node<T> node,Box box){
        box.find = false;
        if(node == null){
            return node;
        }
        int result = t.compareTo(node.element);
        if(result < 0){
            node.left = contains(t,node.left,box);
            if(node.left != null){
                int r = t.compareTo(node.left.element);
                if(r < 0){
                    node = rotateWithLeft(node);
                }
                else if(r > 0){
                    node = rotateWithLeftRight(node);
                }
            }
        }
        else if(result > 0){
            node.right = contains(t,node.right,box);
            if(node.right != null){
                int r = t.compareTo(node.right.element);
                if(r < 0){
                    node = rotateWithRightLeft(node);
                }
                else if(r > 0){
                    node = rotateWithRight(node);
                }
            }
        }
        else {
            box.find = true;
        }
        return node;
    }

    //左边一字伸展
    private Node<T> rotateWithLeft(Node<T> n) {
        Node<T> l = n.left;
        if(l == null){
            return n;
        }
        Node<T> ll = l.left;
        if(ll == null){
            return n;
        }
        n.left = l.right;
        l.right = n;
        l.left = ll.right;
        ll.right = l;
        return ll;
    }

    //左边之字伸展
    private Node<T> rotateWithLeftRight(Node<T> n) {
        Node<T> l = n.left;
        if(l == null){
            return n;
        }
        Node<T> lr = l.right;
        if(lr == null){
            return n;
        }
        l.right = lr.left;
        lr.left = l;
        n.left = lr.right;
        lr.right = n;
        return lr;
    }

    //右边之字伸展
    private Node<T> rotateWithRightLeft(Node<T> n) {
        Node<T> r = n.right;
        if(r == null){
            return n;
        }
        Node<T> rl = r.left;
        if(rl == null){
            return n;
        }
        r.left = rl.right;
        rl.right = r;
        n.right = rl.left;
        rl.left = n;
        return rl;
    }

    //右边一字伸展
    private Node<T> rotateWithRight(Node<T> n) {
        Node<T> r = n.right;
        if(r == null){
            return n;
        }
        Node<T> rr = r.right;
        if(rr == null){
            return n;
        }
        n.right = r.left;
        r.left = n;
        r.right = rr.left;
        rr.left = r;
        return rr;
    }

    void printTree(){
        //使用队列来实现广度优先遍历(按层打印)
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        do {
            Node<T> node = queue.poll();
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