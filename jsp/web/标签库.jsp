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
    Student s3 = new Student(110,"消防车");
    // 将list集合存储到request域中
    request.setAttribute("student",list);
%>
<%--需求:将List集合中的元素遍历,输出学生信息到浏览器--%>
<%--使用java代码--%>
<%
    // 从域中获取
    List<Student> stus = (List<Student>) request.getAttribute("list");
    // 编写for循环遍历list集合
    for (Student stu : stus) {

%>
     id:<%stu.getNo()%>


<%
    }
%>





















