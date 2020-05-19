package com.zhangwei.javabase.jdbc;

import com.zhangwei.javabase.common.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplateDemo {
    public static void main(String[] args) {
        try {
            Account account = new SimpleJdbcQueryTemplate().query("select * from acount where acountNum='10001'",
                    new ResultSetHandler<Account>() {
                        @Override
                        public Account handle(ResultSet rs) throws SQLException {
                            Account account = new Account();
                            if(rs.next()) {
                                account.setAcountNum(rs.getString("acountNum"));
                                account.setUsername(rs.getString("username"));
                                account.setAmount(rs.getDouble("amount"));
                            }
                            return account;
                        }
                    });
            System.out.println(account);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
