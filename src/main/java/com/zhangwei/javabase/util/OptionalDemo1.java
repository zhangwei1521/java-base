package com.zhangwei.javabase.util;

import java.util.Optional;

public class OptionalDemo1 {
    public static void main(String[] args) {
        String msg = "hello";
        //Optional<String> msgOp = Optional.empty();
        //Optional<String> msgOp = Optional.of(null);
        Optional<String> msgOp = Optional.ofNullable(null);
        msgOp.ifPresent(System.out::println);
        Optional<Integer> msgLenOp = msgOp.map(String::length);
        msgLenOp.ifPresent(System.out::println);
        String val = msgOp.orElse("world");
        System.out.println(val);
        val = msgOp.orElseGet(()->"world");
        System.out.println(val);
        //val = msgOp.orElseThrow(()->new RuntimeException("content is null"));
        msgOp = msgOp.filter(msg1->msg1.equals("hello"));
        msgOp.ifPresent(System.out::println);

        User user = new User(22,"zhangsan");
        Optional<User> userOp = Optional.ofNullable(user);
        String userName = userOp.map(User::getName).map(String::toUpperCase).orElse(null);
        System.out.println(userName);
    }
}
