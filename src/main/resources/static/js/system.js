layui.use('element',function(){
    var $=layui.jquery;
    var element = layui.element;


})

//左侧导航栏点击事件
function tabshow(e){
    var url=$(e).attr("url");
    console.log(url);
    $(".showiframe").attr('src',url);
}