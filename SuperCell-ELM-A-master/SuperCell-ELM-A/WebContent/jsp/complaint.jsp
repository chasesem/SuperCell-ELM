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
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="../jsp/index.jsp">SuperCell-ELM-A</a>
        </div>
      </div>
    </nav>
    
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li><a href="../merchantmanage/getmerchantstate.do">商户管理</a></li>
            <li><a href="../recommandedDishes/getlist.do">推荐菜品管理</a></li>
            <li><a href="../merchantmanage/getLowRating.do">评分管理</a></li>
            <li class="active"><a href="../merchantmanage/getcomplain.do">投诉信息管理</a></li>
          </ul>
        </div>
              <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

          <h2 class="sub-header">客户投诉信息</h2>
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>商户ID</th>
                  <th>商家名</th>
                  <th>订单号</th>
                  <th>客户手机号</th>
                  <th>投诉处理状态</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach items="${conplaintList}" var="msg">
              <tr>
              <td>${msg.merchantId}</td>
              <td>${msg.merchantName}</td>
			<td>${msg.orderId}</td>
			<td>${msg.phoneNumber}</td>
			<c:if test="${msg.complainState==1}">
				<td>待处理</td>
			</c:if>
			<c:if test="${msg.complainState==2}">
				<td>驳回投诉</td>
			</c:if>
			<c:if test="${msg.complainState==3}">
				<td>扣除押金</td>
			</c:if>
			<c:if test="${msg.complainState==4}">
				<td>拉黑</td>
			</c:if>
			<td><a href="../merchantmanage/getOrderItem.do/${msg.orderId}"><button class="btn btn-info">查看详情</button></a></td>
			</tr>
			</c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>


</body>
</html>