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

//请求服务数据时所传参数
function queryParams(params){
    var array = [];
    $("input[name='deviceCheckBox']").each(function () {
        if ($(this).is(":checked")) {
            array.push($(this).attr('id'));
        }
    });
    var startTime = document.getElementById("HisChartDeviceStartTime").value;
    var endTime = document.getElementById("HisChartDeviceEndTime").value;
    var queryParamObj = document.getElementById("HisChartDeviceParameterSelID"); //定位id
    var queryParamIndex = queryParamObj.selectedIndex; // 选中索引
    var queryParameter = queryParamObj.options[queryParamIndex].text; // 选中文本
    return {
        pageNumber: params.offset + 1,
        //每页多少条数据
        pageSize: params.limit,
        sDevicesId: array.join(','),
        sQueryParam: queryParameter,
        sStartTime: startTime,
        sEndTime: endTime
    };
}

function ReloadHisDataTable() {
    var array = [];
    var questionColumns = [];
    $("input[name='deviceCheckBox']").each(function () {
        if ($(this).is(":checked")) {
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

            var json = eval(result); //数组
            $('#table_id_example').bootstrapTable('destroy');
            var tableHeight = 80;
            if (json.length > 0)
                tableHeight = 300;
            $('#table_id_example').bootstrapTable({
                //是否显示行间隔色
                striped: true,
                //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                cache: false,
                //是否显示分页（*）
                pagination: true,
                //是否启用排序
                sortable: false,
                //排序方式
                sortOrder: "asc",
                //每页的记录行数（*）
                pageSize: 10,
                //可供选择的每页的行数（*）
                pageList: [10, 25, 50, 100],
                //是否显示搜索
                search: false,
                //data:json,
                //这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据
                url: "/lihuaiotweb01/historyChartsTable/hisMessageTablesPage",
                contentType: "application/x-www-form-urlencoded",//必须要有！！！！
                method: 'post',                      //请求方式（*）
                dataType: "json",
                //默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
                //queryParamsType:'',
                ////查询参数,每次调用是会带上这个参数，可自定义
                queryParamsType:'limit',//查询参数组织方式
                queryParams: queryParams,
                //分页方式：client客户端分页，server服务端分页（*）
                sidePagination: "server",
                locale:'zh-CN',//中文支持
                toolbar:'#toolbar',//指定工作栏
                columns: questionColumns,
                height: tableHeight,
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
                $div.append('<input type="checkbox" name="deviceCheckBox" id="' + json[index].dSerialNum + '"/>' + json[index].dName);
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

function FindDeviceHisMsg() {
    var array = [];
    $("input[name='deviceCheckBox']").each(function () {
        if ($(this).is(":checked")) {
            array.push($(this).attr('id'));
        }
    });
    var startTime = document.getElementById("HisChartDeviceStartTime").value;
    var endTime = document.getElementById("HisChartDeviceEndTime").value;
    var queryParamObj = document.getElementById("HisChartDeviceParameterSelID"); //定位id
    var queryParamIndex = queryParamObj.selectedIndex; // 选中索引
    var queryParameter = queryParamObj.options[queryParamIndex].text; // 选中文本
    var data = {sDevicesId: array.join(','), sQueryParam: queryParameter, sStartTime: startTime, sEndTime: endTime};
    if (array.length > 0 & array.length < 6) {
        ReloadHisDataTable(data);
        //FormatChartLineBar(data);
    } else {
        alert("设备选择1~5个");
    }
}

