<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>SuperCell-ELM-A</title>

<!-- Bootstrap core CSS -->
<link href="/SuperCell-ELM-A/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="/SuperCell-ELM-A/css/dashboard.css" rel="stylesheet">
<style>
.table>thead>tr>th, .table>tbody>tr>td {
	vertical-align: middle;
	text-align: center;
}
</style>
</head>
<body>

		<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="../../jsp/index.jsp">SuperCell-ELM-A</a>
			</div>
		</div>
	</nav>
	
	
		<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12 col-md-12 main">

				<h2 class="sub-header">订单详细信息</h2>
				<div class="table-responsive">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>订单ID</th>
								<th>订单日期</th>
								<th>商户名称</th>
								<th>商户地址</th>
								<th>客户手机号码</th>
								<th>总价(￥)</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td >${orderDetailComplaint.orderDetails.orderId}</td>
								<td >${orderDetailComplaint.orderDetails.dateOfOrder}</td>
								<td >${orderDetailComplaint.orderDetails.shopName}</td>
								<td >${orderDetailComplaint.orderDetails.address}</td>
								<td >${orderDetailComplaint.orderDetails.username}</td>
								<td>${orderDetailComplaint.orderDetails.total}</td>
							</tr>
						</tbody>
					</table>
					
					
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>菜品名</th>
								<th>单价</th>
								<th>数量</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${orderDetailComplaint.orderDetails.orderItemsList}" var="msg">
							<tr>
								<td>${msg.dishesName}</td>
								<td>${msg.price}</td>
								<td>${msg.count}</td>
							</tr>
							</c:forEach>
								
							
						</tbody>
					
					
					
					</table>
					
					
					
					
					
				</div>
				
								<h2 class="sub-header">投诉内容</h2>
				<div>
					<textarea class="form-control" rows="5" readonly>${orderDetailComplaint.complaint}</textarea>
				</div>
				
				<h2 class="sub-header">投诉处理</h2>
				<a href="../changeComplaintState.do?id=${orderDetailComplaint.orderDetails.orderId}&state=2"><button class="btn btn-primary">驳回投诉</button></a>
				<a href="../changeComplaintState.do?id=${orderDetailComplaint.orderDetails.orderId}&state=3"><button class="btn btn-warning">扣除押金</button></a>
				<a href="../changeComplaintState.do?id=${orderDetailComplaint.orderDetails.orderId}&state=4&merchantId=${orderDetailComplaint.orderDetails.merchantId}"><button class="btn btn-danger">拉黑该商户</button></a>
			</div>
		</div>
	</div>

	<div class="modal fade" id="idCardModal">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">身份证图片</h4>
				</div>
				<div class="modal-body">
					<img src="../images/Calculus.jpg" />
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	
	<script src="/SuperCell-ELM-A/js/jquery-1.11.3.min.js"></script>
	<script src="/SuperCell-ELM-A/js/bootstrap.min.js"></script>
</body>
</html>