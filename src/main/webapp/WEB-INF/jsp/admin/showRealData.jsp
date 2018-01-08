<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<link href="/lihuaiotweb01/images/lihuaiotweb01.ico" rel="shortcut icon">
	<title>实时数据显示</title>

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
	<script type="text/javascript" src="/lihuaiotweb01/js/realDeviceList.js"></script>
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
					    	<h1 class="col-md-5">实时数据</h1>
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
	<div class="container" id="footer">
		<div class="row">
			<div class="col-md-12"></div>
		</div>
	</div>
</body>
	<script type="text/javascript">
		$("#nav li:nth-child(2)").addClass("active");
        /*第三步：初始化Datatables*/
        $(function () {
            InitRealDataTable();
        });
	</script>
</html>