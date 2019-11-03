package com.zhangwei.javabase.stream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class Employee {
    private long empId;
    private long depId;
    private String name;
    private double sal;
    private int level;
}
