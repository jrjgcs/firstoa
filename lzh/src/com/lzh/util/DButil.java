package com.lzh.util;

import java.sql.*;
import java.util.ResourceBundle;

public class DButil {
    // 静态代码块,调用静态方法的时候会执行,只会执行一次
    static {
        ResourceBundle rs = ResourceBundle.getBundle("jdbc");
        String driver = rs.getString("driver");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    // 连接的方法
    public static Connection conn() throws SQLException {
       ResourceBundle rs = ResourceBundle.getBundle("jdbc");
        String url = rs.getString("url");
        String user = rs.getString("user");
        String pwd = rs.getString("pwd");
        Connection conn = DriverManager.getConnection(url,user,pwd);
        return conn;
    }
    // 关闭流的方法
    public static void close3(Connection conn, PreparedStatement ps, ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(ps != null){
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
}
