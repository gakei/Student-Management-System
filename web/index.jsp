<%--
  Created by IntelliJ IDEA.
  User: WHJ
  Date: 2020/10/21
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
你好广志。
<%
    String   s   =(String)request.getAttribute( "ValueA ");
    out.print(s);
%>
</body>
</html>
