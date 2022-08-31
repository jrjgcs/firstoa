<%@ page import="java.util.List" %>
<%@ page import="bean.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<%--var用来指定循环中的变量--%>
<%--begin开始--%>
<%--end结束--%>
<%--step步长--%>
<%--底层实际上将i存储到某个域当中的,一般会到小的域当中,pageContext中--%>
<c:forEach var="i" begin="1" end="10" step="1">
    ${i} <br>
</c:forEach>

<%
    List<Student> list = new ArrayList<>();
    Student s1 = new Student(110,"警车");
    Student s2 = new Student(120,"救护车");
    Student s3 = new Student(119,"消防车");

    list.add(s1);
    list.add(s2);
    list.add(s3);
    request.setAttribute("list",list);
%>

<%--var="s"这个s代表的是集合中的每个Student对象--%>
<%--varStatus="这个属性表示var的状态对象,这里是一个java对象,这个java对象代表了var的状态"--%>
<%--varStatus="名字是随意的"--%>
<%--varStatus这个状态对象有count属性,可以直接使用--%>
<c:forEach items="${list}" var="s" varStatus="stuStatus">
    <%--varStatus的count值从1开始,以1递增,主要用于编号,序号--%>
    编号:${stuStatus.count},id:${s.no},name:${s.name}
</c:forEach>




























