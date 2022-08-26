
<%@ page contentType="text/html;charset=UTF-8"%>

<%
  System.out.println("hello world");
%>

<%--
<%!
  System.out.println("hello servlet");
%>
--%>

<%!
  // 成员变量
  private String name = "jackson";
  // 静态代码块
  static {
    System.out.println("静态代码块执行了");
  }
  // 静态方法
  public static void m1(){
    System.out.println("m1 method");
  }

%>