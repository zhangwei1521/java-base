package com.zhangwei.javabase.io;

import java.io.*;
import java.util.Locale;

public class IODemo3 {
    public static void main(String[] args) {
        //testPrintStream();
        //testDataStream();
    }

    private static void testPrintStream(){
        try {
            String fileName = "/tem_file/testfile1";
            PrintStream pts = new PrintStream(fileName);
            pts.printf(Locale.CHINA,"my chinese name is %s, I'm %d years old.\n","张三",25);
            pts.printf(Locale.US,"my english name is %s\n","john");
            pts.format("hello %s","jenny");
            pts.close();

            /*File testfile1 = new File(fileName);
            testfile1.delete();*/
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void testDataStream() {
        StringBuffer sb = new StringBuffer();
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream("/tem_file/testfile2"));
            dos.writeInt(5);
            dos.writeBoolean(true);
            dos.writeChar(98);
            dos.writeDouble(12.56);
            dos.writeFloat(3.14f);
            dos.writeUTF("中文");
            dos.flush();
            dos.close();

            DataInputStream dis = new DataInputStream(new FileInputStream("/tem_file/testfile2"));
            int a = dis.readInt();
            boolean b = dis.readBoolean();
            char c = dis.readChar();
            double d = dis.readDouble();
            float f = dis.readFloat();
            String s = dis.readUTF();
            sb.append(a).append(b).append(c).append(d).append(f).append(s);

            dis.close();
            System.out.println("read finished : ");
            System.out.println(sb.toString());
        } catch (IOException e) {
            if(e instanceof EOFException){
                System.out.println("read finished : ");
                System.out.println(sb.toString());
            }
            else {
                e.printStackTrace();
            }
        }

    }

}
