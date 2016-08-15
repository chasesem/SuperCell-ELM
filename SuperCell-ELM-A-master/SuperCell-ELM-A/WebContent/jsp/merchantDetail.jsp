<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
<link href="../css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="../css/dashboard.css" rel="stylesheet">

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
				<a class="navbar-brand" href="../jsp/index.jsp">SuperCell-ELM-A</a>
			</div>
		</div>
	</nav>
		<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12 col-md-12 main">

				<h2 class="sub-header">商家详细信息</h2>
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								
								<th>商户名称</th>
								<th>商户地址</th>
								<th>联系电话</th>
								<th>商家订单数量</th>
								<th>评分</th>
								<th>身份证</th>
								<th>营业执照</th>
								<th>商店图片</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<tr>
							<td>${merchant.shopName}</td>
								<td>${merchant.address}</td>
								<td>${merchant.phoneNumber}</td>
								<td>${merchant.numberOfOrders}</td>
								<td>${merchant.rating}</td>
								<td><a data-toggle="modal" data-target="#idCardModal" href="#">查看</a></td>
								<td><a data-toggle="modal" data-target="#licenseModal" href="#">查看</a></td>
								
								<td><a data-toggle="modal" data-target="#shopModal" href="#">查看</a></td>
								
								<c:if test="${merchant.merchantState =='1'}">
								<td><a href="../merchantmanage/changestates.do?merchantID=${merchant.id}&state=4"><button class="btn btn-danger">拉黑</button></a></td>
								</c:if>
								<c:if test="${merchant.merchantState =='2'}">
								<td><a href="../merchantmanage/changestates.do?merchantID=${merchant.id}&state=1"><button class="btn btn-success">审核通过</button></a>
								<a href="../merchantmanage/changestates.do?merchantID=${merchant.id}&state=3"><button class="btn btn-danger">审核不通过</button></a>
								</td>
								</c:if>
								<c:if test="${merchant.merchantState =='3'}">
								<td><a href="../merchantmanage/changestates.do?merchantID=${merchant.id}&state=2"><button class="btn btn-info">重新审核</button></a></td>
								</c:if>
								<c:if test="${merchant.merchantState =='4'}">
								<td><a href="../merchantmanage/changestates.do?merchantID=${merchant.id}&state=1"><button class="btn btn-default">恢复正常</button></a></td>
								</c:if>
							</tr>
						</tbody>
					</table>
				</div>
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
					<img src="http://10.222.232.34:8080/SuperCell-ELM-M/pic/pic.do/${merchant.idCardPicPath}" width="90%"/>
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
		<div class="modal fade" id="licenseModal">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">营业执照图片</h4>
				</div>
				<div class="modal-body">
					<img src="http://10.222.232.34:8080/SuperCell-ELM-M/pic/pic.do/${merchant.licensePicPath}" width="90%"/>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	
			<div class="modal fade" id="shopModal">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">商家图片</h4>
				</div>
				<div class="modal-body">
					<img src="http://10.222.232.34:8080/SuperCell-ELM-M/pic/pic.do/${merchant.shopPicPath}" width="90%"/>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	
	



	<script src="../js/jquery-1.11.3.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
</body>
</html>