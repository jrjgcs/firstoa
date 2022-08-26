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

public class add extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        Connection conn = null;
        PreparedStatement ps = null;

        String no = request.getParameter("部门编号");
        String name = request.getParameter("部门名称");
        String loc = request.getParameter("部门位置");


        try {
            conn = jdbc.conn();
            String sql = "insert into t_user values(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,no);
            ps.setString(2,name);
            ps.setString(3,loc);

            int count = ps.executeUpdate();

            if(count==1){
                // 这这servlet里面写的是post方法,所以这个转发用的也是post,以为是一次请求,但是呢访问的list里面没有post方法,怎么办呢?
                // 在servlet中创建post方法,然后调用get方法就可以了
                //request.getRequestDispatcher("/dept/list").forward(request,response);

                // 这里最好使用重定向(浏览器会重新发送一个请求,是get请求的话,就不用再list中重写都doPost方法了)
                // 因为如果用转发的话,刷新的话就会重新请求,就会重复插入数据
                // 因为是在浏览器地址栏上输入,所以会要加项目名
                response.sendRedirect(request.getContextPath()+"/dept/list");
            }else {
                // 这里建议使用重定向
                response.sendRedirect(request.getContextPath()+"/error.html");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
