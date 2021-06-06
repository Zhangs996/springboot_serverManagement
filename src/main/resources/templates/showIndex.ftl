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

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '商品销售产量关系图'
        },
        tooltip: {},
        legend: {
            data: ['销售额','生产量']
        },
        xAxis: {
            data: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"]
        },
        yAxis: {},
        series: [{
            name: '销售额',
            type: 'bar',
            data: [5, 20, 36, 10, 10, 20,5, 20, 36, 10, 10, 20,1,2]
        }, {
            name: '生产量',
            type: 'bar',
            data: [12, 20, 36, 10, 10, 20,5, 20, 36, 10, 10, 20,3],
            itemStyle: {
                normal: { //柱子颜色
                    color: 'blue'
                }
            },
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>


</body>
</html>