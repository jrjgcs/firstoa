<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="bean.User" %>
<%@page contentType="text/html;charset=UTF-8" %>

<%
    // 一个map集合
    Map<String,String> map = new HashMap<>();
    map.put("username","zhangsan");
    map.put("password","123456");
    // 将map集合存放到域中
    request.setAttribute("userMap",map);

    Map<String, User> map1 = new HashMap<>();
    User user = new User();
    user.setName("lishi");
    map1.put("wo",user);
    request.setAttribute("fsadfsd",map1);


%>


<%--使用EL表达式,将map中的数据取出,输出到浏览器--%>
<%--集合自带get方法--%>
${userMap.username}
<br>
${userMap.password}
<br>
${userMap["username"]}
<br>
${userMap["password"]}

<hr>
<%--使用EL表达式,将map1集合中的user对象的name属性取出--%>
<%--存在域中的属性名.集合的key的名字.对象的属性的名字,本来取集合中的数据是要get("key")的,但是EL表达式会自动调用get方法,所以只要写key就可以了,不用加双引号--%>
${fsadfsd.wo.name}

























