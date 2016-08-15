<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/nav.css" type="text/css" />
<link rel="stylesheet" href="../css/orderItem.css" type="text/css" />
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
	<section>
		<c:set var="orderSummary" value="${orderDetail.orderSummary}"></c:set>
		<c:set var="itemDetails" value="${orderDetail.itemDetails}"></c:set>
		<input type="hidden" value="${orderSummary.order.id}" id="currentItemPageOrderId">
		<div class="head"><span>用户——</span>${orderSummary.customerPhoneNumber}</div>
		<div class="dsinfo">
			
			<div class="dhead">
				<div class="dcell">菜品</div><div class="dcell">数量</div><div class="dcell">价格</div>
			</div>
			<c:forEach items="${itemDetails}" var="currentItem">
				<div class="dinfo">
					<div class="dicell">${currentItem.dishesName}</div>
					<div class="dicell">${currentItem.count}</div>
					<div class="dicell"><td>${currentItem.price/100}</td></div>
				</div>
			</c:forEach>
			<div class="total">
				总计 ： <span>${orderSummary.order.total/100}</span>￥
				
			</div>
		</div>
		<div class="otherinfo">下单时间 ：${orderSummary.order.dateOfOrder}</div>
		<div class="otherinfo">收货地址 ： ${orderSummary.order.address}</div>
		<form action="" method="">
			<div class="confirm">
				<c:choose>
					<c:when test="${orderSummary.order.state==merchantNotAck}">
						<a href="#" id="itemPageReceiveBtn">确认接单</a>
                		<a href="#" id="itemPageRefuseBtn">拒绝接单</a>
					</c:when>
					<c:otherwise>
						<a href="../view/order.jsp">好的</a>
					</c:otherwise>
				</c:choose>
			</div>
		</form>
	</section>
</body>
<script type="text/javascript">
$(document).ready(function(){
	function itemPageReceiveOrder(orderId){
		var url = "../orderController/receiveOrder?id="+orderId;
		itemPageSendRequest(url);
	}
	function itemPageRefuseOrder(orderId){
		var url = "../orderController/refuseOrder?id="+orderId;
		itemPageSendRequest(url);
	}
    function itemPageSendRequest(requestUrl){
		$.ajax({
            url:requestUrl,
            type:"GET",
            dataType:"json",
            success:function(data){
                window.location.reload();
            }
        });
	}  

	$("#itemPageReceiveBtn").on("click",function(){
		itemPageReceiveOrder($("#currentItemPageOrderId").val());
	}); 
	$("#itemPageRefuseBtn").on("click",function(){
		itemPageRefuseOrder($("#currentItemPageOrderId").val());
	}); 
});
</script>
</html>