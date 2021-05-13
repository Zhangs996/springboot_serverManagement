//自定义验证表单
layui.use(['table', 'laydate', 'util', 'form'], function () {
    var form=layui.form;
    form.verify({
        num: [
            /^[1-9]\d*$/
            , '密码必须为正整数，且不能出现空格'
        ]
    });
})