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
// 根路径下的dept,所以welcom那里直接写dept/welcome就可以了,前面不用加斜杠
@WebServlet("/dept/welcome") // 这样写是说明了这个servlet的访问路径是在根路径下的
public class welcome extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 在if外面创建,因为要用
        String username = null;
        String password = null;
        // 重来没有使用过浏览器,第一次请求的时候是没有cookie的,就会为null
        // 这里判断是否有正确的cookie,如果有的话就直接跳转到list页面,没有的话就直接跳转到登陆页面,因为cookie只有在登陆成功的时候才会有
        // 之前在登陆servlet里面写了,如果成功的话且点了10天免登陆的话,就会将账号和密码,存到为两个cookie,名字为username和password
        Cookie[] cookies = request.getCookies();
        // 遍历这个cookie数组,如果有正确cookie,那么就直接跳转登陆页面,如果有cookie,那么就会返回数组长度大于0的数组,如果没有的话就会返回null,不会返回一个长度为0的数组
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // 如果cookie的名字是username的话,那么获取的就是用户的名字
                if ("username".equals(cookie.getName())) {
                    username = cookie.getValue();
                }// 这里获取的是名字是password的密码
                else if ("password".equals(cookie.getName())) {
                    password = cookie.getValue();
                }
            }
        }
        // 如果获取的两个cookie都有,那么就直接跳转到列表页面,要么都为空,要么都为不空
        if (username != null && password != null) {
            Boolean success = false;
            // 连接数据库,验证是否正确,如果正确的话,就直接访问列表,不正确,跳转登陆页面
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                conn = DButil.conn();
                String sql = "select * from user where username = ? and password = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1,username);
                ps.setString(2,password);
                rs = ps.executeQuery();
                if(rs.next()){
                    // 有返回数据,说明是正确的,这里加一个session,就不用走登陆页面,因为原来是走登陆页面登陆成功才会有session的,现在就可以直接用session访问list了,因为访问list也是要session的
                    HttpSession session = request.getSession();
                    session.setAttribute("username",username);// 访问list的条件是session不为空,且session里面有username这个数据存进去了
                    request.getRequestDispatcher("/dept/list").forward(request,response);
                } else {
                    // 跳转登陆页面
                    response.sendRedirect("/oa/index.jsp");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                DButil.close3(conn,ps,rs);
            }
        }else{
            // 到登陆页面
            response.sendRedirect("/oa/index.jsp");
        }

    }
}
