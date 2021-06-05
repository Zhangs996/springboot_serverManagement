layui.use('element', function () {
    var $ = layui.jquery;
    var element = layui.element;


})

//详情
function info_view(data) {
    layui.use('layer', function () {
        var layIndex = layer.open({
            type: 2,
            title: ['详情', 'font-weight: bold;'],
            shadeClose: false,
            shade: 0.3,
            anim: 1,
            maxmin: true,
            area: ['500px', '400px'],
            content: ['/userController/detail.html?', 'no']
        });
    });
}

//重置密码
function reloadPw() {
    layui.use('layer', function () {
        layer.confirm('确定重置密码吗?', {icon: 3, title: '提示'},
            function (index) {
                var mask = layer.load();
                $.ajax({
                    url: "/userController/reloadPw.json",
                    type: "GET",
                    contentType: "application/json;charset=UTF-8",
                    success: function (res) {
                        if (res.status) {
                            layui.layer.msg(res.msg, {icon: 1, time: 1000}, function () {
                            })
                        } else {
                            layui.layer.msg(res.msg, {icon: 2, time: 1500})
                        }
                        layer.close(mask);
                    }
                })
                layer.close(index);
            });

    });
}

//左侧导航栏点击事件
function tabshow(e) {
    var url = $(e).attr("url");
    console.log(url);
    $(".showiframe").attr('src', url);
}