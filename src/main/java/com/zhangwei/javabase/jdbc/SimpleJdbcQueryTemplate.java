package com.zhangwei.javabase.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SimpleJdbcQueryTemplate{
    public <T> T query(String sql, ResultSetHandler<T> resultSetHandler) throws SQLException, ClassNotFoundException {
        Connection conn = DBUtils.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        T data = resultSetHandler.handle(rs);
        DBUtils.closeResource(conn,stmt,rs);
        return data;
    }
}
