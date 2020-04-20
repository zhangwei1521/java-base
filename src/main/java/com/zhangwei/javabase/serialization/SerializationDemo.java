package com.zhangwei.javabase.serialization;

import java.io.*;

public class SerializationDemo {
    public static void main(String[] args) {
        //test01();
        //test02();
        test03();
    }

    private static void test01(){
        String fileName = "userfile";
        //序列化User对象到文件
        writeObjectToFile(createUser(),fileName);

        //单例对象反序列化
        User user1 = readObjectFromFile(fileName);
        System.out.println(user1);
        User user2 = readObjectFromFile(fileName);
        System.out.println(user2);
    }

    private static User createUser(){
        User user = User.getInstance();
        user.setAge(25);
        user.setName("zhangwei");
        user.setPass("root");
        System.out.println(user);
        return user;
    }

    private static String writeObjectToFile(Object obj,String fileName){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    private static <T> T readObjectFromFile(String fileName){
        T obj = null;
        File file = new File(fileName);
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            obj = (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return obj;
    }

    //测试使用Externalizable接口的序列化
    private static void test02(){
        String filename = "cupfile";
        Cup cup = new Cup(1,"square","porcelain");
        writeObjectToFile(cup,filename);
        Cup cup1 = readObjectFromFile(filename);
        System.out.println(cup1);
    }

    //测试serialVersionUID
    private static void test03(){
        //需要执行两次，第一次执行序列化，执行后手动修改serialVersionUID，第二次执行反序列化
        String filename = "cupfile";
        /*
        Cup cup = new Cup(1,"square","porcelain");
        writeObjectToFile(cup,filename);
        */
        Cup cup1 = readObjectFromFile(filename);
        System.out.println(cup1);
    }
}
