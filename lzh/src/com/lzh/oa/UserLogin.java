package com.lzh.oa;

import com.lzh.util.DButil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet({"/dept/login", "/dept/out"})
public class UserLogin extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if ("/dept/login".equals(servletPath)) {

        /*
        这里是要连接数据库,验证是否有这个账号,有的话,就登陆,没有的话就去error,然后可以选择重新登陆
         */
            String user = request.getParameter("user");
            String password = request.getParameter("password");
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                conn = DButil.conn();
                String sql = "select * from user where username = ? and password = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, user);
                ps.setString(2, password);
                rs = ps.executeQuery();
                // 因为只有一条数据,只要查的到就算是成功了
                if (rs.next()) {
                    // 如果登陆成功了,我们检查是否有10天免登陆,如果有,就创建一个cookie,没有的话就直接登陆
                    if ("1".equals(request.getParameter("f"))) {
                        // 创建cookie,然后将账号,密码放进去
                        Cookie cookie = new Cookie("username", user);
                        Cookie cookie1 = new Cookie("password", password);
                        // 设置有效时间 时间等于0是删除同名cookie,就是同name的cookie,时间为负就是在运行内存中存,不存本地
                        cookie.setMaxAge(60 * 60 * 24 * 10);
                        cookie1.setMaxAge(60 * 60 * 24 * 10);
                        // 设置关联路径,只要访问这个关联路径和他的子路径,浏览器会自动带上这两个cookie
                        cookie.setPath(request.getContextPath());
                        cookie1.setPath(request.getContextPath());

                        // 然后将这两个cookie响应给浏览器,这个时候,如果浏览器来访问这路径及子路径的话,那么就会携带这两个cookie
                        response.addCookie(cookie);
                        response.addCookie(cookie1);

                    }
                    // 如果没有勾选那个10天免登陆,那么就不会有f=1,就不会执行上面的创建cookie
                    // 为了是只有登陆成功的用户可以访问数据,所以我们添加了session
                    HttpSession session = request.getSession();
                    session.setAttribute("username", user);

                    // 登陆就成功了,要传session数据,所以要转发,转发是不用写项目名的
                    request.getRequestDispatcher("/dept/list").forward(request, response);


                } else {
                    // 登陆失败,重定向到error
                    response.sendRedirect("/oa/error.html");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                DButil.close3(conn, ps, rs);
            }
        } else {
            // 这里是要安全退出的
            HttpSession session = request.getSession(false);
            session.invalidate();
            response.sendRedirect("/oa");
        }
    }
}










































