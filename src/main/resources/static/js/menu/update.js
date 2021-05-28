$(function () {
    //获取后台传过来的iconvalue
    var iconValue = $("#hidden-icon").val();
    showDefalut(iconValue);

    layui.use(['table', 'laydate', 'util', 'form'], function () {
        var form = layui.form;
        //select选中切换input背景icon事件
        form.on('select(selectIcon)', function (data) {
            changeBackGroud(iconValue);
        });
    })
})

//跳转update页面初始化select
function showDefalut(iconValue) {
    getIconsUrl(iconValue);//发送ajax请求渲染select页面
    updateForm();
    changeBackGroud(iconValue);//主动触发背景切换
}

//获取全部icon url 事件
function getIconsUrl(iconValue) {
    var templateSelect = $("select[name='selectIcon']");
    switch (iconValue) {
        case '0':
            templateSelect.append('<option value="0" selected></option>');
            templateSelect.append('<option value="1" ></option>');
            templateSelect.append('<option value="2" ></option>');
            templateSelect.append('<option value="3" ></option>');
            templateSelect.append('<option value="4" ></option>');
            templateSelect.append('<option value="5" ></option>');
            templateSelect.append('<option value="6" ></option>');
            break;
        case '1':
            templateSelect.append('<option value="0" ></option>');
            templateSelect.append('<option value="1" selected></option>');
            templateSelect.append('<option value="2" ></option>');
            templateSelect.append('<option value="3" ></option>');
            templateSelect.append('<option value="4" ></option>');
            templateSelect.append('<option value="5" ></option>');
            templateSelect.append('<option value="6" ></option>');
            break;
        case '2':
            templateSelect.append('<option value="0" ></option>');
            templateSelect.append('<option value="1" ></option>');
            templateSelect.append('<option value="2" selected></option>');
            templateSelect.append('<option value="3" ></option>');
            templateSelect.append('<option value="4" ></option>');
            templateSelect.append('<option value="5" ></option>');
            templateSelect.append('<option value="6" ></option>');
            break;
        case '3':
            templateSelect.append('<option value="0" ></option>');
            templateSelect.append('<option value="1" ></option>');
            templateSelect.append('<option value="2" ></option>');
            templateSelect.append('<option value="3" selected></option>');
            templateSelect.append('<option value="4" ></option>');
            templateSelect.append('<option value="5" ></option>');
            templateSelect.append('<option value="6" ></option>');
            break;
        case '4':
            templateSelect.append('<option value="0" ></option>');
            templateSelect.append('<option value="1" ></option>');
            templateSelect.append('<option value="2" ></option>');
            templateSelect.append('<option value="3" ></option>');
            templateSelect.append('<option value="4" selected></option>');
            templateSelect.append('<option value="5" ></option>');
            templateSelect.append('<option value="6" ></option>');
            break;
        case '5':
            templateSelect.append('<option value="0" ></option>');
            templateSelect.append('<option value="1" ></option>');
            templateSelect.append('<option value="2" ></option>');
            templateSelect.append('<option value="3" ></option>');
            templateSelect.append('<option value="4" ></option>');
            templateSelect.append('<option value="5" selected></option>');
            templateSelect.append('<option value="6" ></option>');
            break;
        case '6':
            templateSelect.append('<option value="0" ></option>');
            templateSelect.append('<option value="1" ></option>');
            templateSelect.append('<option value="2" ></option>');
            templateSelect.append('<option value="3" ></option>');
            templateSelect.append('<option value="4" ></option>');
            templateSelect.append('<option value="5" ></option>');
            templateSelect.append('<option value="6" selected></option>');
            break;
    }
}

//根据select value切换input icon事件
function changeBackGroud(iconValue) {
    var input = $(".layui-select-title").find('input');
    switch (iconValue) {
        case '0':
            input.css({
                background: "url('../static/img/home.png') no-repeat  center"
            });
            break;
        case '1':
            input.css({
                background: "url('../static/img/anthority.png') no-repeat  center"
            });
            break;
        case '2':
            input.css({
                background: "url('../static/img/basics.png') no-repeat  center"
            });
            break;
        case '3':
            input.css({
                background: "url('../static/img/log.png') no-repeat  center"
            });
            break;
        case '4':
            input.css({
                background: "url('../static/img/menu.png') no-repeat  center"
            });
            break;
        case '5':
            input.css({
                background: "url('../static/img/role.png') no-repeat  center"
            });
            break;
        case '6':
            input.css({
                background: "url('../static/img/user.png') no-repeat  center"
            });
            break;
        default:
            break;
    }
    input.attr("placeholder", " ");

}

//更新form

function updateForm() {
    layui.use(['form'], function () {
        var form = layui.form;
        form.render();
    });
}
