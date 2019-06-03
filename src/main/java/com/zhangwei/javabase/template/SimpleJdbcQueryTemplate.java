package com.zhangwei.javabase.template;

import com.zhangwei.javabase.jdbc.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SimpleJdbcQueryTemplate{
    public <T> T query(String sql, ResultSetHandler<T> resultSetHandler) throws SQLException, ClassNotFoundException {
        Connection conn = DBUtils.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return resultSetHandler.handle(rs);
    }
}
