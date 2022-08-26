package com.lzh.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cookie/generate")
public class GenerateCookie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 创建cookie对象
        Cookie cookie = new Cookie("productid", "1654846");
        Cookie cookie1 = new Cookie("username","zhangsan");




        // 设置cookie在一个小时之后失效(保存在硬盘文件中)
        //cookie.setMaxAge(60 * 60);

        // 设置cookie的有效期为0,表示该cookie被删除,主要应用:使用这种方式删除浏览器中的同名cookie
        //cookie.setMaxAge(0);
        // 设置cookie的有效期 < 0 ,表示该cookie不会被存储(表示不会被存储到硬盘中,会放到浏览器的运行内存中)

        cookie.setMaxAge(-1);
        cookie1.setMaxAge(-1);

        // 默认情况下,没有设置path的时候,cookie关联的路径是什么?项目路径的下一个路径及子路径
        cookie.setPath("/servlet13");
        cookie1.setPath("/servlet13");



        // 将cookie响应到浏览器  浏览器的缓存中就会有这两个cookie
        response.addCookie(cookie);
        response.addCookie(cookie1);

    }
}


























