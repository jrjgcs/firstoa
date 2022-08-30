<%@ page import="bean.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%@page contentType="text/html;charset=UTF-8" %>
<%
    // 数组对象
    String[] username = {"zhangsna", "lishi", "wangwu"};

    // 向域中存数组
    request.setAttribute("nameArry", username);

    User use1 = new User("zhagnsan", null, null);
    User use2 = new User("lishi", null, null);
    User[] users = {use1, use2};
    // 数组中放的是对象
    request.setAttribute("users", users);
    // 存放的是集合
    List<String> list = new ArrayList<>();
    list.add("abc");
    list.add("def");
    request.setAttribute("myList",list);
    // 存放set集合
    Set<String> set = new HashSet<>();
    set.add("a");
    set.add("b");
    request.setAttribute("mySet",set);
%>
<hr>
${nameArry} <br>
${nameArry[0]}<%--取出数组的第一个元素--%>
${nameArry[1]}
${nameArry[2]}
<%--取不出数据,在浏览器上显示的就是空白--%>
${nameArry[100]}
<hr>
<%--对象数组,取对象的名字属性--%>
${users[0].name}
<hr>
<%--list集合取数据--%>
${myList[0]}
${myList[1]}

<%--set集合,不能用下标,因为set集合是无序的--%>
<%--${mySet[0]}--%>

${mySet}















































