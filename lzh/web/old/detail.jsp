<%@ page import="com.lzh.bean.Dept" %><%--
  Created by IntelliJ IDEA.
  User: haha
  Date: 2022/8/24
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>详情页</title>
</head>
<body>
<h1>详情页</h1>
<hr>
<%
  Dept dept = (Dept) request.getAttribute("dept");
%>
部门编号:<%=dept.getNo()%> <br>
部门名称:<%=dept.getName()%> <br>
地区:<%=dept.getLoc()%> <br>
<a href="/oa/dept/list">返回</a>
</body>
</html>