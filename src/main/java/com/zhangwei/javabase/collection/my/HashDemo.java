package com.zhangwei.javabase.collection.my;

public class HashDemo {
    public static void main(String[] args) {
        //test01();
        //test02();
        test03();
    }

    private static void test01() {
        String key = "hello";
        int tableSize = 10007;

        int hashVal1 = HashUtil.hash1(key,tableSize);
        System.out.println("hashVal1 : "+hashVal1);

        int hashVal2 = HashUtil.hash2(key,tableSize);
        System.out.println("hashVal2 : "+hashVal2);

        int hashVal3 = HashUtil.hash3(key,tableSize);
        System.out.println("hashVal3 : "+hashVal3);
    }

    private static void test02(){
        SeparateChainingHashTable<String> hashTable = new SeparateChainingHashTable<>();
        hashTable.insert("hello");
        hashTable.insert("java");
        hashTable.insert("hash");
        if(hashTable.contains("java")){
            System.out.println("contains java");
        }
        hashTable.remove("hello");
        hashTable.makeEmpty();

        System.out.println("--------------------");
        hashTable.insert("hello");
        hashTable.insert("java");
        hashTable.insert("hash");
        System.out.println(hashTable);

        System.out.println("--------------------");
        QuadraticProbingHashTable<String> hashTable2 = new QuadraticProbingHashTable<>();
        hashTable2.insert("hello");
        hashTable2.insert("java");
        hashTable2.insert("hash");
        System.out.println(hashTable2);
    }

    private static void test03(){
        String s1 = "dd";
        int h1 = HashUtil.hash4(s1);
        System.out.println(s1+" : "+h1);
        String s2 = "eE";
        int h2 = HashUtil.hash4(s2);
        System.out.println(s2+" : "+h2);
        //System.out.println(h1-101*31);
        //System.out.println(Integer.valueOf('A'));
    }

}
