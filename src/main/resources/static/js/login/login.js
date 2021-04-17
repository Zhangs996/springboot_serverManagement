
//登陆事件
layui.use(['form'], function () {
    var form = layui.form;
    form.on('submit(sblogin)', function (data) {
        var action = data.form.action;
        var method = data.form.method;
        var field = data.field;
        console.log(action);
        console.log(method);
        console.log(field);
        $.ajax({
            type:method,
            url:action,
            dataType: 'json',
            contentType: 'application/json; charset=UTF-8',
            data:field,
            success: function(res) {

            }
        })
    })
})

