$(function () {
    layui.use(['table', 'laydate', 'util', 'form'], function () {
        var form = layui.form
        var setting = {
            view: {
                addHoverDom: addHoverDom,
                removeHoverDom: removeHoverDom,
                selectedMulti: false
            },
            //开启工具栏
            edit: {
                enable: true,
                editNameSelectAll: true,
                showRemoveBtn: true,
                showRenameBtn: true
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                beforeEditName: beforeEditName,
                beforeRemove: beforeRemove,
                beforeRename: beforeRename,
                onRename: onRename
            }
        };
        var zNodes = [];
        getTree();
        var log, className = "dark";


        function beforeEditName(treeId, treeNode) {
            className = (className === "dark" ? "" : "dark");
            showLog("[ " + getTime() + " beforeEditName ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            zTree.selectNode(treeNode);
            setTimeout(function () {
                if (confirm("Start node '" + treeNode.name + "' editorial status?")) {
                    setTimeout(function () {
                        zTree.editName(treeNode);
                    }, 0);
                }
            }, 0);
            return false;
        }

        /*删除事件*/
        function beforeRemove(treeId, treeNode) {
            className = (className === "dark" ? "" : "dark");
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            zTree.selectNode(treeNode);
            var url = "/menuController/removeUrl.json"
            layer.confirm('确定删除该菜单吗?', {icon: 3, title: '提示'},
                function (index) {
                    console.log(treeNode.id);
                    var mask = layer.load();
                    sendAjax.sendGetAjax(url,  $.param({mId:treeNode.id}) , function (res) {
                        if (res.status) {
                            console.log(res.data);
                            zNodes = res.data;
                            layui.layer.msg(res.msg, {icon: 1, time: 1000}, function () {
                                location.reload();
                            })
                        }
                    })
                });
        }


        function beforeRename(treeId, treeNode, newName, isCancel) {
            className = (className === "dark" ? "" : "dark");
            showLog((isCancel ? "<span style='color:red'>" : "") + "[ " + getTime() + " beforeRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>" : ""));
            if (newName.length == 0) {
                setTimeout(function () {
                    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                    zTree.cancelEditName();
                    alert("Node name can not be empty.");
                }, 0);
                return false;
            }
            return true;
        }

        function onRename(e, treeId, treeNode, isCancel) {
            showLog((isCancel ? "<span style='color:red'>" : "") + "[ " + getTime() + " onRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>" : ""));
        }

        function showRemoveBtn(treeId, treeNode) {
            return !treeNode.isFirstNode;
        }

        function showRenameBtn(treeId, treeNode) {
            return !treeNode.isLastNode;
        }

        function showLog(str) {
            if (!log) log = $("#log");
            log.append("<li class='" + className + "'>" + str + "</li>");
            if (log.children("li").length > 8) {
                log.get(0).removeChild(log.children("li")[0]);
            }
        }

        function getTime() {
            var now = new Date(),
                h = now.getHours(),
                m = now.getMinutes(),
                s = now.getSeconds(),
                ms = now.getMilliseconds();
            return (h + ":" + m + ":" + s + " " + ms);
        }

        var newCount = 1;

        function addHoverDom(treeId, treeNode) {
            var sObj = $("#" + treeNode.tId + "_span");
            if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
            var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
                + "' title='add node' onfocus='this.blur();'></span>";
            sObj.after(addStr);
            var btn = $("#addBtn_" + treeNode.tId);
            //添加点击事件
            if (btn) btn.bind("click", function () {
                addTreeNode(treeNode.id);
            });
        };

        function removeHoverDom(treeId, treeNode) {
            $("#addBtn_" + treeNode.tId).unbind().remove();
        };

        function selectAll() {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            zTree.setting.edit.editNameSelectAll = $("#selectAll").attr("checked");
        }

        //获取菜单树
        function getTree() {
            var url = "/menuController/queryTreeAll.json";
            sendAjax.sendGetAjax(url, null, function (res) {
                if (res.status) {
                    console.log(res.data);
                    zNodes = res.data;
                }
            })
        }


        $(document).ready(function () {
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            $("#selectAll").bind("click", selectAll);
        });

    })
})

//菜单树添加按钮点击事件
function addTreeNode(treeId) {
    $(".showTreeiframe").attr('src', "/menuController/addpage.html?" + $.param({treeId: treeId}));
}