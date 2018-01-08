<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <link href="/lihuaiotweb01/images/lihuaiotweb01.ico" rel="shortcut icon">
    <title>人员组织显示</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入bootstrap -->
    <link rel="stylesheet" type="text/css" href="/lihuaiotweb01/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/lihuaiotweb01/css/bidType.css">
    <!-- 引入JQuery  bootstrap.js-->
    <script src="/lihuaiotweb01/js/jquery-3.2.1.min.js"></script>
    <script src="/lihuaiotweb01/js/bootstrap.min.js"></script>
    <!-- 引入bootstrap-table.js-->
    <script src="/lihuaiotweb01/js/bootstrap-table.js"></script>
    <link href="/lihuaiotweb01/css/bootstrap-table.css" rel="stylesheet"/>
    <script src="/lihuaiotweb01/js/bootstrap-table-zh-CN.js"></script>
    <!-- 引入bootstrap-suggest.js-->
    <script src="/lihuaiotweb01/js/bootstrap-suggest.js"></script>
    <script src="/lihuaiotweb01/js/bootstrap-suggest.min.js"></script>
    <!-- jQuery -->
    <script type="text/javascript" src="/lihuaiotweb01/js/peopleUnionORG.js"></script>
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
                        <h1 class="col-md-5">人员组织</h1>
                    </div>
                </div>

                <!--第二步：添加如下 HTML 代码-->
                <div class="panel-body">
                    <div class="zTreeDemoBackground left col-md-2" style="width:230px;height: 400px;overflow: scroll">
                        <div>
                            <input class="text-input" type="text" style="width:120px;" id="txtSearchTreeNode">
                            <button class="btn btn-default" onclick="searchTreeNode()">搜索</button>
                        </div>
                        <ul id="tree" class="ztree"></ul>
                    </div>
                    <jsp:include page="peopleUnionOneORG.jsp"></jsp:include>
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
<%--转移设备--%>
<!--新增页面-->
<div class="delete-popup" id="addNew-popup">
    <div class="addNew-wrap">
        <div class="delete-point">
            <h3 id="addNew-title">授权人员</h3>
            <button class="close" id="addNew-close">&times;</button>
        </div>
        <div class="addNew-content">
            <form action="" method="post">
                <div class="form-name">
                    <div class="row">
                        <label for="name" style="margin-left: 17px;margin-top: 26px">名称</label>
                        <div class="input-group" style="width:240px;margin-left: 50px;margin-top: -30px">
                            <input type="text" class="form-control" id="name"
                                   style="width:240px;margin-right:0px">
                            <div class="input-group-btn" style="width:1px;">
                                <ul class="dropdown-menu dropdown-menu-right" role="menu"></ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-org">
                    <div class="row">
                        <label for="org" style="margin-left: 17px;margin-top: 26px">组织</label>
                        <div class="input-group" style="width:240px;margin-left: 50px;margin-top: -30px">
                            <input type="text" class="form-control" id="org"
                                   style="width:240px;margin-right:0px">
                            <div class="input-group-btn" style="width:1px;">
                                <ul class="dropdown-menu dropdown-menu-right" role="menu"></ul>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <div class="delete-btn">
            <button class="btn btn-danger" id="addNew-save" onclick="saveUnionORG()">保存</button>
            <button class="btn btn-default" id="addNew-cancel">取消</button>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $("#nav li:nth-child(12)").addClass("active");
    /*第三步：初始化Datatables*/
    $(function () {
        var txtSearch = "";
        FindZTreeNode(txtSearch);
        initTable();
    });
</script>
</html>