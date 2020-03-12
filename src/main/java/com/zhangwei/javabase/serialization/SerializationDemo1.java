package com.zhangwei.javabase.serialization;

import java.io.*;

public class SerializationDemo1 {
    public static void main(String[] args) {

        //writeObjectToFile("tempfile");
        readObjectFromFile("tempfile");
        readObjectFromFile("tempfile");
    }

    private static String writeObjectToFile(String fileName){
        User user = User.getInstance();
        user.setAge(25);
        user.setName("zhangwei");
        System.out.println("--------");
        System.out.println(user.toString());

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    private static void readObjectFromFile(String fileName){
        File file = new File(fileName);
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            User user1 = (User) ois.readObject();
            System.out.println("--------");
            System.out.println(user1);
            System.out.println(user1.getName());
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
