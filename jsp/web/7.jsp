<%@ page import="bean.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    // 创建对象
    User user = new User();
    user.setName("jackson");
    user.setPassword("123456");
    // 将这个对象放到请求域当中
    request.setAttribute("userObj",user);
%>
<%--使用EL表达式,从request域当中,取出User对象,并将其输出到浏览器--%>
<%--1.EL表达式会自动从某个范围中取数据 2.将其转成字符串  3.将其输出到浏览器--%>
${userObj}
<%--向输出这个类里面的属性--%>
${userObj.name}
<%--输出password属性--%>
${userObj.password}