$(function () {
    layui.use(['table', 'laydate', 'util', 'form'], function () {
        var form = layui.form;
        var templateSelect = $("select[name='selectIcon']");
        //获取后台传过来的iconvalue
        var iconvalue = $("#hidden-icon").val();
        console.log(templateSelect)
        console.log(iconvalue);
        // 编辑和查看详情，使用js渲染select, 添加option, 在初始化函数中，初始化select为默认选中请选择，
        // 移除默认选项的选中状态
        // removeAttr("selected")，然后获取freemark传过来的value，用swich将对应value的option添加select属性
        switch (iconvalue) {
            case '0':
                $("#selectIcon option[value='']").removeAttr("selected");
                templateSelect.append('<option value="0" selected></option>');
                templateSelect.append('<option value="1" ></option>');
                templateSelect.append('<option value="2" ></option>');
                templateSelect.append('<option value="3" ></option>');
                templateSelect.append('<option value="4" ></option>');
                templateSelect.append('<option value="5" ></option>');
                templateSelect.append('<option value="6" ></option>');
                break;
            case '1':
                $("#selectIcon option[value='']").removeAttr("selected");
                templateSelect.append('<option value="0" ></option>');
                templateSelect.append('<option value="1" selected></option>');
                templateSelect.append('<option value="2" ></option>');
                templateSelect.append('<option value="3" ></option>');
                templateSelect.append('<option value="4" ></option>');
                templateSelect.append('<option value="5" ></option>');
                templateSelect.append('<option value="6" ></option>');
                break;
            case '2':
                $("#selectIcon option[value='']").removeAttr("selected");
                templateSelect.append('<option value="0" ></option>');
                templateSelect.append('<option value="1" ></option>');
                templateSelect.append('<option value="2" selected></option>');
                templateSelect.append('<option value="3" ></option>');
                templateSelect.append('<option value="4" ></option>');
                templateSelect.append('<option value="5" ></option>');
                templateSelect.append('<option value="6" ></option>');
                break;
            case '3':
                $("#selectIcon option[value='']").removeAttr("selected");
                templateSelect.append('<option value="0" ></option>');
                templateSelect.append('<option value="1" ></option>');
                templateSelect.append('<option value="2" ></option>');
                templateSelect.append('<option value="3" selected></option>');
                templateSelect.append('<option value="4" ></option>');
                templateSelect.append('<option value="5" ></option>');
                templateSelect.append('<option value="6" ></option>');
                break;
            case '4':
                break;
            case '5':

                break;
            case '6':

                break;
            default:
                break;
        }
        form.render();


        //select选中切换input背景icon事件
        form.on('select(selectIcon)', function (data) {
            var input = $(".layui-select-title").find('input');
            switch (data.value) {
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


        });
    })
})


//更新3