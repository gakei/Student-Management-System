<%--
  Created by IntelliJ IDEA.
  User: WHJ
  Date: 2020/10/24
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>TestIframe</title>
    <link rel="stylesheet" href="../layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <!-- 内容主体区域 -->
    <div style="padding: 15px;" id="result">
        <div class="layui-btn-group demoTable">
            <button class="layui-btn" data-type="getCheckData">获取选中行数据</button>
            <button class="layui-btn" data-type="getCheckLength">获取选中数目</button>
            <button class="layui-btn" data-type="isAll">验证是否全选</button>
        </div>

        <table class="layui-table" lay-data="{width: 800, height:500, url:'/ShowStudentList', page:true, id:'idTest'}" lay-filter="demo">
            <thead>
            <tr>
                <th lay-data="{type:'checkbox', fixed: 'left'}"></th>
                <th lay-data="{field:'id', width:80, sort: true, fixed: true}">学号</th>
                <th lay-data="{field:'grade', width:80}">年级</th>
                <th lay-data="{field:'classNo', width:100}">班别</th>
                <th lay-data="{field:'name', width:80}">名字</th>
                <th lay-data="{field:'sex', width:80, sort: true}">性别</th>
                <th lay-data="{field:'age', width:80}">年龄</th>
                <th lay-data="{field:'dormitoryNo', width:80, fixed: 'right'}">宿舍号</th>
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
<script src="../layui/layui.js"></script>
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
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
                    console.log(data.id);
                    $.post(
                        '/DeleteStuInfo',
                        {
                            id: data.id,
                        },
                        function(data, status) {
                            if (data.responseCode === 1) {
                                alert("删除成功");
                            }
                            if (data.responseCode === 2) {
                                alert("服务器冒烟啦，请等待攻城狮修复！")
                            }
                        }
                    );
                    obj.del();
                    layer.close(index);
                });
            } else if(obj.event === 'edit'){
                layer.open({
                    type: 2,
                    content: './detail.jsp',
                    fixed: true,
                    title: false,
                    shadeClose:false,
                    area: ['660px', '400px'],
                    btn: ['确认', '取消'],
                    yes: function(index, layero){
                        layer.closeAll();
                    },
                    no: function () {
                    },
                    success:function(layero, index){
/*                        // 获取子页面的iframe
                        var iframe = window['layui-layer-iframe' + index];
                        // 向子页面的全局函数child传参
                        iframe.child(data.grade, data.classNo, data.sex);*/

                        var body=layer.getChildFrame('body',index);//少了这个是不能从父页面向子页面传值的
                        //获取子页面的元素，进行数据渲染
                        body.contents().find("#id").val(data.id);
                        body.contents().find("#name").val(data.name);
                        body.contents().find("#age").val(data.age);
                        body.contents().find("#dormitoryNo").val(data.dormitoryNo);
                    }
                });
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
</body>
</html>
