<%--
  Created by IntelliJ IDEA.
  User: haha
  Date: 2022/8/24
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>修改页</title>
</head>


<body>
<form action="/oa/dept/modify" method="post">
  <h1>修改页</h1>
  <hr>
  部门编号:<input type="text" value="${dept.no}" name="no" readonly /> <br>
  部门名称:<input type="text" value="${dept.name}" name="name"/> <br>
  部门地区:<input type="text" value="${dept.name}" name="loc"/> <br>
  <input type="submit" value="修改">
</form>
</body>
</html>