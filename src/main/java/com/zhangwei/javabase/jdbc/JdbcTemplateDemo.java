package com.zhangwei.javabase.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplateDemo {
    public static void main(String[] args) {
        try {
            Acount acount = new SimpleJdbcQueryTemplate().query("select * from acount where acountNum='10001'",
                    new ResultSetHandler<Acount>() {
                        @Override
                        public Acount handle(ResultSet rs) throws SQLException {
                            Acount acount = new Acount();
                            if(rs.next()) {
                                acount.setAcountNum(rs.getString("acountNum"));
                                acount.setUsername(rs.getString("username"));
                                acount.setAmount(rs.getDouble("amount"));
                            }
                            return acount;
                        }
                    });
            System.out.println(acount);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
