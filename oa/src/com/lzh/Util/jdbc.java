package com.lzh.Util;

import java.sql.*;
import java.util.ResourceBundle;

public class jdbc {

    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("jdbc");
        String dirver = resourceBundle.getString("driver");
        try {
            Class.forName(dirver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection conn() throws SQLException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("jdbc");
        String dirver = resourceBundle.getString("driver");
        String user = resourceBundle.getString("user");
        String pwd = resourceBundle.getString("pwd");
        String url = resourceBundle.getString("url");
        Connection connection = DriverManager.getConnection(url,user,pwd);
        return connection;
    }
    public static void close3(Connection conn , Statement ps ,ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
