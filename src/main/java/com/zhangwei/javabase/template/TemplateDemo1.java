package com.zhangwei.javabase.template;

import com.zhangwei.javabase.jdbc.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TemplateDemo1 {
    public static void main(String[] args) {
        try {
            Account account = new SimpleJdbcQueryTemplate().query("select * from account where id=1",
                    new ResultSetHandler<Account>() {
                        @Override
                        public Account handle(ResultSet rs) throws SQLException {
                            Account account = new Account();
                            if(rs.next()) {
                                account.setId(rs.getInt("id"));
                                account.setName(rs.getString("name"));
                                account.setNum(rs.getString("num"));
                                account.setPwd(rs.getString("pwd"));
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
