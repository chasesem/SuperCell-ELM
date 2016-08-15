<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <title>Order</title>
    <link href="../../css/acknowledgeOrder.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<header>
    <nav>

        <ul>
            <li><a href="/SuperCell-ELM-C">首页</a></li>
            <li><a href="/SuperCell-ELM-C/customerOrder/getOrders.do/1">查看订单</a></li>
            <li><a href="/SuperCell-ELM-C/customerOrder/viewComplaints.do">我的投诉</a></li>
            <li><label>Welcome, <span id="username"></span></label></li>
            <li><a href="/SuperCell-ELM-C/customer/userInfo">修改信息</a></li>
            <li><a id="logout" href="#">注销</a></li>
        </ul>
        <div class="clear">
        </div>
    </nav>
</header>
<section>
    <div class="head"><img src="http://10.222.232.34:8080/SuperCell-ELM-M/pic/pic.do/${orderDetails.shopPicPath }" alt="">${orderDetails.shopName }</div>
    <div class="dsinfo">
        <div class="dhead">
            <div class="dcell">菜品</div><div class="dcell">数量</div><div class="dcell">价格</div>
        </div>
        <c:forEach items="${orderDetails.orderItemsList }" var="oid">
        <div class="dinfo">
            <div class="dicell">${oid.dishesName }</div><div class="dicell">${oid.count }</div><div class="dicell">${oid.price/100 }</div>
        </div>
        </c:forEach>
     
        <div class="total">总计 ： <span>${orderDetails.total/100 }</span>￥</div>
    </div>
    <div class="otherinfo">下单时间 ： <fmt:formatDate  pattern="yyyy-MM-dd HH:mm:ss" value="${orderDetails.dateOfOrder }" /></div>
    <div class="otherinfo">收货地址 ： ${orderDetails.address }</div>
    <form action="../acknowledgeOrder.do?" method="GET">
        <div class="confirm">
        <c:choose>
        <c:when test="${orderDetails.state == 'MERCHANT_ACKNOWLEDGED'}">
            <div class="rank">
                <label>评分:</label>
                <input class="ranktbn" type="number" max="10" min="1" value="10" name="rating"/>
            </div>
            <input class="conbtn" onclick="" type="submit" value="确认收货"/>
            <input type="text" value="${orderDetails.orderId }" display="none" hidden="hidden" name="orderId"/>
        </c:when>
        <c:otherwise>
        	<c:if test="${orderDetails.state == 'MERCHANT_NOT_ACKNOWLEDGED' }">
            <a href="../getOrders.do/0" onclick="">知道了</a>
            </c:if> 
            <c:if test="${orderDetails.state == 'CUSTOMER_ACKNOWLEDGED' }">
            <a href="../getOrders.do/2" onclick="">知道了</a>
            </c:if>
            <c:if test="${orderDetails.state == 'MERCHANT_REFUSED' }">
                <a href="../getOrders.do/3" onclick="">知道了</a>
            </c:if>
        </c:otherwise>    
        </c:choose>
        </div>
    </form>
</section>
</body>
<script src="/SuperCell-ELM-C/js/jquery-1.11.3.min.js"></script>
<script src="/SuperCell-ELM-C/js/showUsername.js"></script>
</html>