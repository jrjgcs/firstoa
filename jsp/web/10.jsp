<%@ page import="bean.User" %>
<%@page contentType="text/html;charset=UTF-8"%>

<%
    // 创建对象
    User user = new User();
    user.setName("zhangsan");
    // 存储到request域中
    request.setAttribute("fasdf",user);
    request.setAttribute("abc.def","hello jsp el!!!");

%>
<%--从域中取user--%>
${fasdf} <br>


<%--取user的username--%>
<%----%>
${fasdf.name} <br>


<%--取user的username,注意[]当中的需要添加 双引号--%>
<%--[]里面的没有双引号的话会将其看做变量,如果带双引号"name",则去user对象的name属性--%>
${fasdf["name"]} <br>
<hr>

<%--将数据取出,输出到浏览器--%>
${requestScope.abc.def} <%--没有出来空白,名字中有.,就要用[]--%>
<br>
${requestScope["abc.def"]}<%--这个是有的--%>