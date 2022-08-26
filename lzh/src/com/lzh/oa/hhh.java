package com.lzh.oa;

import com.lzh.bean.Dept;
import com.lzh.util.DButil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/dept/list", "/dept/del", "/dept/detail", "/dept/update", "/dept/modify", "/dept/add"})
public class hhh extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 首先获取子路径,不是根路径
        String path = request.getServletPath();
        // 为了是登陆的用户才可以访问,所以我们加一个session判断
        HttpSession session = request.getSession(false);// 本来是没有的话,会创建的,但是我们设定只有登陆成功的人,才有session
        if(session != null && session.getAttribute("username") != null) {
            // 判断访问路径
            if ("/dept/list".equals(path)) {
                doList(request, response);
            } else if ("/dept/del".equals(path)) {
                doDel(request, response);
            } else if ("/dept/detail".equals(path)) {
                doDetail(request, response);
            } else if ("/dept/update".equals(path)) {
                doUpdate(request, response);
            } else if ("/dept/modify".equals(path)) {
                doModify(request, response);
            } else if ("/dept/add".equals(path)) {
                doAdd(request, response);
            }

        } else {
            // 如果没有session,那么就跳转到登陆页面
            response.sendRedirect("/oa/index.jsp");
        }
    }

    // 添加数据
    private void doAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String loc = request.getParameter("loc");
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DButil.conn();
            String sql = "insert into t_user (no,name,loc) values (?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, no);
            ps.setString(2, name);
            ps.setString(3, loc);
            int i = ps.executeUpdate();
            if (i == 1) {
                // 数据修改成功,数据已经修改好了,不用转发,只用重定向就可以了
                response.sendRedirect("/oa/dept/list");
            } else {
                // error
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.close3(conn, ps, null);
        }

    }

    // 修改数据
    private void doModify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String loc = request.getParameter("loc");
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DButil.conn();
            String sql = "update t_user set name = ?, loc = ? where no = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, loc);
            ps.setString(3, no);
            int i = ps.executeUpdate();
            if (i == 1) {
                // 数据修改成功,数据已经修改好了,不用转发,只用重定向就可以了
                response.sendRedirect("/oa/dept/list");
            } else {
                // error
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.close3(conn, ps, null);
        }


    }

    // 获取数据,和查看详情差不多的
    private void doUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String no = request.getParameter("no");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DButil.conn();
            String sql = "select * from t_user where no = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, no);

            rs = ps.executeQuery();
            rs.next();
            String name = rs.getString("name");
            String loc = rs.getString("loc");

            Dept dept = new Dept(no, name, loc);
            request.setAttribute("dept", dept);

            request.getRequestDispatcher("/update.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.close3(conn, ps, rs);
        }
    }

    // 查看信息信息
    private void doDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String no = request.getParameter("no");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DButil.conn();
            String sql = "select * from t_user where no = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, no);

            rs = ps.executeQuery();
            rs.next();
            String name = rs.getString("name");
            String loc = rs.getString("loc");

            Dept dept = new Dept(no, name, loc);
            request.setAttribute("dept", dept);

            request.getRequestDispatcher("/detail.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.close3(conn, ps, rs);
        }
    }

    // 删除数据
    private void doDel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement ps = null;
        String no = request.getParameter("no");
        try {
            conn = DButil.conn();
            String sql = "delete from t_user where no = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, no);
            if (ps.executeUpdate() == 1) {
                // 如果跳转成功,就到list的Servlet界面,重新获取下数据库的数据,删除不用共享数据,用重定向,因为列表页面是要连接数据库的,不能直接访问list.jsp
                response.sendRedirect("/oa/dept/list");
            } else {
                // error
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.close3(conn, ps, null);
        }
    }


    // 获取数据的
    private void doList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DButil.conn();
            String sql = "select * from t_user ";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            // 创建一个集合,用来存放这些封装类
            List<Dept> lists = new ArrayList<>();

            // 返回一个查询结果集
            while (rs.next()) {
                // 将查询结果集放到一个封装类里面,因为获取的数据很多很杂
                String no = rs.getString("no");
                String name = rs.getString("name");
                String loc = rs.getString("loc");
                Dept dept = new Dept(no, name, loc);
                lists.add(dept);
            }
            // 将这个存放了封装类的集合存放到请求域里面
            request.setAttribute("list", lists);

            // 这里共享了数据所以就用转发,根目录下,要加斜杠
            request.getRequestDispatcher("/list.jsp").forward(request, response);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DButil.close3(conn, ps, rs);
        }
    }
}















































