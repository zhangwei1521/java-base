package com.zhangwei.javabase.io;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.FileWriter;
import java.io.IOException;

public class IODemo9 {
    public static void main(String[] args) throws IOException {
        //testCharArrayReader();
        testCharArrayWriter();
    }

    private static void testCharArrayReader() throws IOException {
        String data = "*0123456789abcdefghijk";
        CharArrayReader reader = new CharArrayReader(data.toCharArray());
        System.out.println((char)reader.read());
        char[] cbuf = new char[10];
        int count = reader.read(cbuf);
        System.out.println(String.valueOf(cbuf,0,count));

        if(reader.markSupported()){
            reader.mark(10);
            reader.skip(4);
            System.out.println((char)reader.read());
            reader.reset();
        }

        count = reader.read(cbuf);
        System.out.println(String.valueOf(cbuf,0,count));
    }

    private static void testCharArrayWriter() throws IOException {
        String data = "0123456789abcdefghijk";
        char [] chars = new char[data.length()];
        data.getChars(0,data.length(),chars,0);
        CharArrayWriter writer = new CharArrayWriter(4);
        writer.write(chars,0,chars.length);
        //System.out.println(writer.toString());

        FileWriter fileWriter = new FileWriter("/tem_file/file5-1");
        writer.writeTo(fileWriter);
        fileWriter.flush();
        fileWriter.close();
    }
}
