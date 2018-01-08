<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <link href="/lihuaiotweb01/images/lihuaiotweb01.ico" rel="shortcut icon">
    <title>树形菜单显示</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入bootstrap -->
    <link rel="stylesheet" type="text/css" href="/lihuaiotweb01/css/bootstrap.min.css">
    <!-- 引入JQuery  bootstrap.js-->
    <script src="/lihuaiotweb01/js/jquery-3.2.1.min.js"></script>
    <script src="/lihuaiotweb01/js/bootstrap.min.js"></script>
    <!-- DataTables CSS -->
    <link rel="stylesheet" type="text/css" href="/lihuaiotweb01/datatables/css/jquery.dataTables.css">
    <!-- DataTables -->
    <script type="text/javascript" charset="utf8" src="/lihuaiotweb01/datatables/js/jquery.dataTables.js"></script>
    <!-- jQuery -->
    <script type="text/javascript" src="/lihuaiotweb01/js/ztreeDeviceData.js"></script>
    <!-- jQuery ztree -->
    <link rel="stylesheet" href="/lihuaiotweb01/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="/lihuaiotweb01/ztree/js/jquery.ztree.core.js"></script>
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
                        <h1 class="col-md-5">树形菜单</h1>
                    </div>
                </div>

                <!--第二步：添加如下 HTML 代码-->
                <div class="panel-body">
                    <div>
                        <input class="text-input" type="text" id="txtSearchTreeNode">
                        <button class="btn btn-default" onclick="searchTreeNode()">搜索</button>
                    </div>
                    <div class="zTreeDemoBackground left col-md-3" style="width:230px;height: 400px;overflow: scroll">

                        <ul id="tree" class="ztree"></ul>
                    </div>
                    <jsp:include page="treeNodeOperate.jsp"></jsp:include>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container" id="footer">
    <div class="row">
        <div class="col-md-12"></div>
    </div>
</div>
</body>
<script type="text/javascript">
    $("#nav li:nth-child(10)").addClass("active");
    /*第三步：初始化Datatables*/
    $(function () {
        var txtSearch = "";
        FindZTreeNode(txtSearch);
        /*var t = $("#tree");
        t = $.fn.zTree.init(t, setting, zNodes);*/
    });
</script>
</html>