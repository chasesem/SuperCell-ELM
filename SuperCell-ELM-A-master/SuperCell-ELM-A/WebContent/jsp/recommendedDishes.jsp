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
            <li class="active"><a href="../recommandedDishes/getlist.do">推荐菜品管理</a></li>
            <li><a href="../merchantmanage/getLowRating.do">评分管理</a></li>
            <li><a href="../merchantmanage/getcomplain.do">投诉信息管理</a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

          <h2 class="sub-header">菜品列表</h2>
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>菜品ID</th>
                  <th>商户名称</th>
                  <th>菜品名称</th>
                  <th>菜品预览</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach items="${list}" var="item">
                <tr>
               	  <td>${item.merchantId}</td>
                  <td>${item.merchantName}</td>
                  <td>${item.dishesId}</td>
                  <td>${item.dishName}</td>
                  <td><button class="btn btn-success" value="${item.recommended}" onclick="changeState(this,${item.merchantId})">选为推荐</button></td>
                 
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
  <script>
  	var btns = document.getElementsByTagName("Button");
  	
  	for(var i = 0 ; i < btns.length; i++){
  	
  		if(btns[i].value=="false"){
  			btns[i].className="btn btn-default";
  			btns[i].innerHTML = "设为推荐"
  		}else if(btns[i].value=="true"){
  			btns[i].className="btn btn-success";
  			btns[i].innerHTML = "取消推荐";
  		}
  	}
  	function changeState(obj,merchantId){
  		if(obj.className=="btn btn-success"){
  			setState(merchantId,"down");
  			obj.className="btn btn-default";
  			obj.innerHTML = "设为推荐";
  			
  		}else{
  			var abtns = document.getElementsByClassName("btn btn-success");
  	  		if(abtns.length>=4){
  	  			alert("推荐菜品已达上限！");
  	  		}else{
  	  			setState(merchantId,"up");
  				obj.className="btn btn-success";
  				obj.innerHTML="取消推荐";
  	  		}	
  		}
  	}
  	function setState(merchantId,flag){
  		$.ajax({
  			type:"POST",
  			url:"../recommandedDishes/changestate.do",
  			data:{
  				id:merchantId,
  				state:flag
  			},
  			error:function(){
  				alert("额，后台报错了...");
  			}
  		});
  	}
  </script>
</html>