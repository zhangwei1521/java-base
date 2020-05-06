package com.zhangwei.javabase.collection.my;

//平方探测哈希表
public class QuadraticProbingHashTable<T> {

    private static final int DEFAULT_TABLE_SIZE = 11;

    private static class HashEntry<T>{
        public T element;
        public boolean isAlive;

        public HashEntry(T t){
            this(t,true);
        }

        public HashEntry(T t, boolean isAlive){
            this.element = t;
            this.isAlive = isAlive;
        }
    }

    private HashEntry<T> [] array;

    private int currentSize;

    public QuadraticProbingHashTable(){
        this(DEFAULT_TABLE_SIZE);
    }

    public QuadraticProbingHashTable(int size){
        allocateArray(size);
        makeEmpty();
    }

    public void makeEmpty(){
        currentSize = 0;
        for(int i=0; i < array.length; i++){
            array[i] = null;
        }
    }

    public void insert(T t){
        int pos = findPos(t);
        if(isAlive(pos)){
            return;
        }
        array[pos] = new HashEntry<>(t,true);
        if(++currentSize > array.length/2){
            rehash();
        }
    }

    public void remove(T t){
        int pos = findPos(t);
        if(isAlive(pos)){
            array[pos].isAlive = false;
        }
    }

    public boolean contains(T t){
        int pos = findPos(t);
        return isAlive(pos);
    }

    public String toString(){
        StringBuffer stringBuffer = new StringBuffer();
        for(int i=0; i<array.length;i++){
            if(array[i] != null && array[i].isAlive){
                stringBuffer.append(array[i].element).append(',');
            }
        }
        String str;
        if(stringBuffer.length()>0){
            str = stringBuffer.substring(0,stringBuffer.length()-1);
        }
        else {
            str = stringBuffer.toString();
        }
        return str;
    }

    private void allocateArray(int size){
        array = new HashEntry[size];
    }

    private boolean isAlive(int pos){
        return array[pos] != null && array[pos].isAlive;
    }

    private int findPos(T t){
        int offset = 1;
        int pos = myhash(t);
        while (array[pos] != null &&
                !array[pos].element.equals(t)){
            pos += offset;
            offset += 2;
            if(pos >= array.length){
                pos -= array.length;
            }
        }
        return pos;
    }

    private void rehash(){
        HashEntry<T> [] oldArray = array;
        allocateArray(nextPrime(oldArray.length * 2));
        for(HashEntry<T> item : oldArray){
            if(item.isAlive){
                insert(item.element);
            }
        }
    }

    private int myhash(T t){
        int hashVal = t.hashCode();
        hashVal %= array.length;
        if(hashVal < 0){
            hashVal += array.length;
        }
        return hashVal;
    }

    //找到刚好比n大的素数
    private static int nextPrime(int n){
        int m = n;
        while (!isPrime(m)){
            m++;
        }
        return m;
    }

    //判断n是否是素数
    private static boolean isPrime(int n){
        if(n<=3){
            return n>1;
        }
        double m = Math.sqrt(n);
        for(int i=2; i<=m; i++){
            if(n % i == 0){
                return false;
            }
        }
        return false;
    }
}
