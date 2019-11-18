package com.zhangwei.javabase.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtils {
    //private static BasicDataSource dataSource = new BasicDataSource();
    private static String driver = null;
    private static String url = null;
    private static String user = null;
    private static String password = null;

    static {
        InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("database.properties");
        Properties prop = new Properties();
        try {
            prop.load(inputStream);
            driver = prop.getProperty("driver");
            url = prop.getProperty("url");
            user = prop.getProperty("user");
            password = prop.getProperty("password");
            /*dataSource.setDriverClassName(driver);
            dataSource.setUrl(url);
            dataSource.setUsername(user);
            dataSource.setPassword(password);*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url,user,password);
        //return dataSource.getConnection();
        return conn;
    }

    public static void closeResource(Connection conn, Statement stmt, ResultSet rs){
        if(rs != null){
            try {
                rs.close();
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
    }
}
