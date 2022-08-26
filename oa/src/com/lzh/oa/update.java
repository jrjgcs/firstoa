package com.lzh.oa;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class update extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
}
