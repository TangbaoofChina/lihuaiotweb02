// 指定图表的配置项和数据
var option = {
    title: {
        text: '示例曲线'
    },
    tooltip: {trigger: 'axis'},
    toolbox: {
        show: true,
        feature: {
            mark: {show: true},
            dataView: {show: true, readOnly: false},
            magicType: {show: true, type: ['bar']},
            restore: {show: true},
            saveAsImage: {show: true}
        }
    },
    legend: {
        data: ['销量1']
    },
    xAxis: {
        data: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"]
    },
    yAxis: {},
    series: [{
        name: '销量1',
        type: 'line',
        data: [5, 20, 36, 10, 10, 20]
    }]
};

// 指定图表的配置项和数据
var option1 = {
    title: {
        text: '历史曲线'
    },
    tooltip: {trigger: 'axis'},
    toolbox: {
        show: true,
        feature: {
            mark: {show: true},
            dataView: {show: true, readOnly: false},
            magicType: {show: true, type: ['bar']},
            restore: {show: true},
            saveAsImage: {show: true}
        }
    },
    legend: {
        data: []
    },
    xAxis: {
        data: []
    },
    yAxis: {},
    series: []
};

function InitHisDataTable() {
    var array = [];
    var questionColumns = [];
    $.ajax({
        url: "/lihuaiotweb01/historyChartsTable/DeviceHead",
// 数据发送方式
        type: "POST",
// 接受数据格式
        dataType: "json",
// 要传递的数据
        data: {sDevicesId: array.join(',')},
        success: function (result) {
            var json = eval(result); //数组
            for (var i = 0; i < json.length; i++) {
                var temp = {field: json[i].data, title: json[i].title, align: json[i].align};//手动拼接columns
                questionColumns.push(temp);
            }
            $('#table_id_example').bootstrapTable('destroy');
            $('#table_id_example').bootstrapTable({
                columns: questionColumns,
            });
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

function ReloadHisDataTable() {
    var array = [];
    var questionColumns = [];
    $("input[name='deviceCheckBox']").each(function(){
        if($(this).is(":checked"))
        {
            array.push($(this).attr('id'));
        }
    });
    $.ajax({
        url: "/lihuaiotweb01/historyChartsTable/DeviceHead",
        async: false,
// 数据发送方式
        type: "POST",
// 接受数据格式
        dataType: "text",
// 要传递的数据
        data: {sDevicesId: array.join(',')},
        success: function (result) {
            var json = eval(result); //数组
            for (var i = 0; i < json.length; i++) {
                var temp = {field: json[i].data, title: json[i].title, align: json[i].align};//手动拼接columns
                questionColumns.push(temp);
            }
            var array = [];
            $("input[name='deviceCheckBox']").each(function(){
                if($(this).is(":checked"))
                {
                    array.push($(this).attr('id'));
                }
            });
            var startTime = document.getElementById("HisChartDeviceStartTime").value;
            var endTime = document.getElementById("HisChartDeviceEndTime").value;
            var queryParamObj = document.getElementById("HisChartDeviceParameterSelID"); //定位id
            var queryParamIndex = queryParamObj.selectedIndex; // 选中索引
            var queryParameter = queryParamObj.options[queryParamIndex].text; // 选中文本
            $.ajax({
                url: "/lihuaiotweb01/historyChartsTable/hisMessageTables",
                async: false,
// 数据发送方式
                type: "POST",
// 接受数据格式
                dataType: "text",
// 要传递的数据
                data: {sDevicesId: array.join(','),sQueryParam:queryParameter,sStartTime: startTime, sEndTime: endTime},
                success: function (result) {
                    var json = eval(result); //数组
                    $('#table_id_example').bootstrapTable('destroy');
                    var tableHeight = 80;
                    if (json.length > 0)
                        tableHeight = 300;
                    $('#table_id_example').bootstrapTable({
                        data:json,
                        columns: questionColumns,
                        height:tableHeight,
                    });
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);
                }
            });
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

function ChartselectTimeChange() {
    var timeSel = document.getElementById("HisChartDeviceTimeSelID");

    switch (timeSel.value) {
        case "0":
            document.getElementById("HisChartDeviceStartTime").value = BeforeNowtime(1); //1小时
            document.getElementById("HisChartDeviceEndTime").value = GetNowtime(); //当前时间
            break;
        case "1":
            document.getElementById("HisChartDeviceStartTime").value = BeforeNowtime(3); //3小时
            document.getElementById("HisChartDeviceEndTime").value = GetNowtime(); //当前时间
            break;
        case "2":
            document.getElementById("HisChartDeviceStartTime").value = BeforeNowtime(24); //24小时
            document.getElementById("HisChartDeviceEndTime").value = GetNowtime(); //当前时间
            break;
        case "3":
            document.getElementById("HisChartDeviceStartTime").value = NowWeeHours(); //凌晨
            document.getElementById("HisChartDeviceEndTime").value = GetNowtime(); //当前时间
            break;
        case "4":
            document.getElementById("HisChartDeviceStartTime").value = YesterdayWeeHours(); //昨日凌晨
            document.getElementById("HisChartDeviceEndTime").value = YesterdayMidnight(); //昨日午夜
            break;
        case "5":
            document.getElementById("HisChartDeviceStartTime").value = NowWeeMonth(); //本月初
            document.getElementById("HisChartDeviceEndTime").value = GetNowtime(); //当前时间
            break;
        case "6":
            document.getElementById("HisChartDeviceStartTime").value = NowWeeYear(); //今年初
            document.getElementById("HisChartDeviceEndTime").value = GetNowtime(); //当前时间
            break;
    }
}

function getOneCheckBox(name,idnumber) {
    var oCheckbox=document.createElement("input");
    var myText=document.createTextNode(name);
    oCheckbox.setAttribute("type","checkbox");
    oCheckbox.setAttribute("id",idnumber);
    oCheckbox.setAttribute("name","deviceCheckBox");
    var mydiv=document.getElementById("deviceShow");
    mydiv.appendChild(oCheckbox);
    mydiv.appendChild(myText);
}


function getDeviceCheckBox() {
    var data = "";
    $.ajax({
        type: 'POST',
        url: '/lihuaiotweb01/deviceInfo/DeviceInfo',
        async: true,
        dataType: 'json',
        data: {},
        success: function (result) {
            var json = eval(result); //数组
            var $div = $("<div></div>");
            $.each(json, function (index, item) {
                //循环获取数据
                $div.append('<input type="checkbox" name="deviceCheckBox" id="' + json[index].dSerialNum + '"/>'+ json[index].dName);
                if (((index + 1) % 4) === 0) {
                    $('<Br/>').appendTo($div);
                }
            });
            $div.appendTo('#deviceShow');
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

function FindDeviceHisMsg(){
    var array = [];
    $("input[name='deviceCheckBox']").each(function(){
        if($(this).is(":checked"))
        {
            array.push($(this).attr('id'));
        }
    });
    var startTime = document.getElementById("HisChartDeviceStartTime").value;
    var endTime = document.getElementById("HisChartDeviceEndTime").value;
    var queryParamObj = document.getElementById("HisChartDeviceParameterSelID"); //定位id
    var queryParamIndex = queryParamObj.selectedIndex; // 选中索引
    var queryParameter = queryParamObj.options[queryParamIndex].text; // 选中文本
    var data = {sDevicesId: array.join(','),sQueryParam:queryParameter,sStartTime: startTime, sEndTime: endTime};
    if(array.length > 0 & array.length < 6)
    {
        ReloadHisDataTable(data);
        FormatChartLineBar(data);
    }else{
        alert("设备选择1~5个");
    }
}

function InitFormatChart() {
    var myChart = echarts.init(document.getElementById('echartsmain'), 'macarons');
    myChart.setOption(option);
}

function FormatChartLineBar(data) {
    var myChart = echarts.init(document.getElementById('echartsmain'),'macarons');
    //myChart.setOption(option);
    $.ajax({
        type: 'POST',
        url: '/lihuaiotweb01/historyCharts/hisMessageCharts',
        dataType: 'json',
        data: data,
        success: function (result) {
            if(JSON.stringify(result) !== '[]') {
                var json = eval(result); //数组
                var optionstring1 = json.chartsParameters.dParameterName;
                var optionstring2 = json.chartsParameters.dParameterTime;
                var optionstring3 = json.chartsParameters.dParameterdata;
                option1.title.text = "温度曲线";
                option1.legend.data = optionstring1;
                option1.xAxis.data = optionstring2;
                option1.series = [];
                for (var k = 0, length = optionstring3.length; k < length; k++) {
                    option1.series.push(optionstring3[k]);
                }
                // 使用刚指定的配置项和数据显示图表。
                myChart.clear();
                myChart.setOption(option1);
            }else {
                alert("未查询到历史数据");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

