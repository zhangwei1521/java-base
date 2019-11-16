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
    public Employee(long l, long m, String string, double d, int i) {
		
	}
	private long empId;
    private long depId;
    private String name;
    private double sal;
    private int level;
	public Object setLevel(int i) {
		// TODO Auto-generated method stub
		return null;
	}
}
