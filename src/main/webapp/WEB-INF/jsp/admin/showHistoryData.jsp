<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <link href="/lihuaiotweb01/images/lihuaiotweb01.ico" rel="shortcut icon">
    <title>历史数据显示</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入bootstrap -->
    <link rel="stylesheet" type="text/css" href="/lihuaiotweb01/css/bootstrap.min.css">
    <link rel="stylesheet" href="/lihuaiotweb01/css/show.css">
    <!-- 引入JQuery  bootstrap.js-->
    <script src="/lihuaiotweb01/js/jquery-3.2.1.min.js"></script>
    <script src="/lihuaiotweb01/js/bootstrap.min.js"></script>
    <!-- DataTables CSS -->
    <link rel="stylesheet" type="text/css" href="/lihuaiotweb01/datatables/css/jquery.dataTables.css">
    <!-- DataTables -->
    <script type="text/javascript" charset="utf8" src="/lihuaiotweb01/datatables/js/jquery.dataTables.js"></script>
    <!-- jQuery -->
    <script type="text/javascript" src="/lihuaiotweb01/js/timeFormat.js"></script>
    <script type="text/javascript" src="/lihuaiotweb01/js/hisDeviceData.js"></script>

    <%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
</head>
<body>
<!-- 顶栏 -->
<jsp:include page="top.jsp"></jsp:include>
<!-- 中间主体 -->
<div class="container" id="content">
    <div class="row">
        <jsp:include page="menu.jsp"></jsp:include>
        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <h1 class="col-md-3">历史数据</h1>
                        <div class="main-wrap input-group col-md-7">
                            <div class="wrap">
                                <div class="deviceChoose01">
                                    <label>设备：</label>
                                    <select name="HisChartDeviceSelName" id="HisChartDeviceSelID" class="input-sm">
                                        <option value="0">请选择...</option>
                                    </select>
                                </div>
                                <div class="quickChoose01">
                                    <label for="HisChartDeviceTimeSelID" id="choose">快捷选择：</label>
                                    <select name="HisChartDeviceTimeSelName" id="HisChartDeviceTimeSelID"
                                            class="input-sm" onchange="ChartselectTimeChange()">
                                        <option value="0">1小时</option>
                                        <option value="1">3小时</option>
                                        <option value="2" selected="selected">24小时</option>
                                        <option value="3">今日</option>
                                        <option value="4">昨日</option>
                                        <option value="5">当月</option>
                                        <option value="6">今年</option>
                                    </select>
                                </div>
                            </div>
                            <div id="wrap01">
                                <label for="time">时间：</label>
                                <input type="text" class="input-sm" id="HisChartDeviceStartTime">
                                <i class="glyphicon glyphicon-resize-horizontal"></i>
                                <input type="text" class="input-sm" id="HisChartDeviceEndTime">
                            </div>
                        </div>
                        <div class="search">
                            <button class="btn btn-default" id="button" onclick="FindDeviceHisMsg()">搜索</button>
                        </div>
                    </div>
                </div>
                <hr/>
                <!--第二步：添加如下 HTML 代码-->
                <table id="table_id_example" class="display" cellspacing="0" width="100%">
                </table>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $("#nav li:nth-child(3)").addClass("active");
    /*第三步：初始化Datatables*/
    $(function () {
        getDevice();
        document.getElementById("HisChartDeviceStartTime").value = BeforeNowtime(24); //获取日期与时间
        document.getElementById("HisChartDeviceEndTime").value = GetNowtime(); //获取日期与时间
        InitHisDataTable();
    });
</script>
</html>