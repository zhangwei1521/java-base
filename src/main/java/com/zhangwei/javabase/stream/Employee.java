package com.zhangwei.javabase.stream;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Employee {
	private long empId;
    private long depId;
    private String name;
    private double sal;
    private int level;

    public Employee(long empId, long depId, String name, double sal, int level) {
        this.empId = empId;
        this.depId = depId;
        this.name = name;
        this.sal = sal;
        this.level = level;
    }

    public Object setLevel(int i) {
		this.level = i;
		return null;
	}
}
