<%@ page import="com.lzh.bean.Dept" %>
<%@ page import="java.util.List" %><%--
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
  <script type="text/javascript">
    function del(no){
      if(window.confirm("删除了就不能恢复了")){
        document.location.href = "/oa/dept/del?no=" + no;
      }
    }
  </script>
</head>
<body>
<h1>列表页</h1>
<hr>

<h2>欢迎<%=session.getAttribute("username")%></h2>
[<a href="/oa/dept/out">安全退出</a>]
<table border="1px">
  <tr>
    <th>编号</th>
    <th>部门</th>
    <th>地区</th>
    <th>操作</th>
  </tr>

  <%
    int i = 0;
    List<Dept> lists = (List<Dept>)request.getAttribute("list");
    for (Dept dept : lists) {
  %>
  <tr>
    <td><%=(++i)%></td>
    <td><%=dept.getNo()%></td>
    <td><%=dept.getName()%></td>
    <td><%=dept.getLoc()%></td>
    <td>
      <a href="javascript:void(0)" onclick="del(<%=dept.getNo()%>)">删除</a>
      <a href="/oa/dept/update?no=<%=dept.getNo()%>">修改</a>
      <a href="/oa/dept/detail?no=<%=dept.getNo()%>">详情</a>
    </td>
  </tr>
<%
  }
%>
</table>
<a href="/oa/add.jsp">新增数据</a>
</body>
</html>
