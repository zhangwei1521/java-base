package com.zhangwei.javabase.string;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class StringDemo1Test {

	StringDemo1 stringDemo1;
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println("beforeClass.......");
	}
	
	@Before
	public void setUp() {
		stringDemo1 = new StringDemo1();
		System.out.println("before.....");
	}
	
	@Test
	public void test01() {
		String str = "zhangwei";
		String upperString = stringDemo1.toUpperCase(str);
		Assert.assertEquals(str.toUpperCase(), upperString);
		
	}
	
	@Test(expected = RuntimeException.class)
	public void test02() {
		String str = null;
		String upperString = stringDemo1.toLowerCase(str);
		Assert.assertEquals(str.toUpperCase(), upperString);
		
	}
	
	@Test(timeout = 200)
	public void test03() {
		String str = null;
		String upperString = stringDemo1.getTimes(str);
		Assert.assertEquals(null, upperString);
		
	}
	
	@After
	public void tearDown() {
		System.out.println("after....");
	}
	
	@AfterClass
	public static void afterClass() {
		System.out.println("afterClass.......");
	}
}
