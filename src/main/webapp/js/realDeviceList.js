
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
            "url": "/lihuaiotweb01/admin/selectRealDataTable",
            "type": "POST",
            "async": "true",
            "data": {},
            "dataType": "json",
            "dataSrc": "",
        },
    });
}

function InitRealDataTable() {
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
    //定时刷新数据
    setInterval(function () { LoadData(); }, 3000);
}

function LoadData() {

    $('#table_id_example').DataTable().ajax.reload(null, false);
    //$("#table_id_example").dataTable().fnDraw(false);
}


