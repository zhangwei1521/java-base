package com.zhangwei.javabase.serialization;

import java.io.*;

public class SerializationDemo1 {
    public static void main(String[] args) {
        /*User user = User.getInstance();
        user.setAge(25);
        user.setName("zhangwei");
        System.out.println("--------");
        System.out.println(user.toString());

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tempfile"))) {
            oos.writeObject(user);
        } catch (IOException e){
            e.printStackTrace();
        }*/
        readObjectFromFile("tempfile");
        readObjectFromFile("tempfile");
    }

    private static void readObjectFromFile(String fileName){
        File file = new File(fileName);
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            User user1 = (User) ois.readObject();
            System.out.println("--------");
            System.out.println(user1);
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
