package com.zhangwei.javabase.jvm;

/**
 * 分析class文件结构
 */
public class JvmDemo2 {
    private static String HELLO_WORLD="hello world";
    public static void main(String[] args) {
        System.out.println(HELLO_WORLD);
    }
}

/*
    编译后class文件：
		cafe babe 0000 0034 0022 0a00 0700 1309 0014 0015 0900 0600 160a 0017 0018 0800 1907 001a 0700 1b01 000b 4845 4c4c 4f5f
		574f 524c 4401 0012 4c6a 6176 612f 6c61	6e67 2f53 7472 696e 673b 0100 063c 696e	6974 3e01 0003 2829 5601 0004 436f 6465
		0100 0f4c 696e 654e 756d 6265 7254 6162	6c65 0100 046d 6169 6e01 0016 285b 4c6a	6176 612f 6c61 6e67 2f53 7472 696e 673b
		2956 0100 083c 636c 696e 6974 3e01 000a	536f 7572 6365 4669 6c65 0100 0a48 656c	6c6f 2e6a 6176 610c 000a 000b 0700 1c0c
		001d 001e 0c00 0800 0907 001f 0c00 2000	2101 000c 4865 6c6c 6f20 576f 726c 6421	0100 0548 656c 6c6f 0100 106a 6176 612f
		6c61 6e67 2f4f 626a 6563 7401 0010 6a61	7661 2f6c 616e 672f 5379 7374 656d 0100	036f 7574 0100 154c 6a61 7661 2f69 6f2f
		5072 696e 7453 7472 6561 6d3b 0100 136a	6176 612f 696f 2f50 7269 6e74 5374 7265	616d 0100 0770 7269 6e74 6c6e 0100 1528
		4c6a 6176 612f 6c61 6e67 2f53 7472 696e 673b 2956 0021 0006 0007 0000 0001 000a	0008 0009 0000 0003 0001 000a 000b 0001
		000c 0000 001d 0001 0001 0000 0005 2ab7	0001 b100 0000 0100 0d00 0000 0600 0100	0000 0100 0900 0e00 0f00 0100 0c00 0000
		2600 0200 0100 0000 0ab2 0002 b200 03b6	0004 b100 0000 0100 0d00 0000 0a00 0200	0000 0400 0900 0500 0800 1000 0b00 0100
		0c00 0000 1e00 0100 0000 0000 0612 05b3	0003 b100 0000 0100 0d00 0000 0600 0100	0000 0200 0100 1100 0000 0200 12
	根据各单元长度分段：
		cafe babe		//magic
		0000			//minor_version=0
		0034			//major_version=52,即jdk1.8
		0022			//constant_pool_count，共34-1=33个常量
		//常量池开始
		0a 0007 0013	1 //CONSTANT_Methodref_info,后面是方法名常量索引和方法描述符常量索引，即第7个常量和第19个常量，
		09 0014 0015	2 //CONSTANT_Fieldref_info,后面是字段名常量索引和字段描述符常量索引，即第20个常量和第21个常量，
		09 0006 0016	3 //CONSTANT_Fieldref_info,后面是字段名常量索引和字段描述符常量索引，即第6个常量和第22个常量，
		0a 0017 0018 	4 //CONSTANT_Methodref_info,后面是方法名常量索引和方法描述符常量索引，即第23个常量和第24个常量，
		08 0019 		5 //CONSTANT_String_info,后面是字符串常量索引，即第25个索引，即 Hello World!
		07 001a 		6 //CONSTANT_Class_info，后面是类或接口全限定名常量项索引，即第26个常量，即 Hello
		07 001b			7 //CONSTANT_Class_info，后面是类或接口全限定名常量项索引，即第27个常量，即 java/lang/Object
		01 000b 4845 4c4c 4f5f 574f 524c 44								8	HELLO_WORLD
		01 0012 4c6a 6176 612f 6c61 6e67 2f53 7472 696e 673b			9	Ljava/lang/String;
		01 0006 3c69 6e69 743e											10	<init>
		01 0003 2829 56													11	()V
		01 0004 436f 6465												12	Code
		01 000f 4c69 6e65 4e75 6d62 6572 5461 626c 65					13	LineNumberTable
		01 0004 6d61 696e												14	main
		01 0016 285b 4c6a 6176 612f 6c61 6e67 2f53 7472 696e 673b 2956	15	([Ljava/lang/String;)V
		01 0008 3c63 6c69 6e69 743e										16	<clinit>
		01 000a 536f 7572 6365 4669 6c65 								17	SourceFile
		01 000a 4865 6c6c 6f2e 6a61 7661								18	Hello.java
		0c 000a 000b	19 //CONSTANT_NameAndType_info，后面是名称常量索引和描述常量索引，即第10个常量和第11个常量，即 <init>和()V
		07 001c 		20 //CONSTANT_Class_info，后面是类或接口全限定名常量项索引，即第28个常量，即 java/lang/System
		0c 001d 001e	21 //CONSTANT_NameAndType_info，后面是名称常量索引和描述常量索引，即第29个常量和第30个常量，即 out和Ljava/io/PrintStream;
		0c 0008 0009	22 //CONSTANT_NameAndType_info，后面是名称常量索引和描述常量索引，即第8个常量和第9个常量，即 HELLO_WORLD和Ljava/lang/String;
		07 001f 		23 //CONSTANT_Class_info，后面是类或接口全限定名常量项索引，即第31个常量，即 java/io/PrintStream
		0c 0020 0021	24 //CONSTANT_NameAndType_info，后面是名称常量索引和描述常量索引，即第32个常量和第32个常量，即 println和(Ljava/lang/String;)V
		01 000c 4865 6c6c 6f20 576f 726c 6421							25	Hello World!
		01 0005 4865 6c6c 6f 											26	Hello
		01 0010 6a61 7661 2f6c 616e 672f 4f62 6a65 6374					27	java/lang/Object
		01 0010 6a61 7661 2f6c 616e 672f 5379 7374 656d					28	java/lang/System
		01 0003 6f75 74													29	out
		01 0015 4c6a 6176 612f 696f 2f50 7269 6e74 5374 7265 616d 3b	30	Ljava/io/PrintStream;
		01 0013 6a61 7661 2f69 6f2f 5072 696e 7453 7472 6561 6d			31	java/io/PrintStream
		01 0007 7072 696e 746c 6e										32	println
		01 0015 284c 6a61 7661 2f6c 616e 672f 5374 7269 6e67 3b29 56	33	(Ljava/lang/String;)V
		//常量池结束
		0021			//this_class access_flags，0021=0001+0020，即 public
		0006			//this_class， 即第6个常量，即 Hello
		0007			//super_class，即第7个常量，即 java/lang/Object
		0000			//interfaces_count，接口数为 0
		0001			//fields_count ，字段数为 1
		000a 			//field access_flags，000a=0002+0008,即 private static
		0008 			//field name_index,即第8个常量，即 HELLO_WORLD
		0009			//field descriptor_index,即第9个常量，即 Ljava/lang/String;
		0000 			//field attributes_count,属性数为0
		0003			//methods_count,方法数为 3

		0001			//method access_flags,即 public
		000a			//method name_index,即第10个常量，即 <init>
		000b 			//method descriptor_index,即第11个常量，即 ()V
		0001			//method attributes_count,这个方法有一个属性
		000c			//index，第12个常量，即	Code属性，
		0000 001d		//code属性长度，不包括这里的4个字节和index的2个字节，这里code属性总长为29个字节
		0001 			//max_stack=1，操作数栈最大深度
		0001 			//max_locals=1，局部变量表最大Slot数量（一个Slot存放一个32位以内的数据类型）
		0000 0005 		//code_length,表示接下来的5个字节为方法的内容
		2ab7 0001 b1
		0000			// method exception_table_length,这里异常表长度为0，所以后面没有异常表内容。
		0001			// code属性的属性表长度，这里为1
		000d 			// name_index,即第13个常量，即LineNumberTable
		0000 0006 		// attribute_length,这里LineNumberTable长度为6个字节
		0001			// line_number_table_length;这里为1，即只有1组源码、字节码行号映射
		0000 0001 		// 表示字节码行号0000对应源码行号0001

		0009 			// 0009=0001+0008，即 public static
		000e 			// 第14个常量，即 main
		000f 			// 第15个常量，即 ([Ljava/lang/String;)V
		0001 			// 这个方法有1个属性
		000c 			// Code属性
		0000 0026 		// 这里code属性总长为38个字节
		0002 			// max_stack=2，
		0001 			// max_locals=1，
		0000 000a 		// code_length,表示接下来的10个字节为方法的内容
		b200 02b2 0003 b600 04b1 	//0xb2即getstatic指令，用于访问静态数据，后面的2个字节0002表示第二个常量，第二个常量引用了第20个和
									//第21个常量，即System和PrintStream；后面的字节b2和0003表示访问第三个常量，即Hello类的HELLO_WORLD,
									//字节b6是invokevirtual指令，后面的0004表示第4个常量，即PrintStream类的实例方法println，这里就是将
									//HELLO_WORLD作为参数传递给println方法执行；字节b1表示return指令。
		0000 			// 异常表长度为0
		0001 			// code属性的属性表长度，这里为1
		000d 			// LineNumberTable
		0000 000a 		// 这里LineNumberTable长度为10个字节
		0002 			// 2组源码、字节码行号映射
		0000 0004 		// 字节码行号0000对应源码行号0004
		0009 0005 		// 字节码行号0009对应源码行号0005

		0008 			// static
		0010 			// <clinit>
		000b 			// ()V
		0001 			// 这个方法有1个属性
		000c 			// Code属性
		0000 001e 		// 这里code属性总长为30个字节
		0001 			// max_stack=1，
		0000 			// max_locals=0，
		0000 0006 		// code_length,表示接下来的6个字节为方法的内容
		1205 b300 03b1
		0000 			// 异常表长度为0
		0001  			// code属性的属性表长度，这里为1
		000d 			// LineNumberTable
		0000 0006 		// 这里LineNumberTable长度为6个字节
		0001 			// 1组源码、字节码行号映射
		0000 0002 		// 字节码行号0000对应源码行号0002

		0001 			// this_class attributes_count 类属性数量为1
		0011 			// 属性名常量索引，第十七个常量，即 SourceFile
		0000 0002 		// 属性长度：2个字节
		0012			// sourcefile_index,第18个常量，即 Hello.java
	注：常量池中字符串字面量常量如果含有中文，则采用UTF-8编码占用3个字节（实际上class文件中字符串常量默认使用UTF-8编码）

 */
