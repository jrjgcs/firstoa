<%--
  Created by IntelliJ IDEA.
  User: haha
  Date: 2022/8/24
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>登陆页</title>
</head>
<body>
<h1>登陆</h1>
<hr>

<form action="/oa/dept/login">
  账号: <input type="text" name="user"> <br>
  密码: <input type="password" name="password"> <br>
  <input type="checkbox" value="1" name="f"> 十天免登录 <br>
  <input type="submit" value="登陆">
</form>


</body>
</html>