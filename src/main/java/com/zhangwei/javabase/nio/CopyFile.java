package com.zhangwei.javabase.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Scanner;

public class CopyFile {
    public static void main(String[] args) throws IOException {
        copyUseBuffer(args);
        copyWithNoBuffer(args);
    }

    private static void copyUseBuffer(String[] args) throws IOException {
        LocalTime time1 = LocalTime.now();
        checkArgs(args);
        File source = new File(args[0]);
        File target = new File(args[1]);
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
        Duration duration = Duration.between(time1,LocalTime.now());
        System.out.println("time : "+(duration.getSeconds()+" s "+duration.getNano()+" ns"));
    }

    private static void checkArgs(String[] args){
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
        /*if(target.exists()){
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
        }*/
    }

    // 使用零拷贝：这种方式下，传输数据并不需要将源数据从内核态拷贝到用户态，
    // 再从用户态拷贝到目标通道的内核态，也避免了两次用户态和内核态间的上下文切换，
    // 传输效率显著高于普通模式
    private static void copyWithNoBuffer(String[] args) throws IOException {
        LocalTime time1 = LocalTime.now();
        checkArgs(args);
        File source = new File(args[0]);
        File target = new File(args[1]);
        FileChannel in = new FileInputStream(source).getChannel();
        FileChannel out = new FileOutputStream(target).getChannel();
        in.transferTo(0,in.size(),out);
        in.close();
        out.close();
        System.out.println("复制完成！");
        Duration duration = Duration.between(time1,LocalTime.now());
        System.out.println("time : "+(duration.getSeconds()+" s "+duration.getNano()+" ns"));
    }
}
