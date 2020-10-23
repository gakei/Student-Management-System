<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>layout 后台大布局 - Layui</title>
    <link rel="stylesheet" href="../layui/css/layui.css">
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
                    <a href="javascript:;">读者信息管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" id="readerList">读者列表</a></dd>
                        <dd><a href="javascript:;" id="addReader">添加读者信息</a></dd>
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
            <div class="layui-btn-group demoTable">
                <button class="layui-btn" data-type="getCheckData">获取选中行数据</button>
                <button class="layui-btn" data-type="getCheckLength">获取选中数目</button>
                <button class="layui-btn" data-type="isAll">验证是否全选</button>
            </div>

            <table class="layui-table" lay-data="{width: 728, height:330, url:'/ShowList', page:true, id:'idTest'}" lay-filter="demo">
                <thead>
                <tr>
                    <th lay-data="{type:'checkbox', fixed: 'left'}"></th>
                    <th lay-data="{field:'id', width:80, sort: true, fixed: true}">ID</th>
                    <th lay-data="{field:'name', width:80}">用户名</th>
                    <th lay-data="{field:'sex', width:80, sort: true}">性别</th>
                    <th lay-data="{field:'age', width:80}">年龄</th>
                    <th lay-data="{field:'grade', width:160, fixed: 'right'}">年级</th>
                    <th lay-data="{fixed: 'right', width:178, align:'center', toolbar: '#barDemo'}"></th>
                </tr>
                </thead>
            </table>
            <script type="text/html" id="barDemo">
                <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
                <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
            </script>
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

<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use('table', function(){
        var table = layui.table;
        //监听表格复选框选择
        table.on('checkbox(demo)', function(obj){
            console.log(obj)
        });
        //监听工具条
        table.on('tool(demo)', function(obj){
            var data = obj.data;
            if(obj.event === 'detail'){
                layer.msg('ID：'+ data.id + ' 的查看操作');
            } else if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    obj.del();
                    layer.close(index);
                });
            } else if(obj.event === 'edit'){
                layer.alert('编辑行：<br>'+ JSON.stringify(data))
            }
        });

        var $ = layui.$, active = {
            getCheckData: function(){ //获取选中数据
                var checkStatus = table.checkStatus('idTest')
                    ,data = checkStatus.data;
                layer.alert(JSON.stringify(data));
            }
            ,getCheckLength: function(){ //获取选中数目
                var checkStatus = table.checkStatus('idTest')
                    ,data = checkStatus.data;
                layer.msg('选中了：'+ data.length + ' 个');
            }
            ,isAll: function(){ //验证是否全选
                var checkStatus = table.checkStatus('idTest');
                layer.msg(checkStatus.isAll ? '全选': '未全选')
            }
        };

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script>
    $(function () {
        $("#examinationList").on("click", function () {
            /*点击考试列表*/
            $.get(
                "<%=application.getContextPath()%>/TestAjax",
                function (data) {
                    /*console.log(data.id)*/
                    $("#result").html("");
                    $.each(data, function (k, v) {//这里的函数参数是键值对的形式，k代表键名，v代表值
                        $("#result").append(
                            '<div class="layui-card block">'
                            + '<div class="layui-card-header">'
                            +  '序号:'
                            + data[k].id
                            + '</div>'
                            + '<div class="layui-card-body">'
                            + data[k].examName + '<br>'
                            + data[k].examTime + '<br>'
                            + data[k].chargeTeacher + '<br>'
                            + data[k].examPlace + '<br>'
                            + '</div>'
                            + '</div>')
                    });
                })
        })
    });
    $(function () {
        $("#readerList").on("click", function () {
            /*点击考试列表*/
            $.get(
                "<%=application.getContextPath()%>/ShowList",
                function (data) {
                    /*console.log(data.id)*/
                    $("#result").html("");
                    $.each(data, function (k, v) {//这里的函数参数是键值对的形式，k代表键名，v代表值
                        $("#result").append(
                            '<div class="layui-card block">'
                            + '<div class="layui-card-header">'
                            +  '序号:'
                            + data[k].id
                            + '</div>'
                            + '<div class="layui-card-body">'
                            + data[k].name + '<br>'
                            + data[k].sex + '<br>'
                            + data[k].age + '<br>'
                            + data[k].grade + '<br>'
                            + '</div>'
                            + '</div>')
                    });
                })
        })
    });
    //添加读者信息
    $(function () {
        $("#addReader").on("click", function () {
            $("#result").html("");
            $("#result").append("<h2>录入学生信息</h2>\n" +
                "    <form action=\"/TomcatTest\" method=\"post\" name=form>\n" +
                "\n" +
                "        <span style=\"font-size: large; \">录入页面</span><br>\n" +
                "\n" +
                "        学生名：<input type=\"text\" value=\"\" name=\"name\"><br>\n" +
                "\n" +
                "        <input id=\"man\" type=\"radio\"  value=\"0\" checked=\"checked\" name=\"sex\" />男<input id=\"woman\" type=\"radio\" value=\"1\"  name=\"sex\"/>女 <br>\n" +
                "\n" +
                "        年龄：<input type=\"text\" value=\"\" name=\"age\"><br>\n" +
                "\n" +
                "        年级：<input type=\"text\" value=\"\" name=\"grade\"><br>\n" +
                "\n" +
                "        <input type=\"submit\" value=\"提交\" name=\"submit\">\n" +
                "\n" +
                "        <input type=\"reset\" value=\"重置\">\n" +
                "\n" +
                "    </form>");
        })
    })
</script>

</body>
</html>
