<%@ page import="java.util.List" %>
<%@ page import="bean.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@page contentType="text/html; charset=UTF-8"%>
<%--引入标签库,这里引入的是jstl的核心标签库--%>
<%--用法是在jsp中代替java代码--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


负责展示的是HTML语言
HTML中有大量的标签
<%
    // 创建List集合
    List<Student> list = new ArrayList<>();

    // 创建Student对象
    Student s1 = new Student(110,"警察");
    Student s2 = new Student(120,"救护车");
    Student s3 = new Student(119,"消防车");
    list.add(s1);
    list.add(s2);
    list.add(s3);
    // 将list集合存储到request域中
    request.setAttribute("student",list);
%>
<%--需求:将List集合中的元素遍历,输出学生信息到浏览器--%>
<%--使用java代码--%>
<%
    // 从域中获取
    List<Student> stus = (List<Student>) request.getAttribute("student");
    // 编写for循环遍历list集合
    for (Student stu : stus) {
%>
     id:<%=stu.getNo()%>,name:<%=stu.getName()%>
<%
    }
%>
<br>

<hr>

<%--使用core标签库中的forEach标签,对List集合进行遍历--%>
<%--EL表达式只能从域中取数据--%>
<%--var后面的名字的属性是随意的,var属性代表的是集合中的每一个元素--%>
<c:forEach items="${student}" var="s">
    id:${s.no},name:${s.name} <br>
</c:forEach>




















