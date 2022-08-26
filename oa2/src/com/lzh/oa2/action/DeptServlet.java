package com.lzh.oa2.action;

import com.lzh.oa2.util.jdbc;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 模糊匹配
// 只要请求路径都是以"/dept"开始的,都走这个servlet
// 模板类
@WebServlet({"/dept/list","/dept/detail","/dept/add","/dept/del","/dept/update","/dept/update2"})
//@WebServlet("/dept/*")
public class DeptServlet extends HttpServlet {
    // 模板方法
    // 重写service方法(并没有重写doGet或者doPost)

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取servlet path 获取请求域中的类路径
        String servletPath = request.getServletPath();
        if (servletPath.equals("/dept/list")) {
            doList(request, response);
        } else if (servletPath.equals("/dept/add")) {
            doAdd(request, response);
        } else if (servletPath.equals("/dept/del")) {
            doDel(request, response);
        } else if (servletPath.equals("/dept/update")) {
            doUpdate(request, response);
        } else if (servletPath.equals("/dept/detail")) {
            doDetail(request, response);
        } else if (servletPath.equals("/dept/update2")) {
            doUpdate2(request, response);
        }
    }



    private void doList(HttpServletRequest request, HttpServletResponse response)
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

    private void doAdd(HttpServletRequest request, HttpServletResponse response)
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
            ps.setString(1, no);
            ps.setString(2, name);
            ps.setString(3, loc);

            int count = ps.executeUpdate();

            if (count == 1) {
                // 这这servlet里面写的是post方法,所以这个转发用的也是post,以为是一次请求,但是呢访问的list里面没有post方法,怎么办呢?
                // 在servlet中创建post方法,然后调用get方法就可以了
                //request.getRequestDispatcher("/dept/list").forward(request,response);

                // 这里最好使用重定向(浏览器会重新发送一个请求,是get请求的话,就不用再list中重写都doPost方法了)
                // 因为如果用转发的话,刷新的话就会重新请求,就会重复插入数据
                // 因为是在浏览器地址栏上输入,所以会要加项目名
                response.sendRedirect(request.getContextPath() + "/dept/list");
            } else {
                // 这里建议使用重定向
                response.sendRedirect(request.getContextPath() + "/error.html");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void doUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // 获取部门编号
        String no =request.getParameter("no");
        String name =request.getParameter("name");
        String loc =request.getParameter("loc");


        out.print("             <!DOCTYPE html>");
        out.print("     <html>");
        out.print("     <head>");
        out.print("       <meta charset='utf-8'>");
        out.print("       <title>修改数据</title>");
        out.print("     </head>");
        out.print("     <body>");
        out.print("     <h1>修改数据</h1>");
        out.print("     <hr>");
        out.print("     <form action='/oa/dept/update2' method='post'>");
        out.print("     ");
        out.print("                     部门编号 <input type='text' value='"+no+"' name='no' readonly/> <br>");
        out.print("                     部门名称 <input type='text' value='"+name+"' name='name' /> <br>");
        out.print("                     部门位置 <input type='text' value='"+loc+"' name='loc' /> <br>");
        out.print("     ");
        out.print("       <input type='submit' name='修改按钮' value='修改'>");
        out.print("     </form>");
        out.print("     </body>");
        out.print("     </html>");
    }

    private void doDetail(HttpServletRequest request, HttpServletResponse response)
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

    private void doUpdate2(HttpServletRequest request, HttpServletResponse response)
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

    private void doDel(HttpServletRequest request, HttpServletResponse response)
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
        if (count == 1) {
            // 删除成功
            // 仍然跳转到列表页面
            // 部门列表页面的显示执行另一个Servelet,怎么办?需要转发
            // request.getRequestDispatcher("/dept/list").forward(request,response);
            // 相当于是跳转到这个链接,是在浏览器上输入的,所以要加项目名
            response.sendRedirect(request.getContextPath() + "/dept/list");
        } else {
            // 删除失败
            // request.getRequestDispatcher("/error.html").forward(request,response);
            response.sendRedirect(request.getContextPath() + "/error.html");
        }
    }

}
