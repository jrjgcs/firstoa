package com.lzh.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/test/session")
public class TestSessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // reques和session都是服务器端的java对象,都在JVM当中
        // request对象代表了一次请求对象(一次请求对象对应一个request对象,两次请求就会对应两个不同的request对象)
        // session对象代表了一次会话(一次会话对应一个session对象)
        // 获取session对象
        // WEB服务器 这个时候如果有session对象就会获取,没有的话就会创建
        HttpSession session = request.getSession();

        // 向会话中存数据
        //session.setAttribute();
        // 向会话中取数据
        //session.getAttribute();

        // 将session对象响应到浏览器
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("会话对象:" + session);// org.apache.catalina.session.StandardSessionFacade@6255e5f6
    }
}
