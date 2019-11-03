package com.zhangwei.javabase.io;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class EntityParser {
    public static void main(String[] args) {
        File file = getFile(args);
        if(file==null){
           return;
        }
        parseFile(file);
    }

    private static File getFile(String[] args){
        if(args==null || args.length<1 || args[0]==null || args[0].length()<1){
            System.err.println("文件名不能为空");
            return null;
        }
        String fileName = args[0];
        fileName = fileName.replaceAll("\\\\","/");
        System.out.println(fileName);
        File f1 = new File(fileName);
        if(!f1.exists()){
            System.err.println("文件不存在");
            return null;
        }
        if(!fileName.endsWith(".java")){
            System.err.println("文件类型错误");
            return null;
        }
        return f1;
    }

    private static void parseFile(File file){
        try (FileReader fileReader = new FileReader(file);
             LineNumberReader lineReader = new LineNumberReader(fileReader);
        ) {
            boolean flag = false;
            List<String> dbFieldsList = new ArrayList<>();
            List<String> enFieldsList = new ArrayList<>();
            String dbFieldName = "";
            String line = lineReader.readLine();
            while (line!=null){
                if(flag){
                    String fieldName = line.substring(line.lastIndexOf(" ")+1,line.lastIndexOf(";"));
                    dbFieldsList.add(dbFieldName);
                    enFieldsList.add(fieldName);
                    flag = false;
                }
                if(line.contains("@Column")){
                    dbFieldName = line.substring(line.indexOf("\"")+1,line.lastIndexOf("\""));
                    flag = true;
                }
                line = lineReader.readLine();
            }
            StringBuilder dbFieldsStr = new StringBuilder();
            StringBuilder dbFieldsStr2 = new StringBuilder();
            StringBuilder resultMaps = new StringBuilder();
            StringBuilder enFieldsStr = new StringBuilder();
            for(int i=0; i< enFieldsList.size();i++){
                String dbField = dbFieldsList.get(i);
                String enField = enFieldsList.get(i);
                dbFieldsStr.append(dbField);
                dbFieldsStr.append(",");
                dbFieldsStr2.append(dbField);
                dbFieldsStr2.append(",\n");

                resultMaps.append("<result column=\""+dbField+"\"  property=\""+enField+"\" jdbcType=\"VARCHAR\" />\n");
                enFieldsStr.append("#{"+enField+",jdbcType=VARCHAR},\n");
            }
            System.out.println(dbFieldsStr.toString());
            System.out.println("----------");
            System.out.println(dbFieldsStr2.toString());
            System.out.println("----------");
            System.out.println(enFieldsStr.toString());
            System.out.println("----------");
            System.out.println(resultMaps.toString());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
