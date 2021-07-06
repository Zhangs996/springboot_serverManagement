<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../../static/frame/layui-v2.5.6/layui/css/layui.css">
    <script src="../../static/frame/jquery-v3.5.1/jquery-3.5.1.js" charset="utf-8"></script>
    <script src="../../static/frame/layui-v2.5.6/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="../../static/js/common/util.js"></script>
    <script src="../../static/js/common/info.js" charset="utf-8"></script>


</head>
<body>
<div id="app">
    <form class="layui-form add-form" method="post" action="${request.contextPath}/NoticeController/saveEmailProducer.json">

        <div class="layui-row">
            <div class="layui-col-xs12">
                <div class="layui-form-item">
                    <label class="layui-form-label">收件人<span style="color:red;">*</span>：</label>
                        <div class="layui-input-inline">
                            <select name="reciveMan" lay-verify="required" lay-search="">
                                <option value="">直接选择或搜索选择</option>
                                <#list users as user>
                                <option value=${user.uId}>${user.uName}</option>
                                </#list>
                            </select>
                        </div>
                </div>
            </div>
        </div>

        <div class="layui-row">
            <div class="layui-col-xs12">
                <div class="layui-form-item">
                    <label class="layui-form-label">标题<span style="color:red;">*</span>：</label>
                    <div class="layui-input-block">
                        <input type="text" maxlength="40" name="eTopic" lay-verify="required" placeholder="请输入菜单URL"
                               class="layui-input layui-input-sm ">
                    </div>
                </div>
            </div>
        </div>

        <div class="layui-row">
            <div class="layui-col-xs12">
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">文本域 ：</label>
                    <div class="layui-input-block">
                        <textarea name="eContent" placeholder="请输入内容" class="layui-textarea"></textarea>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-row">
            <div class="layui-col-xs12">
                <div class="layui-form-item" style="text-align: right;">
                    <div class="layui-input-block">
                        <button type="submit" class="layui-btn btn-primary layui-btn-sm" lay-submit
                                lay-filter="save_form">发送
                        </button>
                        <button type="reset" class="layui-btn layui-btn-primary layui-btn-sm">重置</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>