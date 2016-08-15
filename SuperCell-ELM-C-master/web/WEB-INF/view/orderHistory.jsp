<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Orders</title>
    <link href="../../css/orderHistory.css" rel="stylesheet" type="text/css"/>
    <style>
        .yts{
            display: block;
            width: 100px;
            height: 25px;
            line-height: 25px;
            margin: 0 auto;
            background: #fa9787;
            margin-top: 12px;
            color: #FFF;
            border-radius: 6px;
        }
    </style>
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
<div class="cover" id="cover" hidden="hidden"></div>
<section>
    <div class="head">
        <a href="../getOrders.do/0"
                <c:if test="${orderState == 0 }">
                    class="active"
                </c:if>
        >待商家接单</a>
        <a href="../getOrders.do/1"
                <c:if test="${orderState == 1 }">
                    class="active"
                </c:if>
        >待确认收货</a>
        <a href="../getOrders.do/2"
                <c:if test="${orderState == 2 }">
                    class="active"
                </c:if>
        >已确认订单</a>
        <a href="../getOrders.do/3"
                <c:if test="${orderState == 3 }">
                    class="active"
                </c:if>
        >被拒绝订单</a>
    </div>
    <div class="orders">
        <div class="mtable">
            <div class="mtheader">
                <div>商家</div>
                <div></div>
                <div>总价（元）</div>
                <div>下单时间</div>
                <div>操作</div>
            </div>
            <c:forEach items="${ordersList }" var="orders">
                <div class="mtr">
                    <div class="mtd">
                        <img src="http://10.222.232.34:8080/SuperCell-ELM-M/pic/pic.do/${orders.shopPicPath }"/>
                    </div>
                    <div class="mtd">
                        <span>${orders.shopName }</span>
                    </div>
                    <div class="mtd">
                            ${orders.total/100 }
                    </div>
                    <div class="mtd">
                        <fmt:formatDate  pattern="yyyy-MM-dd HH:mm:ss" value="${orders.dateOfOrder }" />
                    </div>
                    <div class="mtd" id="${orders.orderId }">
                        <a href="../viewOrderDetails.do/${orders.orderId }" class="obtn">订单详情</a>
                        <c:if test="${orders.state == 'CUSTOMER_ACKNOWLEDGED' }">
                            <c:if test="${orders.complaintState == 0}">
                                <a href="#" class="obtn" onclick="comOrd(this)">我要投诉</a>
                            </c:if>
                            <c:if test="${orders.complaintState == 1}">
                                <span class="yts">已投诉</span>
                            </c:if>
                        </c:if>
                    </div>

                </div>
            </c:forEach>
        </div>
    </div>
</section>
<div class="complain" hidden="hidden" id="complain">
    <div class="head">我要投诉 <span class="hxbtn" onclick="comDis()"></span></div>
    <div>T.T 非常抱歉，我们会改进的!</div>
    <form action="../complain.do" method="post">
        <input type="text" hidden="hidden" name="orderId" id="comid"/>
        <div>
            <input type="text" placeholder="投诉内容" name="message"/>
        </div>
        <div class="combtn">
            <input type="submit" name="" value="提交投诉"/>
        </div>
    </form>
</div>
</body>
<script>
    function comDis() {
        var cover = document.getElementById("cover");
        cover.setAttribute("hidden", "hidden");
        var complain = document.getElementById("complain");
        complain.setAttribute("hidden", "hidden");
    }
    function comOrd(obj) {
        var comid = document.getElementById("comid");
        comid.value = obj.parentNode.id;
        var cover = document.getElementById("cover");
        var complain = document.getElementById("complain");
        cover.removeAttribute("hidden");
        complain.removeAttribute("hidden");
    }
</script>
<script src="../../js/jquery-1.11.3.min.js"></script>
<script src="../../js/showUsername.js"></script>
</html>