<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../layui/css/layui.css"  media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>

<!-- 示例-970 -->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
    <legend>修改学生信息</legend>
</fieldset>

<form class="layui-form" action="" lay-filter="example">
    <div class="layui-form-item">
        <div class="layui-inline" style="display: none">
            <div class="layui-input-inline">
                <input type="text" name="id" autocomplete="off" class="layui-input" id="id">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-inline">
                <input type="text" name="name" autocomplete="off" class="layui-input" id="name">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-inline">
                <input type="text" name="sex" autocomplete="off" class="layui-input" id="sex">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">年龄</label>
            <div class="layui-input-inline">
                <input type="text" name="age" autocomplete="off" class="layui-input" id="age">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">年级</label>
            <div class="layui-input-inline">
                <input type="text" name="grade" autocomplete="off" class="layui-input" id="grade">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">班别</label>
            <div class="layui-input-inline">
                <input type="text" name="classNo" autocomplete="off" class="layui-input" id="classNo">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">宿舍号</label>
            <div class="layui-input-inline">
                <input type="text" name="dormitoryNo" autocomplete="off" class="layui-input" id="dormitoryNo">
            </div>
        </div>
    </div>

    <%--<div class="layui-form-item">
        <div class="layui-input-block">
            <button type="button" class="layui-btn layui-btn-normal" id="LAY-component-form-setval">赋值</button>
            <button type="button" class="layui-btn layui-btn-normal" id="LAY-component-form-getval">取值</button>
            <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
        </div>
    </div>--%>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="button" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
        </div>
    </div>
</form>

<script src="../layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
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
            $.post(
                '/EditStudentInfo',
                {
                    id: $("#id").val(),
                    name: $("#name").val(),
                    sex: $("#sex").val(),
                    age: $("#age").val(),
                    grade: $("#grade").val(),
                    classNo: $("#classNo").val(),
                    dormitoryNo: $("#dormitoryNo").val()
                },
                function(data, status) {
                    if (data.responseCode === 1) {
                        alert("编辑成功");
                        layer.closeAll();
                        window.parent.location.reload();
                    } else if (data.responseCode === 2) {
                        alert("服务器程序出错，请稍后再试");
                        layer.closeAll();
                        window.parent.location.reload();
                    } else {
                        alert("您的输入有误，请重新输入");
                    }
                }
            );
        });

        //表单赋值
        layui.$('#LAY-component-form-setval').on('click', function(){
            form.val('example', {
                "username": "贤心" // "name": "value"
                ,"password": "123456"
                ,"interest": 1
                ,"like[write]": true //复选框选中状态
                ,"close": true //开关状态
                ,"sex": "女"
                ,"desc": "我爱 layui"
            });
        });

        //表单取值
        layui.$('#LAY-component-form-getval').on('click', function(){
            var data = form.val('example');
            alert(JSON.stringify(data));
        });

    });
</script>

</body>
</html>