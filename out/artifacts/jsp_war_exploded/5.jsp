<%--
<%@page session="false"%>
--%>
<%--
<%@page contentType="text/json" pageEncoding="UTF-8"%>
<%@page contentType="text/html;charset=UTF-8"%>
--%>
<%--
<%@page import="java.util.List , java.applet.Applet" %>
--%>

<%@page errorPage="/error.jsp"  %>
<%
    /*出现异常会跳转到error.jsp,然后在那里打印输出异常信息*/
    String name = null;
    // 空指针异常
    name.toString();
%>