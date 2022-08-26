<%@page contentType="text/html; UTF-8" %>


System.out.println("hello jsp");

<%
  System.out.println("hello jsp");
%>

<%--
  这个是jsp的专业注释
  不会被编译到java源代码中
--%>


<%--
  private int i ;
  会报错:在service方法中,不能写成员变量
--%>