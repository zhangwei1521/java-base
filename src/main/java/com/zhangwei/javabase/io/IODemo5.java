package com.zhangwei.javabase.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class IODemo5 {
    public static void main(String[] args) {
        try (InputStream input = new FileInputStream("/tem_file/emoji.txt")){
            int n = input.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
