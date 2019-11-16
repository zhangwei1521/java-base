package com.zhangwei.javabase.io;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class IODemo1 {
    public static void main(String[] args) {
        //newFile();;
        seeFile();
        //seeOtherInfo();
        //seeDirectory();
        //createDir();
        //testFileDescriptor();
    }

    private static void newFile(){
        File f1 = new File("D:/");
        System.out.println(f1.getName());
        System.out.println(f1.getPath());
        System.out.println("-------------");
        File f2 = new File("D:/","book");
        System.out.println(f2.getName());
        System.out.println(f2.getPath());
        System.out.println("-------------");
        File f3 = new File(f1,"book");
        System.out.println(f3.getName());
        System.out.println(f3.getPath());
        File f4 = new File(f2.toURI());
    }

    private static void seeFile(){
        File f1 = new File("D:/tem_file/threadinfo.txt");
        System.out.println("file name: "+f1.getName());
        System.out.println("file path: "+f1.getPath());
        System.out.println("file abs path: "+f1.getAbsolutePath());
        System.out.println("file parent: "+f1.getParent());
        System.out.println("file exists ? "+f1.exists());
        System.out.println("file can write? "+f1.canWrite());
        System.out.println("file can read? "+f1.canRead());
        System.out.println("file is directory？ "+f1.isDirectory());
        System.out.println("file is file? "+f1.isFile());
        System.out.println("file is abs? "+f1.isAbsolute());
        System.out.println("file last modified: "+f1.lastModified());
        System.out.println("file size: "+f1.length()+" bytes");

        System.out.println("---------");
        boolean result = f1.renameTo(new File("D:/tem_file/threadinfo3.txt"));
        System.out.println("file rename to success? "+result);
        System.out.println("file name: "+f1.getName());
        System.out.println("file exists ? "+f1.exists());
        result = f1.delete();
        System.out.println("file delete success? "+result);
    }

    private static void seeOtherInfo(){
        File f1 = new File("/tem_file/threadinfo1.txt");
        System.out.println("disk part free space: "+f1.getFreeSpace());
        System.out.println("disk part total space:"+f1.getTotalSpace());
        System.out.println("disk part usable space:"+f1.getUsableSpace());
        System.out.println("file is hidden? "+f1.isHidden());
        System.out.println("file modify lastModifiedTime success? "+f1.setLastModified(new Date().getTime()));
        //System.out.println("file set readOnly success? "+f1.setReadOnly());
        System.out.println(f1.setWritable(false));
    }

    private static void seeDirectory(){
        File f1 = new File("/tem_file");
        MyFilenameFilter filenameFilter = new MyFilenameFilter("jpg");
        MyFileFilter fileFilter = new MyFileFilter();

        if(f1.isDirectory()){
            File [] fs = f1.listFiles(fileFilter);
            for (File fi : fs){
                if(fi.isDirectory()){
                    System.out.println(fi.getName() + " is a directory");
                }
                else {
                    System.out.println(fi.getName() + " is a file");
                }
            }
        }
        /*
        if(f1.isDirectory()){
            String[] fs = f1.list(filenameFilter);
            for(String fn : fs){
                File f2 = new File(f1.getPath()+"/"+fn);
                if(f2.isDirectory()){
                    System.out.println(fn + " is a directory");
                }
                else {
                    System.out.println(fn + " is a file");
                }
            }
        }
        */
    }

    private static void createDir(){
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
            File file = new File("/tem_file/file4");
            //file.createNewFile();
            FileDescriptor fd = new FileOutputStream("/tem_file/file4").getFD();
            FileOutputStream out = new FileOutputStream(fd);
            out.write(5);
            FileInputStream input = new FileInputStream(fd);
            char c = (char)input.read();
            System.out.println("char from file3: "+c);

            out.close();
            //input.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
