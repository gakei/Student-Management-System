<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  Created by IntelliJ IDEA.
  User: WHJ
  Date: 2020/10/21
  Time: 23:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>showList</title>
</head>
<body>
    Hello!!!!!Live
    <c:forEach items="${requestScope.testList}" var="i">
        <p>${i}</p>
    </c:forEach>
</body>
</html>
