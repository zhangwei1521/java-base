package com.zhangwei.javabase.stream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

public class StreamDemo1 {
    public static void main(String[] args) throws IOException {

        //test01();

        //test02();

        //test03();

        test04();
    }

    //构造 stream 对象
    private static void test01() throws IOException {
        Stream<String> stream1 = Stream.of("A", "B", "C", "D");

        Stream<?> stream2 = Stream.empty();

        String[] array = {"A", "B", "C", "D"};
        Stream<String> stream3 = Arrays.stream(array);

        Stream<Integer> stream4 = Arrays.asList(1,2).stream();

        Stream<Integer> stream5 = Stream.iterate(0, n -> n + 2).limit(51);

        Stream<String> stream6 = Stream.generate(() -> "Hello Man!").limit(10);

        Stream<String> stream7 = Files.lines(Paths.get("src/main/resources/database.properties"), Charset.defaultCharset());
    }

    //流的操作
    private static void test02(){
        System.out.println("stream foreach : ");
        Stream.of("abc","def","ghijk").forEach(System.out::print);

        System.out.println("\nstream filter : ");
        Stream.of(1, 8, 5, 2, 1, 0, 9, 2, 0, 4, 8).filter(n->n>2).forEach(item-> System.out.print(item+"\t"));

        System.out.println("\nstream map : ");
        Stream.of("abc","def","ghijk").map(s->s.length()).forEach(item-> System.out.print(item+"\t"));
        System.out.println();
        Stream.of("a","b","c").map(s->s.toUpperCase()).forEach(item-> System.out.print(item+"\t"));

        System.out.println("\nstream distinct : ");
        Stream.of(1, 8, 5, 2, 1, 0, 9, 2, 0, 4, 8).distinct().forEach(item-> System.out.print(item+"\t"));

        System.out.println("\nstream sorted : ");
        Stream.of(1, 8, 5, 2, 1, 0, 9, 2, 0, 4, 8).sorted().forEach(item-> System.out.print(item+"\t"));

        System.out.println("\nstream limit : ");
        Stream.of(1, 8, 5, 2).limit(2).forEach(item-> System.out.print(item+"\t"));

        System.out.println("\nstream skip : ");
        Stream.of(1, 8, 5, 2).skip(2).forEach(item-> System.out.print(item+"\t"));

        System.out.println("\nstream peek : ");
        //replace不会修改原字符串
        /*Stream.of("abc","ef","ghijk").peek(s->s.replace('a','A')).forEach(item-> System.out.print(item+"\t"));
        System.out.println();*/
        Stream<Employee> empStream = getEmpStream();
        empStream.peek((emp)-> emp.setLevel(10)).forEach(item-> System.out.print(item+"\t"));

        System.out.println("\nmix : ");
        Stream.of(1, 8, 5, 2, 1, 0, 9, 2, 0, 4, 8).distinct().filter(n->n>2).skip(1).limit(2).sorted().forEach(item-> System.out.print(item+"\t"));

        System.out.println();
        List<String> result = Stream.of("HelloMan".split("")).collect(Collectors.toList());
        System.out.println(result);

        List<String[]> list = Stream.of("Hello","Man").map(s->s.split("")).collect(Collectors.toList());
        list.forEach(item-> System.out.print(Arrays.toString(item)+"\t"));

        System.out.println();
        result = Stream.of("Hello","Man").map(s->s.split("")).flatMap(Arrays::stream).collect(Collectors.toList());
        System.out.println(result);

        System.out.println("to array : ");
        Integer [] array = Stream.of(1, 8, 5, 2).toArray((n)->new Integer[4]);
        System.out.println(Arrays.toString(array));
    }

    //收集元素
    private static void test03(){
        Optional<Integer> result = Stream.of(1, 8, 5, 2).reduce((a,b)->a+b);
        System.out.println(result.orElseThrow(()->new RuntimeException("error")));

        Integer sum = Stream.of(1, 8, 5, 2).reduce(0,Integer::sum);
        System.out.println(sum);

        long size = Stream.of(1, 8, 5, 2).collect(Collectors.counting());
        System.out.println(size);

        List<Integer> list = Stream.of(1, 8, 5, 2).collect(Collectors.toList());
        System.out.println(list);

        Optional<Integer> max = Stream.of(1, 8, 5, 2).collect(Collectors.maxBy(Comparator.comparing(Integer::valueOf)));
        System.out.println(max.orElseThrow(()->new RuntimeException("null")));
    }

    //统计API
    private static void test04(){
        Stream<Employee> empStream = getEmpStream();

        double salSum = empStream.map(emp->emp.getSal()).reduce(0D,Double::sum);
        System.out.println("salSum : "+salSum);

        //stream不能重复使用，这里需要重新获取
        empStream = getEmpStream();
        DoubleSummaryStatistics salStatis = empStream.collect(Collectors.summarizingDouble(Employee::getSal));
        System.out.println("count : "+salStatis.getCount());
        System.out.println("sum : "+salStatis.getSum());
        System.out.println("average : "+salStatis.getAverage());
        System.out.println("max : "+salStatis.getMax());
        System.out.println("min : "+salStatis.getMin());

        //stream不能重复使用，这里需要重新获取
        empStream = getEmpStream();
        Map<Long,List<Employee>> empMap = empStream.collect(Collectors.groupingBy(Employee::getDepId));
        empMap.forEach((k,v)-> System.out.println(k+" : "+v));

        //stream不能重复使用，这里需要重新获取
        empStream = getEmpStream();
        Map<String,List<Employee>> newMap = empStream.collect(Collectors.groupingBy(emp->{
            if(emp.getSal()>9000){
                return "A";
            }
            else if(emp.getSal()>8000){
                return "B";
            }
            else {
                return "C";
            }
        }));
        newMap.forEach((k,v)-> System.out.println(k+" : "+v));

        //stream不能重复使用，这里需要重新获取
        empStream = getEmpStream();
        Map<Long,Map<String,List<Employee>>> mapMap = empStream.collect(Collectors.groupingBy(Employee::getDepId,Collectors.groupingBy(emp->{
            if(emp.getSal()>9000){
                return "A";
            }
            else if(emp.getSal()>8000){
                return "B";
            }
            else {
                return "C";
            }
        })));
        System.out.println(mapMap);

        //stream不能重复使用，这里需要重新获取
        empStream = getEmpStream();
        Map<Long,Long> sizeMap = empStream.collect(Collectors.groupingBy(Employee::getDepId,Collectors.counting()));
        sizeMap.forEach((k,v)-> System.out.println(k+" : "+v));

        //stream不能重复使用，这里需要重新获取
        empStream = getEmpStream();
        Map<Long,Employee> maxMap = empStream.collect(Collectors.groupingBy(Employee::getDepId,collectingAndThen(maxBy(comparingDouble(Employee::getSal)),Optional::get)));
        maxMap.forEach((k,v)-> System.out.println(k+" : "+v));
    }

    private static Stream<Employee> getEmpStream(){
        Employee emp1 = new Employee(1L,101L,"jack",8888D,6);
        Employee emp2 = new Employee(2L,101L,"mike",9999D,7);
        Employee emp3 = new Employee(3L,102L,"zake",8555D,6);
        Employee emp4 = new Employee(4L,102L,"cake",9566D,7);

        Stream<Employee> empStream = Stream.of(emp1,emp2,emp3,emp4);

        return empStream;
    }
}
