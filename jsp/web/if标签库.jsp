<%@page contentType="text/html;charset=UTF-8"%>
<%--
八大内置对象:
pageContext < request < session < application
page exception config
out response
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--核心标签库中的if标签--%>
text属性是必须的,支持EL表达式,只能是boolean类型的
<c:if test="${empty param.username}"> <br>
用户名不能为空
</c:if>
<%--scope有四个值可选:page(pageContext域) request(request域) session(session域) application(application域)--%>
<%--v保存的是text里面的boolean里面的值--%>
<c:if test="${not empty param.username}" var="v" scope="request"> <br>
    欢迎你${param.username}
</c:if>
<hr>
<%--通过将EL表达式将request域当中的v取出--%>
${v}