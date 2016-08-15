<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Preview</title>
    <link href="../css/orderPreview.css" rel="stylesheet" type="text/css"/>

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
    <div class="head">${otp.shopName }<span> > 预览订单</span></div>
    <div class="dsinfo">
        <div class="dhead">
            <div class="dcell">菜品</div><div class="dcell">数量</div><div class="dcell">价格</div>
        </div>
        <c:forEach items="${otp.oidList }" var="oid">
        <div class="dinfo">
            <div class="dicell">${oid.dishesName }</div><div class="dicell">${oid.count }</div><div class="dicell">${oid.price/100 }</div>
        </div>
        </c:forEach>

        <div class="total">总计 ： <span>${otp.total/100 }</span>￥</div>
    </div>

    <div class="head">我的信息</div>
    <div class="uinfo">
        <div>联系方式：${otp.username }</div>
        <div>收货地址：${otp.address }</div>
    </div>
    <div class="combtn">
        <a href="makeOrder.do">确认提交</a>
    </div>
    <div class="combtn">
        <a href="/SuperCell-ELM-C/dishes/viewDishes" style="background:#64cdff;">返回商店</a>
    </div>
</section>
</body>
<script src="/SuperCell-ELM-C/js/jquery-1.11.3.min.js"></script>
<script src="/SuperCell-ELM-C/js/showUsername.js"></script>
</html>