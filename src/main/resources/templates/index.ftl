<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>layout 后台大布局 - Layui</title>
    <link rel="stylesheet" type="text/css" href="../static/frame/layui-v2.5.6/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">layui 后台布局</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">控制台</a></li>
            <li class="layui-nav-item"><a href="">商品管理</a></li>
            <li class="layui-nav-item"><a href="">用户</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="">邮件管理</a></dd>
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                ${userDao.uName}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="${request.contextPath}/logout.html">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">基础功能</a>
                    <dl class="layui-nav-child" >
                        <dd ><a id="logContrl" url="/LogController/hello.html" onclick="tabshow(this)" >日志管理</a></dd>
                        <dd><a id="userContrl" url="/userlist.html" onclick="tabshow(this)">用户管理</a></dd>
                        <dd><a id="roleContrl" url="/RoleController/role_native.html" onclick="tabshow(this)">角色管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">权限管理</a>
                    <dl class="layui-nav-child">
                        <dd><a id="menuContrl" url="/menuController/showindex.html" onclick="tabshow(this)">菜单管理</a></dd>
                        <dd><a id="rolepartimContrl">角色成员管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="">云市场</a></li>
                <li class="layui-nav-item"><a href="">发布商品</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <iframe src="/showDeafult.html" id="main" class="showiframe" width="100%" style="height: 99.9%" frameborder="0"></iframe>
<#--
        <iframe src="/LogController/hello.html" id="main" width="100%" style="height: 99.9%" frameborder="0"></iframe>
-->
        <div class="layui-footer">
            <!-- 底部固定区域 -->
            © layui.com - 底部固定区域
        </div>
    </div>
    <script src="../static/frame/jquery-v3.5.1/jquery-3.5.1.js"></script>
    <script src="../static/frame/layui-v2.5.6/layui/layui.js"></script>
    <script src="../static/js/system.js"></script>
<#--<script src="../src/layui.js"></script>-->>

    <#--<script>-->
        <#--//JavaScript代码区域-->
        <#--layui.use(['element','jquery'], function(){-->
            <#--var $=layui.jquery;-->
            <#--var element = layui.element;-->
            <#--$(document).ready(function(){-->
                <#--$("dd>a").click(function (e) {-->
                    <#--e.preventDefault();//取消事件的默认动作。-->
                    <#--$("#main").attr("src",$(this).attr("href"));-->
                <#--});-->
            <#--});-->
            <#--// $("#cloud").click(function(){$("#main").html("jaosgoaighowg");});-->
        <#--});-->

    <#--</script>-->
</body>
</html>