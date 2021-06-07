<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <#--<link rel="stylesheet" href="//res.layui.com/layui/dist/css/layui.css"  media="all">-->
    <link rel="stylesheet" href="../../static/frame/layui-v2.5.6/layui/css/layui.css">
    <link rel="stylesheet" href="../../static/css/log/log.css">
    <script src="../../static/frame/jquery-v3.5.1/jquery-3.5.1.js" charset="utf-8"></script>
    <script src="../../static/frame/echars/echarts.min.js"></script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // //series对象
    // function seriesArr() {
    //     this.name = name
    //     this.data = data
    // }
    //
    // var seriesArr = new seriesArr();
    // 指定图表的配置项和数据
    myChart.setOption({
        title: {
            text: '商品销售产量关系图'
        },
        tooltip: {},
        legend: {
            data: ['销售额', '生产量']
        },
        xAxis: {
            // data: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"]
            data: []
        },
        yAxis: {},
        series: [{
            name: '销售额',
            type: 'bar',
            data: []
        }, {
            name: '生产量',
            type: 'bar',
            data: [],
            itemStyle: {
                normal: { //柱子颜色
                    color: 'blue'
                }
            },
        }]
    });
    myChart.showLoading();//异步加载显示加载中
    // var url = "/RoleController/queryBindMenuByRid.json?" + $.param({rId: rIdVal});
    var url = "/RoleController/queryBindMenuByRid.json?" + $.param({rId: rIdVal});
    // 异步加载数据
    sendAjax.sendGetAjax(url, null, function (res) {
        if (res.status) {
            var xAxisData=  res.data.xAxisData;
            var seriesList= res.data.seriesList;
            // 填入数据
            myChart.setOption({
                xAxis: {
                    data: xAxisData
                },
                series: seriesList
                // series: [{
                //     // 根据名字对应到相应的系列
                //     name: '销量',
                //     data: data.data
                // }, {
                //     name: '生产量',
                //     data: data.data
                // }]
            });
        } else {
            layer.msg(result.msg, {icon: 2, time: 1500});
        }
    })
</script>


</body>
</html>