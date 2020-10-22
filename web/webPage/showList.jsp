<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>开始使用layui</title>
    <link rel="stylesheet" href="../layui/css/layui.css">
</head>

<body bgcolor="#a9a9a9">

<style type="text/css">
    .block {
        width: 200px;
        height: 200px;
    }
</style>
<!-- 你的HTML代码 -->

<c:forEach items="${requestScope.list}" var="i">
    <div class="layui-card block">
        <div class="layui-card-header">
            学生信息 <td>       序号：${i.id}
        </div>
        <div class="layui-card-body">
            ${i.name}<br>
            ${i.sex}<br>
            ${i.age}<br>
            ${i.grade}<br>
        </div>
    </div>
</c:forEach>

</body>
</html>