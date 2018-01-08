

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

function getDevice() {
    var data = "";
    $.ajax({
        type: 'POST',
        url: '/lihuaiotweb01/deviceInfo/DeviceInfo',
        async: true,
        dataType: 'json',
        data: {},
        success: function (result) {
            var json = eval(result); //数组
            var optionstring = "";
            $.each(json, function (index, item) {
                //循环获取数据
                var name = json[index].dName;
                var idnumber = json[index].dSerialNum;
                optionstring += "<option value=\"" + idnumber + "\" >" + name + "</option>";
            });
            $("#HisChartDeviceSelID").html("<option value=\"" + 0 + "\"'>请选择...</option> " + optionstring);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

function InitHisDataTable() {
    $.ajax({
        type: 'POST',
        data: {},
        url: '/lihuaiotweb01/historyTable/DeviceHead',
        dataType:"json",
        success: function (result) {
            /*alert("1");*/
            var json = eval(result); //数组
            LoadTable(json);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}


function LoadTable(tablehead) {
    $('#table_id_example').dataTable({
        "data": "",
        "columns": tablehead,
        "language": {
            "sProcessing": "处理中...",
            "sLengthMenu": "显示 _MENU_ 项结果",
            "sZeroRecords": "没有匹配结果",
            "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
            "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
            "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
            "sInfoPostFix": "",
            "sSearch": "搜索:",
            "sUrl": "",
            "sEmptyTable": "表中数据为空",
            "sLoadingRecords": "载入中...",
            "sInfoThousands": ",",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上页",
                "sNext": "下页",
                "sLast": "末页"
            },
            "oAria": {
                "sSortAscending": ": 以升序排列此列",
                "sSortDescending": ": 以降序排列此列"
            }
        },
        "bPaginate": true, //翻页功能
        "bLengthChange": true, //改变每页显示数据数量
        "bFilter": true, //过滤功能
        "bStateSave": true,
        "bSort": false, //排序功能
        "bInfo": true,//页脚信息
        "bAutoWidth": true,//自动宽度
        //"bServerSide":true,//这个用来指明是通过服务端来取数据
        //"bProcessing": true,  // 是否显示取数据时的那个等待提示

        //"serverSide": true,//因为我们的数据都是从后台过来的，不是前台的静态数据，要开启“服务器模式”，这样，你每次对表格的操作，都会变成一次次的请求发送给服务器
        "ajax": {
            "url": "/lihuaiotweb01/historyTable/showHisMessageTable",
            "type": "POST",
            "async": "true",
            "data": {},
            "dataType": "json",
            "dataSrc": "",
        },
    });
}

function GetMyList(data) {

    $('#table_id_example').DataTable().settings()[0].ajax.data = data;
    $('#table_id_example').DataTable().ajax.reload(null, false);

}

function FindDeviceHisMsg(){
    var objS = document.getElementById("HisChartDeviceSelID");
    var deviceId = objS.options[objS.selectedIndex].value;
    var startTime = document.getElementById("HisChartDeviceStartTime").value;
    var endTime = document.getElementById("HisChartDeviceEndTime").value;
    var data = {sDeviceId: deviceId, sStartTime: startTime, sEndTime: endTime};
    if(deviceId != '0')
    {
        GetMyList(data);
    }else{
        alert("请选择设备");
    }

}

