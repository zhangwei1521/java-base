package com.zhangwei.javabase.collection.my;

public class HashUtil {

    /**
     * 如果 tableSize 远大于 key.length() * 127
     * 则 此分配不是均匀的
     * @param key
     * @param tableSize
     * @return
     */
    public static int hash1(String key, int tableSize){
        int hashVal = 0;
        for(int i=0;i<key.length();i++){
            hashVal += key.charAt(i);
        }
        return hashVal % tableSize;
    }

    /**
     * 这里的思路是把 key的前3位转为一个27进制的数
     * 27 是 26 个字母加 1 个空格
     * 在英语词典中，三字母组合的有效组合数为2851，而不是26*26*26
     * 如果 tableSize 远大于 2851，则此散列也不是均匀的
     * @param key
     * @param tableSize
     * @return
     */
    public static int hash2(String key, int tableSize){
        int hashVal = 0;
        for(int i=0; i<3; i++){
            hashVal += key.charAt(i)*(Math.pow(27,i));
        }
        return hashVal % tableSize;
    }

    /**
     * 将 key 转为37进制数，转换后的数可能会溢出成为负数
     * @param key
     * @param tableSize
     * @return
     */
    public static int hash3(String key, int tableSize){
        int hashVal = 0;
        for(int i=0; i<key.length(); i++){
            hashVal = 37 * hashVal + key.charAt(i);
        }
        /*hashVal %= tableSize;
        if(hashVal < 0){
            hashVal += tableSize;
        }*/
        //另一种做法
        hashVal &= (tableSize-1);
        return hashVal;
    }

    public static int hash4(String key){
        int hash = 0;
        if (key.length() > 0) {
            char val[] = key.toCharArray();

            for (int i = 0; i < val.length; i++) {
                hash = 31 * hash + val[i];
            }
        }
        return hash;
    }
}
