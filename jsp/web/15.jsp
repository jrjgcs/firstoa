<%@page contentType="text/html; charset=UTF-8" %>
<%--JSP中的隐含对象
  1.pageContext
  2.param
  3.paramValues
  4.initParam
  5.其他

--%>

${pageContext.request.contextPath} <br>

<%--request是JSP九大内置对象--%>
<%--request.getParameter("username") 获取请求--%>
用户名:<%=request.getParameter("username")%> <br>

用户名:${param.username} <br>

<%--假设用户提交的数据: http://localhost:8080/jsp/15.jsp?aihao=smoke&aihao=drink--%>
爱好:${param.aihao}<%--取的是smoke--%>
<%--如果有同名的,只取数组中的第一个元素--%>
<hr>
爱好:${paramValues.aihao}<%--这里取的是数组的第一个元素--%>
<br>
爱好:<%=request.getParameterValues("aihao")%><%--这里取的是一个数组--%>
<hr>

<%--获取数组中的元素:[下标]--%>
爱好:${paramValues.aihao[0]}、${paramValues.aihao[1]} <br>
<%--爱好:<%=request.getParameterValues("aihao")[0]%>、<%=request.getParameterValues("aihao")[1]%>--%>


<%--EL表达式中的隐含对象:initParam--%>
<%--ServletContext是Servlet上下文对象,对应jsp九大内置对象之一是:application--%>

<%
    /*在xml文件里面存的*/
    String pageSize = application.getInitParameter("pageSize");
    String pageNum = application.getInitParameter("pageNum");

%>
使用java代码
每页显示的记录条数:<%=pageSize%>
页码:<%=pageNum%>
<br>
不使用java代码
每页显示的记录条数:<%=application.getInitParameter("pageSize")%>
页码:<%=application.getInitParameter("pageNum")%>
<br>
使用EL表达式

每页显示的记录条数:${initParam.pageSize}
页码:${initParam.pageNum}
<br>

<hr>
<%

%>

















