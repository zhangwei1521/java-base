package com.zhangwei.javabase.io;

import com.zhangwei.javabase.string.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class MdTool {

    public static void main(String[] args) {

    }

    public static void readFile(String fileName){
        if(StringUtils.isEmpty(fileName)){
            return;
        }
        File sourceFile = new File(fileName);
        String simpleFileName = sourceFile.getName().substring(0,sourceFile.getName().lastIndexOf(','));
        String targetFileName = "/tem_file/"+simpleFileName+".md";
        try (InputStream inputStream = new FileInputStream(fileName);
             Scanner scanner = new Scanner(inputStream);
             PrintWriter printWriter = new PrintWriter(new FileOutputStream(targetFileName));
        ){
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                if(line.startsWith("\t\t\t\t\t")){
                    String title = line.trim();
                    String mdTitile = "# "+title;
                    printWriter.write(mdTitile);
                    printWriter.println();
                    continue;
                }
                if(line.startsWith("\t\t\t\t\t")){
                    String title = line.trim();
                    String mdTitile = "# "+title;
                    printWriter.write(mdTitile);
                    printWriter.println();
                }
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
