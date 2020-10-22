<%--
  Created by IntelliJ IDEA.
  User: WHJ
  Date: 2020/10/21
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>录入页面</title>
</head>
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js">
</script>
<body>
    <h2>录入学生信息</h2>
    <form action="/TomcatTest" method="post" name=form>

        <span style="font-size: large; ">录入页面</span><br>

        学生名：<input type="text" value="" name="name"><br>

        性别：<input type="text" value="" name="sex"><br>

        年龄：<input type="text" value="" name="age"><br>

        年级：<input type="text" value="" name="grade"><br>

        <input type="submit" value="提交" name="submit">

        <input type="reset" value="重置">

    </form>
</body>
</html>
