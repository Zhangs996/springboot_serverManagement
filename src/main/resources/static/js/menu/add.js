//自定义验证表单
layui.use(['table', 'laydate', 'util', 'form'], function () {
    var form=layui.form;
    form.verify({
        num: [
            /^[1-9]\d*$/
            , '密码必须6到12位，且不能出现空格'
        ]
    });
})
//更新3