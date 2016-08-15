
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Complaint</title>
    <style>
        body{
            padding: 0;
            margin: 0;
            background-color:#F5F5F5;
        }
        a{
            text-decoration: none;
        }
        header{
            background-color: #64cdff;
            width: 100%;
            height: 50px;
            position: relative;
            transition: .6s;
        }
        header:hover{
            background: #00adff;
            transition: .6s;
        }
        nav{
            position: absolute;
            bottom: 10px;
            right: 12%;
        }
        nav>ul>li{
            list-style: none;
            float: left;
            font-size: 20px;
            color: #FFF;
            padding-left: 20px;
        }
        nav>ul>li>a{
            text-decoration: none;
            color: #454d67;
            font-size: 20px;
            transition: .3s;
        }
        nav>ul>li>a:hover{
            color:#ffffff;
            transition: .5s;
        }
        .clear{
            clear: both;
        }
        section{
            width: 960px;
            margin: 0 auto;
        }
        .head{
            background: #FFF;
            height: 50px;
            line-height: 50px;
            border-bottom: 1px solid #AAA;
            color: #fa9700;
            margin-top: 30px;
            font-weight: 600;
            padding-left: 20px;
        }
        .mtable{
            display: table;
            width: 100%;
            text-align: center;
            background: #FFF;
        }
        .mtheader>div{
            font-size: 15px;
            font-weight: 600;
            color: #FFF;
            padding: 10px 0;
            background: #91dcff;
        }

        .mtr>div{
            padding: 10px 0;
            height: 70px;
            max-height: 70px;
            vertical-align: middle;
            position: relative;
            border-bottom: 1px dashed #DDD;
        }
        .mtr>div:nth-child(1),.mtr>div:nth-child(3),.mtr>div:nth-child(4)>div:nth-child(5){
            line-height: 70px;
        }
        .mtr>div:nth-child(2){
            overflow: overlay;
        }
        .mtr>div:nth-child(4)>label{
            display: block;
            width: 80px;
            height: 30px;
            margin: 0 auto;
            margin-top: 17px;
            line-height: 30px;
            font-size: 14px;
            font-weight: 600;
            border-radius: 8px;
        }
        .mtr>div>.untreated{
            background: #ccc;
            color: #FFF;
        }
        .mtr>div>.reject{
            background: #333;
            color: #FFF;
        }
        .mtr>div>.deduct{
            background: #c00;
            color: #FFF;
        }

        .mtheader>div,.mtr>div{
            float: left;
            text-align: center;
            vertical-align:middle;
        }
        .mtheader>div:nth-child(1),.mtr>div:nth-child(1){
            width: 160px;
        }
        .mtheader>div:nth-child(2),.mtr>div:nth-child(2){
            width: 250px;
        }
        .mtheader>div:nth-child(3),.mtr>div:nth-child(3){
            width: 250px;
        }
        .mtheader>div:nth-child(4),.mtr>div:nth-child(4){
            width: 100px;
        }
        .mtheader>div:nth-child(5),.mtr>div:nth-child(5){
            width: 200px;
        }
        .cm{
        }
        .mtr>.cm>img{
            margin: 0 auto;
            width: 60px;
            height: 60px;
            border-radius: 50%;
            display: block;
        }
        .mtr>.cm>label{
            display: block;
            line-height: 18px;
            font-size: 18px;
        }
        .obtn{
            display: block;
            position: absolute;
            top: 27.5px;
            right: 50px;
            width: 100px;
            height: 35px;
            margin: 0 auto;
            color: #00adff;
            border: 1px solid #00adff;
            background: #fbfbfb;
            font-weight: 600;
            transition: .5s
        }
        .obtn:hover{
            border: 1px solid #EEE;
            background: #00adff;
            color: #fff;
            transition: .3s
        }
        .ctab{
            width: 960px;
            background: #fff;
            padding: 10px 0px;
            border-bottom: 1px dotted #303030;
        }
        .ctab>table{
            width: 100%;
            text-align: center;
            margin: 0 auto;
        }
        .ctab>table>thead{
            color: #8a8a8a;
            background: #EEE;
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
<section>
    <div class="head">
        我的投诉
    </div>
    <div class="mtable">

        <div class="mtheader">
            <div>被投诉商家</div><div>投诉内容</div><div>下单时间</div><div>处理</div><div>查看</div>
        </div>
        <c:forEach items="${cdList}" var="complaint">
        <div class="mtr">
            <div class="cm">
                <img src="http://10.222.232.34:8080/SuperCell-ELM-M/pic/pic.do/${complaint.shopPicPath }" alt="">
                <label for="">${complaint.merchantName}</label>
            </div>
            <div style="line-height:70px;">
               ${complaint.complainMessage}
            </div>
            <div>
                <fmt:formatDate  pattern="yyyy-MM-dd HH:mm:ss" value="${complaint.dateOfOrder}" />
            </div>
            <div>
                <label class="deduct">
                    <c:if test="${complaint.state==1}">未处理</c:if>
                    <c:if test="${complaint.state==2}">投诉驳回</c:if>
                    <c:if test="${complaint.state==3}">扣除押金</c:if>
                    <c:if test="${complaint.state==4}">商家已拉黑</c:if>
                </label>
            </div>
            <div>
                <button class="obtn" onclick="showComplaint(this)">订单详情</button>
            </div>
        </div>
        <div class="ctab" hidden="hidden">
            <table cellpadding="5px">
                <thead>
                <tr><th>菜品名</th><th>单价</th><th>数量</th></tr>
                </thead>
                <tbody>
                <c:forEach items="${complaint.oidList}" var="orderItem">
                    <tr><td>${orderItem.dishesName}</td><td>${orderItem.price/100}</td><td>${orderItem.count}</td></tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        </c:forEach>
    </div>
</section>
</body>
<script>
    function showComplaint(obj){
        $(obj).parent().parent().next().stop(true, false).slideToggle(500);
    }
</script>
<script src="../js/jquery-1.11.3.min.js"></script>
<script src="../js/showUsername.js"></script>
</html>
