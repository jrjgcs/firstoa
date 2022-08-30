<%@ page import="jakarta.servlet.http.HttpServletRequest" %>
<%@page contentType="text/html; charset=UTF-8"%>

<%--
    // 9大内置对象
    // pageContext request session application page out response config exception
    // pageContext是页面上下文
--%>
<%--以下代码一般只在EL表达式中用，因为EL表达式里里面没有request内置对象--%>
<%=pageContext.getRequest() %>
<br>
<%=request %>

<%--
requestScope 这个只代表"请求范围",不等同于request对象
在EL表达式当中有一种隐式的对象:pageContext
EL表达式中的pageContext和JSP中的九大内置对象pageContext是同一个对象
--%>
<hr>
<%--这里pageContext.getRequest()拿出来的是ServletRequest,但是我们要的是HttpServletRequest--%>
<%--getContextPath是只有HttpServletRequest方法才有的--%>
<%=((HttpServletRequest)pageContext.getRequest()).getContextPath()%> <br>
这段java代码对应EL表达式 <br>
<%--获取应用的根--%>
${pageContext.request.getContextPath()} <br>
<%--获取应用的目录--%>
${pageContext.request.getServletPath()}

































