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

public class del extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 根据部门编号,删除部门,就是读取提交信息
        String no = request.getParameter("delete");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = jdbc.conn();
            // 开启事务:修改表的时候,一般要开启事务,确保数据没有问题之后才会删除
            conn.setAutoCommit(false);

            String sql = "delete from t_user where no = ?";
            ps = conn.prepareStatement(sql);

            ps.setString(1, no);
            // 影响了多少条记录的时候,就是成功了几条的意思
            int i = ps.executeUpdate();
            count = i;

            // 事务提交
            conn.commit();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            // 遇到异常的话就回滚
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        } finally {
            jdbc.close3(conn, ps, null);
        }

        // 判断删除成功了还是失败了
        if(count == 1){
            // 删除成功
            // 仍然跳转到列表页面
            // 部门列表页面的显示执行另一个Servelet,怎么办?需要转发
            // request.getRequestDispatcher("/dept/list").forward(request,response);
            response.sendRedirect(request.getContextPath()+"/dept/list");
        }else {
            // 删除失败
            // request.getRequestDispatcher("/error.html").forward(request,response);
            response.sendRedirect(request.getContextPath()+"/error.html");
        }
    }
}
