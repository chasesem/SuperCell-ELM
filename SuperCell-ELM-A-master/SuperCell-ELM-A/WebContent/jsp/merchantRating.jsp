<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
            <li class="active"><a href="../merchantmanage/getLowRating.do">评分管理</a></li>
            <li><a href="../merchantmanage/getcomplain.do">投诉信息管理</a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

          <h2 class="sub-header">商家评分</h2>
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>商户ID</th>
                  <th>评分</th>
                  <th>状态</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
               <c:forEach items="${list}" var="msg">
               <tr>
               	<td>${msg.merchantId}</td>
               	<td>${msg.rating}</td>
               	<c:if test="${msg.merchantState==1}">
					<td>审核通过</td>
					<td><a href="../merchantmanage/changestates.do?merchantID=${msg.merchantId}&state=4"><Button class="btn btn-danger">加入到黑名单</Button></a></td>
				</c:if>
				<c:if test="${msg.merchantState==4}">
					<td>已拉黑</td>
					<td><a href="../merchantmanage/changestates.do?merchantID=${msg.merchantId}&state=1"><Button class="btn btn-success">恢复正常</Button></a></td>
				</c:if>
               </tr>
               </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="../js/jquery-1.11.3.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
  </body>
</html>