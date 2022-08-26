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
import java.sql.SQLException;

public class update2 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String loc = request.getParameter("loc");

        Connection conn = null;
        PreparedStatement ps = null;


        try {
            conn = jdbc.conn();
            String sql = "update t_user set name = ? , loc = ? where no = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,loc);
            ps.setString(3,no);

            if(ps.executeUpdate()==1){
                // request.getRequestDispatcher("/dept/list").forward(request,response);
                response.sendRedirect(request.getContextPath()+"/dept/list");
            }else {
                // request.getRequestDispatcher("/error.html").forward(request,response);
                response.sendRedirect(request.getContextPath()+"/error.html");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            jdbc.close3(conn,ps,null);
        }


    }
}
