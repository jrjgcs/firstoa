<%@page contentType="text/html; charset=UTF-8" %>

<%
  request.setAttribute("username","zhangsan");
%>

<%--取出数据并且输出到浏览器--%>
<%=request.getAttribute("username")%>
采用EL表达式:${username}<%--null--%>
<br>

<%=request.getAttribute("usernam")%>
<%--EL表达式主要任务是页面展示,要求最终页面展示上是友好的--%>
采用El表达式:${usernam}<%--空白,不会报错,做一个空处理--%>

<hr>
<%--表面上是这种写法,实际上还是要编译生成java代码--%>
这个EL表达式等同于这一段java代码:<%=request.getAttribute("usernam") == null ? "":request.getAttribute("usernam")%>