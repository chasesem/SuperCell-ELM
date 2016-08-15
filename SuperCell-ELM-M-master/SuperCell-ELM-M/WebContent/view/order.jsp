<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商家订单页面</title>
<link rel="stylesheet" href="../css/nav.css" type="text/css" />
<link rel="stylesheet" href="../css/order.css" type="text/css" />
<script type="text/javascript" src="../js/jquery.min.js"></script>

</head>
<body>
<header>
		<input type="hidden" id="merchantPhonenumer" value="${sessionScope.merchant}">
		<div class="mynav">
			<ul>
				<li><a href="/SuperCell-ELM-M/view/homePage.jsp">首页</a></li>
				<li><a href="/SuperCell-ELM-M/merchant/getMerchantInfoByPhoneNumber.do?phoneNumber=${sessionScope.merchant}">商家管理</a></li>
				<li><a href="/SuperCell-ELM-M/dishesController/showdishes.do">菜品管理</a></li>
				<li><a href="/SuperCell-ELM-M/view/order.jsp">订单管理</a></li>
				<li><a href="/SuperCell-ELM-M/complaint/getComplaintInfos/${sessionScope.merchant}">投诉管理</a></li>
			</ul>
		<div class="clear"></div>
		</div>
        <nav>
            <ul>
                <li><label>Welcome,<span>${sessionScope.merchant}</span></label></li>
                <li><a href="/SuperCell-ELM-M/merchant/logOff.do">注销</a></li>
                
            </ul>
            <div class="clear">
            </div>
        </nav>
    </header>
    <div class="cover" id="cover" hidden="hidden"></div>
    <section>
    	<div class="head" id="headNav">
    		<a class="active" href="#" id="notAckBtn">未确认订单</a>
    		<a href="#" id="ackBtn">已确认订单</a>
    		<a href="#" id="refusedBtn">已拒绝订单</a>
    	</div>
    	<div class="orders">
    		<div class="mtable" id="orderDiv">
    			<div class="mtheader">
    				<div>用户</div><div>总价（元）</div><div>下单时间</div><div>操作</div>
    			</div>
    			<!-- <div class="mtr">
					<div class="mtd">
						13631272505
					</div>
					<div class="mtd">
						81
					</div>
					<div class="mtd">
						2016-08-10 15:10:10
					</div>
					<div class="mtd" id="1">
						<a href="#" class="obtn">订单详情</a>
						<a href="#" class="obtn">确认接单</a>
						<a href="#" class="obtn">拒绝接单</a>
					</div>
				</div>
				 -->
				
				
    		</div>
    		
    	</div>
    </section>
</body>
<script type="text/javascript" src="../js/order.js"></script>
</html>