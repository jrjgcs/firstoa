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


public class test extends HttpServlet {


/*
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }
*/

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // 这里是动态的获取项目名，也就是路径
        String path = request.getContextPath();


        out.print("			<!DOCTYPE html>");
        out.print("	<html>");
        out.print("		<head>");
        out.print("			<meta charset='utf-8'>");
        out.print("			<title>数据列表</title>");


      out.print("  <script type='text/javascript'>");
      out.print("          function del(no){");
      out.print("              if(window.confirm('删了不可恢复')){");
      out.print("                  document.location.href = '"+path+"/dept/del?delete=' + no;");
      out.print("              }");
      out.print("          }");
      out.print("  </script>");



        out.print("		</head>");
        out.print("		<body>");
        out.print("			<h1 align='center'>数据列表</h1>");
        out.print("			<hr>");
        out.print("			<table border='1px' align='center'>");
        out.print("				<tr>");
        out.print("					<th>序号</th>");
        out.print("					<th>部门编号</th>");
        out.print("					<th>部门</th>");
        out.print("					<th>地区</th>");
        out.print("					<th>操作</th>");
        out.print("				</tr>");

        try {
            conn = jdbc.conn();
            String sql = "select * from t_user";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            int i = 0;

            while (rs.next()) {

                String no = rs.getString("no");
                String name = rs.getString("name");
                String loc = rs.getString("loc");

                out.print("				<tr>");
                out.print("					<td>"+(++i)+"</td>");
                out.print("					<td>"+no+"</td>");
                out.print("					<td>"+name+"</td>");
                out.print("					<td>"+loc+"</td>");
                out.print("					<td>");
                out.print("						<a href='javascript:void(0)' onclick='del("+no+")'>删除</a>");
                out.print("						<a href='/oa/dept/update?no="+no+"&name="+name+"&loc="+loc+"'>修改</a>");
                out.print("						<a href='"+path+"/dept/detail?no="+no+"'>详情</a>");
                out.print("					</td>");
                out.print("				</tr>");
            }
            out.print("			</table>");
            out.print("			<a href='/oa/add.html'>新增数据</a>");
            out.print("		</body>");
            out.print("	</html>");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            jdbc.close3(conn, ps, rs);
        }
    }
}
