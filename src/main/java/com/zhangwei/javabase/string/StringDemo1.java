package com.zhangwei.javabase.string;

import javax.management.RuntimeErrorException;

public class StringDemo1 {
    public static void main(String[] args) {
        test02();
    }

    private static void test01(){
        String s1 = "";
        Double d1 = Double.valueOf(s1);
        System.out.println(s1+" : "+d1);
    }

    private static void test02(){
        DemoStr demoStr = new DemoStr(){{
            System.out.println("before:"+getStr());
            setStr("test02");
            System.out.println("after:"+getStr());
        }};
        System.out.println(demoStr);
    }
    
    public String toUpperCase(String str) {
		if(str==null)
			return null;
		return str.toUpperCase();
	}
    
    public String toLowerCase(String str) {
		if(str==null)
			throw new RuntimeException("str is null");
		return str.toLowerCase();
	}
    
    public String getTimes(String str) {
		if(str==null) {
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				//e.printStackTrace();
			}
			return null;
		}
		return str.toLowerCase();
	}
}

class DemoStr{
    private String str="initStr";

    public DemoStr(){
        this.str = "newStr";
    }

    public String getStr(){
        return this.str;
    }

    public void setStr(String str){
        this.str=str;
    }
    public String toString(){
        return this.str;
    }
}
