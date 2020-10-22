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
    <title>登录页面</title>
</head>
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js">
</script>
<script>
    $(document).ready(function(){
        $("button").click(function(){
            $.post("/try/ajax/demo_test_post.php",{
                    name:"菜鸟教程",
                    url:"http://www.runoob.com"
                },
                function(data,status){
                    alert("数据: \n" + data + "\n状态: " + status);
                });
        });
    });
</script>
<body>
    <form action="/TomcatTest" method="post" name=form>

        <span style="font-size: large; ">登录界面</span><br>

        用户名：<input type="text" value="" name="username"><br>

        密    码：<input type="text" value="" name="password"><br>

        <input type="submit" value="提交" name="submit">

        <input type="reset" value="重置">

    </form>
</body>
</html>
