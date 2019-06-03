package com.zhangwei.javabase.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PushbackInputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

public class IODemo2 {
    public static void main(String[] args) throws IOException {
        //testFin();
        //testFout();
        //testBain();
        //testBaout();
        //testPrintStream();
        //testDataStream();
        testRAFile();
    }

    private static void testFin() throws IOException {
        FileInputStream fin1 = new FileInputStream("/tem_file/threadinfo1.txt");
        File f2 = new File("/tem_file/threadinfo2.txt");
        if(f2.exists()){
            FileInputStream fin2 = new FileInputStream(f2);
            System.out.println("available bytes: "+fin2.available());
            int result = fin2.read();
            System.out.println("available bytes: "+fin2.available());
            byte[] buffer = new byte[9];
            result = fin2.read(buffer);
            System.out.println("available bytes: "+fin2.available());
            fin2.skip(6);
            result = fin2.read(buffer,5,4);
            System.out.println("available bytes: "+fin2.available());
            System.out.println("-----");
            System.out.println("mark supported? "+fin2.markSupported());
            fin2.close();
        }
        fin1.close();
    }

    private static void testFout() throws IOException{
        try ( FileOutputStream fout1 = new FileOutputStream("tem_file/file1"); ){

        } catch (IOException e){
            throw e;
        }
        String str = "FileOutputStream is a model for write data to file";
        byte [] buffer = str.getBytes();
        //
        File file2 = new File("/tem_file/file2");
        if(!file2.exists()){
            FileOutputStream fout2 = new FileOutputStream(file2);
            fout2.write(buffer);

            fout2.close();
        }
        else {
            FileOutputStream fout3 = new FileOutputStream(file2,true);
            fout3.write((int)'\n');
            fout3.write(buffer,0,buffer.length/2);

            fout3.close();
        }
    }

    private static void testBain(){
        String str = "abcdefghijklmn";
        byte [] source = str.getBytes();
        ByteArrayInputStream baIn1 = new ByteArrayInputStream(source);
        ByteArrayInputStream baIn2 = new ByteArrayInputStream(source,0,source.length);
        int c;
        while ((c = baIn2.read()) != -1){
            if(c=='h'){
                baIn2.mark(1000);
                System.out.print(Character.toUpperCase((char)c));
            }
            else {
                System.out.print((char)c);
            }
        }
        System.out.println("----");
        baIn2.reset();
        c = baIn2.read();
        System.out.println((char)c);
    }

    private static void testBaout() throws IOException {
        ByteArrayOutputStream baout = new ByteArrayOutputStream(3);
        String str = "abcdefg";
        byte [] buffer = str.getBytes();
        baout.write(buffer);
        System.out.println(baout.size());
        System.out.println(baout.toString());
        byte [] bytes = baout.toByteArray();
        for(int i=0;i<bytes.length;i++) System.out.print((char)bytes[i]);
        FileOutputStream fout = new FileOutputStream("/tem_file/file2");
        baout.writeTo(fout);
        fout.flush();
        fout.close();
        baout.reset();
        for(int i=0;i<3;i++) baout.write('X');
        System.out.println();
        System.out.println(baout.toString());
    }

    private static void testPrintStream(){
        try {
            PrintStream pts = new PrintStream("/tem_file/file2");
            pts.printf(Locale.CHINA,"my chinese name is %s, I'm %d years old.\n","张伟",25);
            pts.printf(Locale.US,"my english name is %s\n","john");
            pts.format("hello %s","jenny");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void testDataStream() {
        StringBuffer sb = new StringBuffer();
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream("/tem_file/file3"));
            dos.writeInt(5);
            dos.writeBoolean(true);
            dos.writeChar(98);
            dos.writeDouble(12.56);
            dos.writeFloat(3.14f);
            dos.writeUTF("a爱");
            dos.flush();
            dos.close();

            DataInputStream dis = new DataInputStream(new FileInputStream("/tem_file/file3"));
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

    private static void testRAFile(){
        try {
            RandomAccessFile rf = new RandomAccessFile("/tem_file/file3","rwd");
            int a = rf.readInt();
            rf.seek(5);
            char c = rf.readChar();
            System.out.println(a+" : "+c);
            rf.setLength(5);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
