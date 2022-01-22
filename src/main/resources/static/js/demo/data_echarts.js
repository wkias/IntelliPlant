$(function () {

    var barChart = echarts.init(document.getElementById("echarts-bar-chart"));
    var baroption ={
        title: {
            text: '设备总览'
        },
        legend: {
            data:['设备数量']
        },
        tooltip : {
            trigger: 'axis'
        },
        xAxis: {
            type: '',
            data: []
        },
        yAxis: {
        },

        series: [{
            name: '设备数量',
            type: 'bar',
            data: []
        }],
        calculable : true,
        grid:{
            x:30,
            x2:40,
            y2:24
        },
    };
    $.getJSON('/factory/equipmentManage/equipmentChart',function(data){
        $.each(data,function(i,n){
            baroption.xAxis.data.push(i);
            baroption.series[0].data.push(n);
        })
        barChart.setOption(baroption);
        window.onresize = barChart.resize;
    });



    var customerChart=echarts.init(document.getElementById("customer-chart"))
    var customerOption={
        title: {
            text: '客户报表'
        },
        legend: {
            data:['客户订单金额']
        },
        tooltip : {
            trigger: 'axis'
        },
        xAxis: {
            name:'金额（万元）',
            type: 'value',
            data: []
        },
        yAxis: {
            type: 'category',
            axisLabel:{
                fontSize:10
            },
            data: []
        },
        grid: {
            left:'35%',
            right: '15%'
        },
        series: [{
            name: '客户订单金额',
            type: 'bar',
            data: []
        }],
    }
    $.getJSON('/factory/orderManage/customerChart',function(data){
        data=Object.entries(data)
        data.sort(function (a,b) {
            return a[1]-b[1];
        })
        $.each(data,function(i,n){
            customerOption.yAxis.data.push(n[0]);
            customerOption.series[0].data.push(n[1]/10000);
        })
        console.log(customerOption)
        customerChart.setOption(customerOption);
        window.onresize = customerChart.resize;
    });


    var productPlanChart=echarts.init(document.getElementById("product-plan-chart"));
    var productPlanOption= {
        title: {
            text: '计划进度'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            data: ['完成数量', '需求数量']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'value'
        },
        yAxis: {
            name:'产品名称',
            type: 'category',
            data: []
        },
        series: [
            {
                name: '完成数量',
                type: 'bar',
                stack: '总量',
                // label: {
                //     show: true,
                //     position: 'right',
                //     formatter:function () {
                //         return "{d}"
                //     }
                // },
                data:[]
            },
            {
                name: '需求数量',
                type: 'bar',
                stack: '总量',
                label: {
                    show: true,
                    position: 'insideRight',
                },
                data: []
            },
        ]
    }
    $.getJSON('/factory/productPlan/planChart',function(data){
        data=Object.entries(data)
        console.log(data)
        $.each(data,function(i,n){
            productPlanOption.yAxis.data.push(n[0]);
            productPlanOption.series[0].data.push(n[1].complete);
            productPlanOption.series[1].data.push(n[1].demand);
        })
        console.log(productPlanOption)
        productPlanChart.setOption(productPlanOption);
        window.onresize = productPlanChart.resize;
    });

});
