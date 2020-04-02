package com.zhangwei.javabase.jdbc;

import java.sql.*;

public class JdbcDemo1 {
    public static void main(String[] args){
        //test01();
        test02();
    }

    private static void test01(){
        String url="jdbc:mysql://127.0.0.1:3306/java";
        String user="root";
        String password="root";

        String sql = "select * from acount";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,user,password);
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()){
                Acount acount = new Acount();
                acount.setAcountNum(resultSet.getString("acountNum"));
                acount.setUsername(resultSet.getString("username"));
                acount.setAmount(resultSet.getDouble("amount"));
                System.out.println(acount);
            }
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void test02(){
        String sql = "select * from acount";
        try(
                Connection conn = DBUtils.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet resultSet = stmt.executeQuery(sql);
        ) {
            while (resultSet.next()){
                Acount acount = new Acount();
                acount.setAcountNum(resultSet.getString("acountNum"));
                acount.setUsername(resultSet.getString("username"));
                acount.setAmount(resultSet.getDouble("amount"));
                System.out.println(acount);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
