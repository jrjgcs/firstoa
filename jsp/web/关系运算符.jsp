<%@ page import="bean.Student" %>
<%@page contentType="text/html; charset=UTF-8" %>
<%--
    关于EL表达式中的运算符
    加号不会做字符串的拼接
        1.算术运算符
            + - * / %
        2.关系运算符
            == ! > >= < <= eq
            双等号和eq都会调用equals方法
        3.逻辑运算符
            ! && || not and or (注意:! 和 not 都是取反)
        4.条件运算符
            ? :
        5.取值运算符
            [] 和 .
        6.empty 运算符
--%>

${10 + 20} <br> <%--30--%>
<%--在EL表达式中,"+" 运算符只能做求和运算,不会进行字符串的拼接操作--%>
${10 + "20"} <br>

<hr>
java.lang.NumberFormatException: For input string: "abc"
如果搞不成数字,就会报异常,是数字格式化异常
\${10 + "abc"}
<hr>



<%-- 关系运算符 --%>
${"abc" == "abc"}
<br>
这样子就是普通的字符串
${"abc"} == ${"abc"}


<hr>
<%
    Object obj = new Object();
    request.setAttribute("obj1",obj);
    request.setAttribute("obj2",obj);
%>
${obj1 == obj2} <%--true--%>

<br>

<%
    // 字符串常量词
    String s1 = new String("hehe");
    String s2 = new String("hehe");
    request.setAttribute("a",s1);
    request.setAttribute("b",s2);
%>
虽然内存地址不同,但是指向同一个地方,相同时因为调用了equals方法
${a == b} <%--true,会自动调用equals方法--%>

<br>
这两个就是不同的对象
<%
    Object obj1 = new Object();
    Object obj2 = new Object();
    request.setAttribute("obj1",obj1);
    request.setAttribute("obj2",obj2);
%>
${obj1 == obj2} <%--false--%> <br>


<br>
使用了equals方法
<%
    Student stu1 = new Student(10,"警察");
    Student stu2 = new Student(10,"警察");
    request.setAttribute("stu1",stu1);
    request.setAttribute("stu2",stu2);
%>
${stu1==stu2}

${stu1 eq stu2}
<hr>
${stu1 != stu2} <%--false,这里也会调用equals方法--%>

<%--以下语法有优先级,以下语法错误,没有加小括号--%>
\${!stu1 == stu2} <br>
<%--以下正确--%>
${!(stu1 == stu2)}

<hr>
<%--empty运算符--%>
<%--判断是否为空,如果为空的话,结果就是true--%>
${empty param.username} <br>
${!empty param.username} <br>
${not empty param.username} <br>

<%--结果false--%>
<%--empty返回的是true/false,和空不相等,所以返回false,第一个是没有小括号,也是false,是因为类型不匹配--%>
${empty param.password == null}
${(empty param.password) == null}

<%--这个是true,如果password没有的话--%>
${param.password == null}
<hr>
${empty param.username ? "对不起,用户名不能为空!" : "登陆成功"}



















