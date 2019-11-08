package com.zhangwei.javabase.jdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


public class JdbcDemo3 {

    @Test
    public void test01(){
        String sqlStr = "insert into emp values(7903,'Jack','programer',7902,'2018-12-12',5221.13,450.58,98)";
        try {
            Connection conn = DBUtils.getConnection();
            Statement stmt = conn.createStatement();
            int rt = stmt.executeUpdate(sqlStr);
            DBUtils.closeResource(conn,stmt,null);
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void test02(){
        String sqlStr = "insert into emp values(?,?,?,?,?,?,?,?)";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlStr);
            ps.setInt(1,7904);
            ps.setString(2,"Nick");
            ps.setString(3,"programer");
            ps.setInt(4,7902);
            //ps.setString(5,"2000-12-12");
            ps.setDate(5,new Date(88,12,12));
            ps.setDouble(6,6000.16);
            ps.setDouble(7,550.45);
            ps.setDouble(8,20);
            int rt = ps.executeUpdate();
            DBUtils.closeResource(conn,ps,null);
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void test03(){
        String sqlStr = "select * from emp";
        try {
            Connection conn = DBUtils.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStr);
            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()){
                int empno = rs.getInt("empno");
                String ename = rs.getString("ename");
                String job = rs.getString("job");
                int mgr = rs.getInt("mgr");
                Date hiredate = rs.getDate("hiredate");
                double sal = rs.getDouble("sal");
                double comm = rs.getDouble("comm");
                double deptno = rs.getDouble("deptno");
                Employee employee = new Employee(empno,ename,job,mgr,hiredate,sal,comm,deptno);
                System.out.println(employee);
            }
            for(int i=1; i<=rsmd.getColumnCount(); i++ ){
                System.out.println(rsmd.getColumnName(i));;
            }
            DBUtils.closeResource(conn,stmt,rs);
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void test04(){
        try {
            Connection conn = DBUtils.getConnection();
            boolean autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("update emp set deptno=40 where empno=?");
            for(int i=0;i<3;i++){
                ps.setInt(1,7902+i);
                ps.addBatch();
            }
            ps.executeBatch();
            /*ps.setInt(1,7902);
            ps.executeUpdate();
            ps.setInt(1,7903);
            ps.executeUpdate()*/;
            conn.commit();
            conn.setAutoCommit(autoCommit);
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
}
