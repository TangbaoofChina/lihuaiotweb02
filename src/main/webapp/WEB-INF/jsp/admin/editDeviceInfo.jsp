<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>


<!DOCTYPE html>
<html>
<head>
	<link href="/lihuaiotweb01/images/lihuaiotweb01.ico" rel="shortcut icon">
	<title>修改设备信息</title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- 引入bootstrap -->
	<link rel="stylesheet" type="text/css" href="/lihuaiotweb01/css/bootstrap.min.css">
	<!-- 引入JQuery  bootstrap.js-->
	<script src="/lihuaiotweb01/js/jquery-3.2.1.min.js"></script>
	<script src="/lihuaiotweb01/js/bootstrap.min.js"></script>
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
					    	<h1 style="text-align: center;">修改设备信息</h1>
						</div>
				    </div>
				    <div class="panel-body">
						<form class="form-horizontal" role="form" action="/lihuaiotweb01/deviceInfo/editDeviceInfo" id="editfrom" method="post">
							  <div class="form-group ">
							    <label for="inputEmail3" class="col-sm-2 control-label" >序号</label>
							    <div class="col-sm-10">
							      <input readonly="readonly" type="text" class="form-control" id="inputEmail3" name="dSerialNum" placeholder="请输入序号" value="${deviceInfo.dSerialNum}" />
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="inputDeviceName" class="col-sm-2 control-label">名称</label>
							    <div class="col-sm-10">
							      <input type="text" class="form-control" id="inputDeviceName" name="dName" placeholder="请输入名称" value="${deviceInfo.dName}"/>
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="inputDeviceIp" class="col-sm-2 control-label">连接IP</label>
							    <div class="col-sm-10">
								    <input readonly="readonly" type="text" class="form-control" id="inputDeviceIp" name="dIp" placeholder="请输入IP"  value="${deviceInfo.dIp}"  />
							    </div>
							  </div>
							<div class="form-group">
								<label for="inputDevicePort" class="col-sm-2 control-label">端口号</label>
								<div class="col-sm-10">
									<input readonly="readonly" type="text" class="form-control" id="inputDevicePort" name="dPort" placeholder="请输入端口号"  value="${deviceInfo.dPort}"  />
								</div>
							</div>
							<div class="form-group">
								<label for="inputDeviceProtocol" class="col-sm-2 control-label">协议</label>
								<div class="col-sm-10">
									<input readonly="readonly" type="text" class="form-control" id="inputDeviceProtocol" name="dProtocol" placeholder="请输入协议"  value="${deviceInfo.dProtocol}"  />
								</div>
							</div>
							  <div class="form-group" style="text-align: center">
								<button class="btn btn-default" type="submit">提交</button>
								<button class="btn btn-default" type="reset">重置</button>
							  </div>
						</form>
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
		$("#nav li:nth-child(1)").addClass("active")
	</script>
</html>