<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录界面</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        body {
            position: relative;
            background: url("/image/avatar.jpg") no-repeat;
            background-size:cover;
        }
        .divLogin {
            position: absolute;
            right: 150px;
            top: 120px;
            width: 450px;
            height: 350px;
            background-color: rgba(255, 255, 255, 0.4);
            border-radius: 8%;
        }
        .divLogin h3{
            width: 300px;
            margin: 50px 170px;
            font-size: 30px;
            color: #f7ff00;
            font-family: 华文楷体;
            font-weight: bolder;
        }
        .div1,
        .div2 {
            margin: 50px 60px;
            height: 20px;
            font-size: 18px;
            font-weight: 900;
            color: papayawhip;
        }

        .div1 label,
        .div2 label {
            float: left;
            display: block;
        }

        .div1 input,
        .div2 input {
            float: left;
            display: block;
            margin-left: 20px;
            height: 22px;
            width: 210px;
            border-radius: 5%;
            border-width: 3px;
            border-color: wheat;
            outline:medium;
        }

        .theSubmit {
            width: 100px;
            height: 40px;
            margin-left: 180px;
            display: block;
            color: gray;
            background-color: palegoldenrod;
            border-color: wheat;
            border-radius: 5%;
            border-width: 3px;
            outline:medium;
            font-size: 20px;
        }
    </style>
</head>
<body>
<p hidden>${msg}</p><br>
<%--    登录模块--%>
<div class="divLogin">
    <h3>
        登录账号
    </h3>

    <form action="../webPage/back.jsp" method="post">
        <div class="div1">
            <label for="username">username</label>
            <input type="text" name="username" id="username" placeholder="请输入账号"/>
        </div>

        <div class="div2">
            <label for="password">password</label>
            <input type="password" name="password" id="password" placeholder="请输入密码"/>
        </div>

        <input type="submit" value="登录" class="theSubmit"/>
        <p>${msg}</p>
    </form>
</div>
</body>
</html>
