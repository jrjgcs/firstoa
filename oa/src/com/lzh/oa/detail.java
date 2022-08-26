package com.lzh.oa;

import com.lzh.Util.jdbc;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class detail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // 获取部门编号
        /*
        虽然提交的是 数字 30 但是服务器获取的是字符串 30
         */
        String no = request.getParameter("no");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

 out.print("  <!DOCTYPE html>");
 out.print("  <html>");
 out.print("      <head>");
 out.print("          <meta charset='utf-8'>");
 out.print("          <title>详情</title>");
 out.print("      </head>");
 out.print("      <body>");
 out.print("          <h1>详情</h1>");
 out.print("          <hr>");





        try {
            conn = jdbc.conn();
            String sql = "select name,loc from t_user where no = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,no);
            rs = ps.executeQuery();

            if(rs.next()) {
                String name = rs.getString("name");
                String loc = rs.getString("loc");
                out.print("                  部门编号：" + no + "  <br>");
                out.print("                  部门名称：" + name + " <br>");
                out.print("          部门位置：" + loc + " <br>");
            }

            out.print("          ");
            out.print("          <button onclick='window.history.back()'>返回</button>");
            out.print("      </body>");
            out.print("  </html>");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            jdbc.close3(conn,ps,rs);
        }

    }
}


































