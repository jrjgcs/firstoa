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
<%--这个username是从session域中取的，因为他会从所有的域里面去找的，只有session域里面有--%>
<h3>欢迎${username}</h3>
<h1>详情页</h1>
<hr>
部门编号:${dept.no}<br>
部门名称:${dept.name}<br>
地区:${dept.loc}<br>
<input type="button" value="后退" onclick="window.history.back()">
</body>
</html>