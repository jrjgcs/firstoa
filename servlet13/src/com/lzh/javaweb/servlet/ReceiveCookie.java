package com.lzh.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ReceiveCookie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 通过java程序怎么接收浏览器发送过来的cookie呢?
        // 当然通过request对象了
        // 返回一个cookies数组,因为浏览器会发送多个cookie
        // 注意:这个方法可能会返回null,如果浏览器没有提交cookie,会返回null,不会返回长度为0的数组
        Cookie[] cookies = request.getCookies();

        // 如果不是null,表示一定有cookie
        if (cookies != null) {
            // 遍历数组
            for (Cookie cookie : cookies){
                // 获取cookiename和value
                String name = cookie.getName();
                String value = cookie.getValue();
                System.out.println(name + value);
            }
        }

    }
}
