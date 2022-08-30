<%@page contentType="text/html;charset=UTF-8" %>
<%
  pageContext.setAttribute("data","pageContext");
  request.setAttribute("data","request");
  session.setAttribute("data","session");
  application.setAttribute("data","application");

%>
<%--在没有指定范围的情况下,优先从小范围中取数据--%>
<%--1.pageContext < 2.request < 3.session < 4.application--%>
${data}
<hr>
<%--在EL表达式中可以指定范围来读取数据--%>
<%--EL表达式有4个隐含的范围对象--%>
<%--pageScope requestScope sessionScope applicationScope--%>
${pageScope.data} <br>
${requestScope.data} <br>
${sessionScope.data} <br>
${applicationScope.data} <br>
<%--在实际开发中,因为向某个域中存储数据的时候,那么都是不同的,所以xxxScope.指定就可以--%>