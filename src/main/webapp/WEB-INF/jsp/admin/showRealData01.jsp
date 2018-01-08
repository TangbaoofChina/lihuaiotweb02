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
							<form class="bs-example bs-example-form col-md-5" role="form" style="margin: 20px 0 10px 0;" action="/lihuaiotweb01/admin/selectRealDataByName" id="form1" method="post">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="请输入设备名称" name="findByName">
									<span class="input-group-addon btn" id="sub">搜索</span>
								</div>
							</form>
						</div>
				    </div>
				    <table class="table table-bordered">
					        <thead>
					            <tr>
					                <th>序号</th>
				  					<th>名称</th>
				  					<th>室内温度01</th>
				  					<th>室内温度02</th>
				  					<th>室内温度03</th>
				  					<th>室外温度</th>
									<th>发送时间</th>
									<th>接收时间</th>
					            </tr>
					        </thead>
					        <tbody>
							<c:forEach  items="${deviceList}" var="item">
								<tr>
									<td>${item.deviceSerialNum}</td>
									<td>${item.dName}</td>
									<td>${item.eC01InTemp1}</td>
									<td>${item.eC01InTemp2}</td>
									<td>${item.eC01InTemp3}</td>
									<td>${item.eC01OutTemp4}</td>
									<td>${item.sSendTime}</td>
									<td>${item.sRecevieTime}</td>
								</tr>
							</c:forEach>
					        </tbody>
				    </table>
				    <div class="panel-footer">
						<c:if test="${pagingVO != null}">
							<nav style="text-align: center">
								<ul class="pagination">
									<li><a href="/lihuaiotweb01/admin/showRealData?page=${pagingVO.upPageNo}">&laquo;上一页</a></li>
									<li class="active"><a href="">${pagingVO.curentPageNo}</a></li>
									<c:if test="${pagingVO.curentPageNo+1 <= pagingVO.totalCount}">
										<li><a href="/lihuaiotweb01/admin/showRealData?page=${pagingVO.curentPageNo+1}">${pagingVO.curentPageNo+1}</a></li>
									</c:if>
									<c:if test="${pagingVO.curentPageNo+2 <= pagingVO.totalCount}">
										<li><a href="/lihuaiotweb01/admin/showRealData?page=${pagingVO.curentPageNo+2}">${pagingVO.curentPageNo+2}</a></li>
									</c:if>
									<c:if test="${pagingVO.curentPageNo+3 <= pagingVO.totalCount}">
										<li><a href="/lihuaiotweb01/admin/showRealData?page=${pagingVO.curentPageNo+3}">${pagingVO.curentPageNo+3}</a></li>
									</c:if>
									<c:if test="${pagingVO.curentPageNo+4 <= pagingVO.totalCount}">
										<li><a href="/lihuaiotweb01/admin/showRealData?page=${pagingVO.curentPageNo+4}">${pagingVO.curentPageNo+4}</a></li>
									</c:if>
									<li><a href="/lihuaiotweb01/admin/showRealData?page=${pagingVO.totalCount}">最后一页&raquo;</a></li>
								</ul>
							</nav>
						</c:if>
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
		$("#nav li:nth-child(2)").addClass("active");

        $("#sub").click(function () {
            $("#form1").submit();
        });

        <c:if test="${pagingVO != null}">
			if (${pagingVO.curentPageNo} == ${pagingVO.totalCount}) {
				$(".pagination li:last-child").addClass("disabled")
			};

			if (${pagingVO.curentPageNo} == ${1}) {
				$(".pagination li:nth-child(1)").addClass("disabled")
			};
        </c:if>
	</script>
</html>