package com.zhangwei.javabase.jvm;

/**
 *
 */
public class JvmDemo6 {
    public static void main(String[] args) {
        //String s1 = "java";
        String str1 = new StringBuilder("ja").append("va").toString();

        //System.out.println(s1==str1);
        //.out.println(s1==str1.intern());
        //这里输出false，我的理解是String的常量池里默认保存了java这个字符串
        //str1.intern()返回常量池中的java串的引用，所以和str1不是同一个引用
        System.out.println(str1==str1.intern());

        //这里使用了"hello"，就会把它加入到String的常量池
        String str2 = new StringBuilder("hello").toString();
        //所以str2.intern()返回常量池中hello的音乐，不等于str2
        System.out.println(str2==str2.intern());

        //常量池中没有"hellojava"
        String str3 = new StringBuilder("hello").append("java").toString();
        //所以str3.intern()把str3的引用放入常量池，返回str3引用
        System.out.println(str3==str3.intern());

        String str4 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str4==str4.intern());

        String str5 = new StringBuilder("wor").append("ld").toString();
        System.out.println(str5== str5.intern());

        String str6 = new StringBuilder("cla").append("ss").toString();
        System.out.println(str6== str6.intern());

        //java -XX:PermSize=10M -XX:MaxPermSize=10M RuntimeConstantPoolOOM
        //jdk1.6及之前的虚拟机会发生oom，方法区内存溢出
        //jdk1.7开始String.intern方法不再将字符串常量复制保存到方法区，而是引用字符串对象在堆中的地址。
		/*List<String> list = new ArrayList<>();
		int i = 0;
		while(true) {
			list.add(String.valueOf(i++).intern());
		}*/
    }
}
