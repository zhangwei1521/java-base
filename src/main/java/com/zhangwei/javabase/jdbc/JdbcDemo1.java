package com.zhangwei.javabase.jdbc;

import java.sql.*;

public class JdbcDemo1 {
    public static void main(String[] args){
        String url="jdbc:mysql://127.0.0.1:3306/network_info";
        String user="root";
        String password="root";

        String sql = "select * from account";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,user,password);
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()){
                Account account = new Account();
                account.setId(resultSet.getInt("id"));
                account.setName(resultSet.getString("name"));
                account.setNum(resultSet.getString("num"));
                account.setPwd(resultSet.getString("pwd"));
                System.out.println(account);
            }
            DBUtils.closeResource(conn,stmt,resultSet);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
