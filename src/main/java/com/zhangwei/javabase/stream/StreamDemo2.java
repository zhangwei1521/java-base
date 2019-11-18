package com.zhangwei.javabase.stream;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamDemo2 {

    public static long sumFor(long n){
        long result = 0;
        for(long i=1L;i<=n;i++){
            result += i;
        }
        return result;
    }

    public static long sumStream(long n){
        return Stream.iterate(1L,i->i+1).limit(n).reduce(0L,Long::sum);
    }

    public static long sumParallelStream(long n){
        return Stream.iterate(1L,i->i+1).limit(n).parallel().reduce(0L,Long::sum);
    }

    public static long sumLongStream(long n){
        return LongStream.rangeClosed(1L,n).reduce(0L,Long::sum);
    }

    public static long sumParallelLongStream(long n){
        return LongStream.rangeClosed(1L,n).parallel().reduce(0L,Long::sum);
    }

    public static void main(String[] args) {
        long number = 20000000L;
        //System.out.println("total time of sumFor with number:"+number+" is: "+sumTest(StreamDemo2::sumFor,number)+" 毫秒");
        //System.out.println("total time of sumStream with number:"+number+" is: "+sumTest(StreamDemo2::sumStream,number)+" 毫秒");
        System.out.println("total time of sumParallelStream with number:"+number+" is: "+sumTest(StreamDemo2::sumParallelStream,number)+" 毫秒");
        //System.out.println("total time of sumLongStream with number:"+number+" is: "+sumTest(StreamDemo2::sumLongStream,number)+" 毫秒");
        //System.out.println("total time of sumParallelLongStream with number:"+number+" is: "+sumTest(StreamDemo2::sumParallelLongStream,number)+" 毫秒");
    }

    public static long sumTest(Function<Long,Long> func,long number){
        long maxValue = Long.MAX_VALUE;
        for(int i=0;i<10;i++){
            long start = System.nanoTime();
            long sum = func.apply(number);
            long end = System.nanoTime();
            System.out.println("result: "+sum);
            long time = (end-start)/1000000;
            if(time<maxValue){
                maxValue = time;
            }
        }
        return maxValue;
    }
}
