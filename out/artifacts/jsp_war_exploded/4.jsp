
<%@ page contentType="text/html;charset=UTF-8"%>

<%
    String name = "jack";
    // 输出到控制台的
    System.out.println("name=" + name);
    // service特有的方法 输出到网页的
    out.write("name2 = " + name);

%>

<%--
<%!
    out.write();
%>
--%>


<%= 100 + 200 %> <%--out.print(100+200);--%>

<%= "zhangsan" %> <%--out.print("zhangsan");--%>

<br>
<% String username = "jack";%>

<%="欢迎"+username%>








































