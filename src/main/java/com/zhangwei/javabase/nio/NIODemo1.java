package com.zhangwei.javabase.nio;

import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;

public class NIODemo1 {
    public static void main(String[] args) throws IOException {
        listDir();
    }

    private static void readFile1(){
        int count;
        Path filepath;
        filepath = Paths.get("/tem_file/file1.txt");
        try (SeekableByteChannel channel = Files.newByteChannel(filepath)) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            count = channel.read(buffer);
            for(;count != -1;){
                buffer.rewind();
                for(int i=0;i<count;i++){
                    System.out.println((char)buffer.get());
                }
                count = channel.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readFile2(){
        Path filepath;
        filepath = Paths.get("/tem_file/file1.txt");
        try (FileChannel channel = (FileChannel) Files.newByteChannel(filepath);){
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY,0,channel.size());
            for(int i=0;i<buffer.limit();i++){
                System.out.println((char)buffer.get());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeFile1(){
        try (FileChannel channel = (FileChannel)Files.newByteChannel(Paths.get("/tem_file/file1.txt"),
                StandardOpenOption.WRITE,StandardOpenOption.CREATE);){
            ByteBuffer buffer = ByteBuffer.allocate(26);
            for(int i=0;i<26;i++){
                buffer.put((byte) ('A'+i));
            }
            buffer.rewind();
            channel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeFile2(){
        try (FileChannel channel = (FileChannel)Files.newByteChannel(Paths.get("/tem_file/file1.txt"),
                StandardOpenOption.WRITE,StandardOpenOption.CREATE,StandardOpenOption.READ,StandardOpenOption.TRUNCATE_EXISTING)){
            ByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE,0,26);
            for(int i=0;i<26;i++){
                buffer.put((byte) ('a'+i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copyFile(){
        Path src = Paths.get("/tem_file/file1.txt");
        Path des1 = Paths.get("/tem_file/file2.txt");
        Path des2 = Paths.get("/tem_file/file3.txt");
        try {
            Files.copy(src,des1, StandardCopyOption.COPY_ATTRIBUTES,StandardCopyOption.REPLACE_EXISTING);
            Files.copy(src,des2,StandardCopyOption.COPY_ATTRIBUTES);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readAttributes() throws IOException {
        Path file = Paths.get("/tem_file/file1.txt");
        Path path = Paths.get("/tem_file/");
        BasicFileAttributes attributes = Files.readAttributes(file,BasicFileAttributes.class);
        System.out.println(attributes.isDirectory());
        System.out.println(attributes.lastModifiedTime());
        System.out.println(attributes.creationTime());
        System.out.println(attributes.fileKey());
    }

    private static void listDir(){
        Path dir = Paths.get("/tem_file/");
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir,"*.{txt,jpg}")) {
            for(Path item : directoryStream){
                System.out.println(Files.isDirectory(item)?"DIR> "+item.getFileName():item.getFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
