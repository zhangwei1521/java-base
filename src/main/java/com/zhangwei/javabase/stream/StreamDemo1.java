package com.zhangwei.javabase.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.IntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

public class StreamDemo1 {
    public static void main(String[] args) {
       /* Stream.of(1, 8, 5, 2, 1, 0, 9, 2, 0, 4, 8).filter(n->n>2).distinct().skip(1).limit(2).sorted().forEach(System.out::println);
        List<String> result = Stream.of("Hello Man".split("")).collect(Collectors.toList());
        result = Stream.of("Hello Man").map(s->s.split("")).flatMap(Arrays::stream).collect(Collectors.toList());
        List<String[]> list = Stream.of("Hello Man").map(s->s.split("")).collect(Collectors.toList());
        result.stream().forEach(System.out::println);
*/
        //Stream.iterate(0, n -> n + 2).limit(51).forEach(System.out::println);
        //Stream.of("a","b","c").map(s->s.toUpperCase()).forEach(System.out::println);
        //Stream.of("abc","ef","ghijk").map(s->s.length()).forEach(System.out::println);
        //Stream.of("abc","ef","ghijk").peek(s->s.replace('a','A')).forEach(System.out::println);
        /*Integer [] arr = Stream.of(1, 8, 5, 2).toArray((n)->new Integer[4]);
        Optional<Integer> result = Stream.of(1, 8, 5, 2).reduce((a,b)->a+b);
        System.out.println(result.orElseThrow(()->new RuntimeException("null")));*/
        /*Integer result = Stream.of(1, 8, 5, 2).reduce(0,Integer::sum);
        System.out.println(result);
        long size = Stream.of(1, 8, 5, 2).collect(Collectors.counting());
        List<Integer> list = Stream.of(1, 8, 5, 2).collect(Collectors.toList());
        Optional<Integer> max = Stream.of(1, 8, 5, 2).collect(Collectors.minBy(Comparator.comparing(Integer::valueOf)));
        System.out.println(max.orElseThrow(()->new RuntimeException("null")));
*/
        Employee emp1 = new Employee(1L,101L,"jack",8888D,6);
        Employee emp2 = new Employee(2L,101L,"mike",9999D,7);
        Employee emp3 = new Employee(3L,102L,"zake",8555D,6);
        Employee emp4 = new Employee(4L,102L,"cake",9566D,7);

        Stream<Employee> empStream = Stream.of(emp1,emp2,emp3,emp4);
        empStream.peek((emp)-> emp.setLevel(10)).forEach(System.out::println);
        /*empStream.map(emp->emp.getSal()).reduce(0D,Double::sum);
        DoubleSummaryStatistics salStatis = empStream.collect(Collectors.summarizingDouble(Employee::getSal));
        System.out.println("count: "+salStatis.getCount());
        System.out.println("sum: "+salStatis.getSum());
        System.out.println("average: "+salStatis.getAverage());
        System.out.println("max: "+salStatis.getMax());
        System.out.println("min: "+salStatis.getMin());*/
        //Map<Long,List<Employee>> empMap = empStream.collect(Collectors.groupingBy(Employee::getDepId));
        /*Map<String,List<Employee>> newMap = empStream.collect(Collectors.groupingBy(emp->{
            if(emp.getSal()>9000){
                return "A";
            }
            else if(emp.getSal()>8000){
                return "B";
            }
            else {
                return "C";
            }
        }));*/

        /*Map<Long,Map<String,List<Employee>>> mapMap = empStream.collect(Collectors.groupingBy(Employee::getDepId,Collectors.groupingBy(emp->{
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
        System.out.println(mapMap);*/
       /* Map<Long,Long> sizeMap = empStream.collect(Collectors.groupingBy(Employee::getDepId,Collectors.counting()));*/
       // Map<Long,Employee> maxMap = empStream.collect(groupingBy(Employee::getDepId,collectingAndThen(maxBy(comparingDouble(Employee::getSal)),Optional::get)));

    }
}
