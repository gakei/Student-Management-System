<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>layout 后台大布局 - Layui</title>
    <link rel="stylesheet" href="../layui/css/layui.css">
    <script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
    <script>
        $(function () {
            $("#examinationList").on("click", function () {
                /*点击考试列表*/
                $.get(
                    "<%=application.getContextPath()%>/TestAjax",
                    function (data) {
                        console.log(data.id)
                        $("#result").html(data.id)
                    })
            })
        })
    </script>
</head>
<body class="layui-layout-body" bgcolor="#f0f8ff">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">学生信息管理系统</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">控制台</a></li>
            <li class="layui-nav-item"><a href="">商品管理</a></li>
            <li class="layui-nav-item"><a href="">用户</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="">邮件管理</a></dd>
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    贤心
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">退了</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">成绩统计分析</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" id="examinationList">考试列表</a></dd>
                        <dd><a href="javascript:;">总排名</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">学生信息管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">学生列表</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="">云市场</a></li>
                <li class="layui-nav-item"><a href="">发布商品</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;" id="result">
            <c:forEach items="${requestScope.list}" var="i">
                <div class="layui-card block">
                    <div class="layui-card-header">
                        学生信息       序号：${i.id}
                    </div>
                    <div class="layui-card-body">
                            ${i.name}<br>
                            ${i.sex}<br>
                            ${i.age}<br>
                            ${i.grade}<br>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © WHJ - Code and Life
    </div>
</div>
<script src="../layui/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });
</script>
</body>
</html>
