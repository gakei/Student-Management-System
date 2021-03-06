<%--
  Created by IntelliJ IDEA.
  User: WHJ
  Date: 2020/10/27
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../layui/css/layui.css"  media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<form class="layui-form" action="/InputRulesRecord" method="post">

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-inline">
                <input type="text" name="name" id="name" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">违规类型</label>
        <div class="layui-input-inline">
            <select name="type" id="type">
                <option value="" selected="">请选择违规类型</option>
                <option value="1">夜不归宿</option>
                <option value="2">违规使用违禁电器</option>
                <option value="3">内务不整洁</option>
                <option value="4">宿舍内抽烟</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="button" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
        </div>
    </div>
</form>

<script src="../layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script>
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate;

        //日期
        laydate.render({
            elem: '#date'
        });
        laydate.render({
            elem: '#date1'
        });

        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //自定义验证规则
        form.verify({
            title: function(value){
                if(value.length < 5){
                    return '标题至少得5个字符啊';
                }
            }
            ,pass: [
                /^[\S]{6,12}$/
                ,'密码必须6到12位，且不能出现空格'
            ]
            ,content: function(value){
                layedit.sync(editIndex);
            }
        });

        //监听指定开关
        form.on('switch(switchTest)', function(data){
            layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
                offset: '6px'
            });
            layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
        });

        //监听提交
        form.on('submit(demo1)', function(data){
            console.log($("#name").val());
            console.log($("#type").val());
            $.post(
                '/InputRulesRecord',
                {
                    name: $("#name").val(),
                    type: $("#type").val()
                },
                function(data, status) {
                    console.log(data.responseCode);
                    if (data.responseCode === 1) {
                        alert("没有这个学生");
                    }
                    if (data.responseCode === 2) {
                        alert("提交成功");
                        window.parent.location.reload();
                    }
                }
            );
        });

    });
</script>

</body>
</html>
