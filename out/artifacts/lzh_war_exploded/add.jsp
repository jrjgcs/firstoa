<%--
  Created by IntelliJ IDEA.
  User: haha
  Date: 2022/8/24
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>新增数据</title>
</head>
<body>
<form action="/oa/dept/add" method="post">
    <h1>新增数据</h1>
    <hr>
    部门编号:<input type="text" name="no"/> <br>
    部门名称:<input type="text" name="name" /> <br>
    部门地区:<input type="text" name="loc" /> <br>
    <input type="submit" value="保存">
</form>
</body>
</html>
