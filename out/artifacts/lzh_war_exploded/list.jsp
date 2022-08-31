<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: haha
  Date: 2022/8/24
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>列表页面</title>
  <base href="http://localhost:8080/oa/">
  <script type="text/javascript">
    function del(no){
      if(window.confirm("删除了就不能恢复了")){
        /*html的base标签可能对js代码不起作用，最好用EL表达式*/
        document.location.href = "${pageContext.request.contextPath}/dept/del?no=" + no;
      }
    }
  </script>
</head>
<body>
<h1>列表页</h1>
<hr>
<h2>欢迎${username}</h2>
[<a href="${pageContext.request.contextPath}/dept/out">安全退出</a>]

<table border="1px">
  <tr>
    <th>编号</th>
    <th>部门</th>
    <th>地区</th>
    <th>操作</th>
  </tr>
  <c:forEach items="${list}" var="dept" varStatus="deptStatus">
    <tr>
      <td>${deptStatus.count}</td>
      <td>${dept.no}</td>
      <td>${dept.name}</td>
      <td>${dept.loc}</td>
      <td>
        <a href="javascript:void(0)" onclick="del(${dept.no})">删除</a>
        <a href="${pageContext.request.contextPath}/dept/update?no=${dept.no}">修改</a>
        <a href="${pageContext.request.contextPath}/dept/detail?no=${dept.no}">详情</a>
      </td>
    </tr>
  </c:forEach>
</table>
<a href="add.jsp">新增数据</a>
</body>
</html>
