<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%--
    if(){

    }else if(){

    }else if(){

    }else{

    }
--%>
<c:choose>
    <c:when test="${param.age < 18}">
        青年
    </c:when>

    <c:when test="${param.age < 35}">
        中年
    </c:when>
        老年
    <c:when test="${param.age < 55}">

    </c:when>
</c:choose>