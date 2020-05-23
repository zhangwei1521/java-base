package com.zhangwei.javabase.io;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * File API测试
 */
public class IODemo1 {
    public static void main(String[] args) throws IOException {
        //testDir();
        //testFile();
        //testDiskSpace();
        //testFileOperation();
        //testFilenameFilter();
        //testFileFilter();
        //testDirOperation();
        testFileDescriptor();
    }

    private static void testDir() throws IOException {
        File f1 = new File("D:/");
        System.out.println("name : "+f1.getName());
        System.out.println("path : "+f1.getPath());
        System.out.println("-------------");

        File f2 = new File("D:/","book");
        System.out.println("name : "+f2.getName());
        System.out.println("path : "+f2.getPath());
        System.out.println("-------------");

        File f3 = new File(f1,"book");
        System.out.println("name : "+f3.getName());
        System.out.println("path : "+f3.getPath());
        System.out.println("absolute file : "+f3.getAbsoluteFile());
        System.out.println("absolute path : "+f3.getAbsolutePath());
        System.out.println("is absolute ? "+f3.isAbsolute());
        System.out.println("-------------");

        File f4 = new File(f2.toURI());
        System.out.println("path : "+f4.getPath());
        System.out.println("uri : "+f4.toURI());
        System.out.println("-------------");

        File f5 = new File("");
        System.out.println("exists : "+f5.exists());
        System.out.println("name : "+f5.getName());
        System.out.println("path : "+f5.getPath());
        System.out.println("absolute file : "+f5.getAbsoluteFile());
        System.out.println("absolute path : "+f5.getAbsolutePath());
        System.out.println("-------------");

        File f6 = new File("K:/");
        System.out.println("exists : "+f6.exists());
        System.out.println("name : "+f6.getName());
        System.out.println("path : "+f6.getPath());
        System.out.println("absolute file : "+f6.getAbsoluteFile());
        System.out.println("absolute path : "+f6.getAbsolutePath());
        System.out.println("-------------");

        File f7 = new File("./");
        System.out.println("exists : "+f7.exists());
        System.out.println("name : "+f7.getName());
        System.out.println("path : "+f7.getPath());
        System.out.println("absolute file : "+f7.getAbsoluteFile());
        System.out.println("canonical file : "+f7.getCanonicalFile());
        System.out.println("absolute path : "+f7.getAbsolutePath());
        System.out.println("canonical path : "+f7.getCanonicalPath());
        System.out.println("is absolute ? "+f7.isAbsolute());
        System.out.println("-------------");

        File f8 = new File(f7,"src");
        System.out.println("path : "+f8.getPath());
        System.out.println("absolute path : "+f8.getAbsolutePath());
        System.out.println("canonical path : "+f8.getCanonicalPath());
        System.out.println("-------------");
    }

    private static void testFile() throws IOException {
        File f1 = new File("D:/tem_file/file1");
        System.out.println("file1 exists ? "+f1.exists());//true
        System.out.println("file1 name: "+f1.getName());
        System.out.println("file1 path: "+f1.getPath());
        System.out.println("file1 absolute path: "+f1.getAbsolutePath());
        System.out.println("file1 canonical path: "+f1.getCanonicalPath());
        System.out.println("file1 parent: "+f1.getParent());
        System.out.println("file1 can write? "+f1.canWrite());
        System.out.println("file1 can read? "+f1.canRead());
        System.out.println("file1 is directory? "+f1.isDirectory());
        System.out.println("file1 is file? "+f1.isFile());
        System.out.println("file1 is absolute? "+f1.isAbsolute());
        System.out.println("file1 is hidden? "+f1.isHidden());
        System.out.println("file1 last modified: "+f1.lastModified());
        System.out.println("file1 size: "+f1.length()+" bytes");
    }

    private static void testDiskSpace(){
        File f2 = new File("/tem_file/file2");
        System.out.println("disk part free space: "+f2.getFreeSpace());
        System.out.println("disk part total space:"+f2.getTotalSpace());
        System.out.println("disk part usable space:"+f2.getUsableSpace());
    }

    private static void testFileOperation() throws IOException {
        File f3 = new File("D:/tem_file/file3");
        System.out.println("file3 exists ? "+f3.exists());//true
        System.out.println("file3 canonical path: "+f3.getCanonicalPath());

        File f4 = new File(f3.getParent(),"file4");
        System.out.println("file4 exists ? "+f4.exists());//false

        boolean result = f3.renameTo(f4);
        System.out.println("file3 rename success? "+result);//true
        System.out.println("file3 exists ? "+f3.exists());//false
        System.out.println("file4 exists ? "+f4.exists());//true
        result = f3.delete();
        System.out.println("file3 delete success? "+result);//false
        result = f4.delete();
        System.out.println("file4 delete success? "+result);//true

        f3.createNewFile();

        System.out.println("file3 modify lastModifiedTime success? "+f3.setLastModified(new Date().getTime()));
        System.out.println("file3 set readOnly success? "+f3.setReadOnly());
        System.out.println("file3 set writable success? "+f3.setWritable(true));
    }

    private static void testFilenameFilter(){
        File f1 = new File("D:/tem_file");
        if(f1.isDirectory()){
            MyFilenameFilter filenameFilter = new MyFilenameFilter("txt");
            File [] fs = f1.listFiles(filenameFilter);
            for (File fi : fs){
                System.out.println(fi.getPath());
            }
        }
    }

    private static void testFileFilter(){
        File f1 = new File("/tem_file");
        if(f1.isDirectory()){
            MyFileFilter fileFilter = new MyFileFilter();
            fileFilter.canRead=true;
            fileFilter.canWrite=true;
            fileFilter.canExecute=true;
            fileFilter.lastModifiedTime=System.currentTimeMillis()-1000*30*30*24*30;
            File [] fs = f1.listFiles(fileFilter);
            for (File fi : fs){
                if(fi.isDirectory()){
                    System.out.println("dir  : "+fi.getName());
                }
                else {
                    System.out.println("file : "+fi.getName());
                }
            }
        }
    }

    private static void testDirOperation(){
        File dir1 = new File("/tem_file/dir1");
        boolean result = dir1.mkdir();
        System.out.println("make dir1 success? "+result);
        File dir2 = new File("/tem_file/dir0/dir2");
        result =dir2.mkdir();
        System.out.println("make dir2 success? "+result);
        result =dir2.mkdirs();
        System.out.println("make dir2 success? "+result);
    }

    private static void testFileDescriptor()  {
        try {
            File f4 = new File("/tem_file/file4");
            FileOutputStream out = new FileOutputStream(f4);
            FileDescriptor fd = out.getFD();
            out.write(5);
            FileInputStream input = new FileInputStream(fd);

            //这里报错，因为不能使用同一个FileDescriptor同时进行读写操作
            char c = (char)input.read();
            System.out.println("char from file4: "+c);

            out.close();
            input.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
