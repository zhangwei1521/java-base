package com.zhangwei.javabase.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

public class CopyFile {
    public static void main(String[] args) throws IOException {
        if(args==null || args.length!=2){
            System.err.println("参数异常");
            return;
        }
        File source = new File(args[0]);
        File target = new File(args[1]);
        if(!source.exists()){
            System.err.println("源文件不存在");
            return;
        }
        if(target.exists()){
            System.out.println("目标文件已存在，是否覆盖？");
            Scanner scanner = new Scanner(System.in);
            String result = scanner.next();
            while (true){
                if(result.equals("y")){
                    break;
                }
                else if(result.equals("n")){
                    return;
                }
                result = scanner.next();
            }
        }
        FileChannel in = new FileInputStream(source).getChannel();
        FileChannel out = new FileOutputStream(target).getChannel();
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        while (true){
            buffer.clear();
            int r = in.read(buffer);
            if(r==-1){
                break;
            }
            buffer.flip();
            out.write(buffer);
        }
        in.close();
        out.close();
        System.out.println("复制完成！");
    }
}
