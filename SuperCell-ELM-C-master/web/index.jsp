<%--
  Created by IntelliJ IDEA.
  User: WUJO2
  Date: 8/6/2016
  Time: 8:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Client</title>
    <style>
        body {
            padding: 0;
            margin: 0;
            background-color: #F5F5F5;
        }

        a {
            text-decoration: none;
        }

        header {
            background-color: #64cdff;
            width: 100%;
            height: 50px;
            position: relative;
            top: 0;
            z-index: 900;
            transition: .6s;
        }

        header:hover {
            background: #00adff;
            transition: .6s;
        }

        nav {
            position: absolute;
            bottom: 10px;
            right: 12%;
        }

        nav > ul > li {
            list-style: none;
            float: left;
            font-size: 20px;
            color: #FFF;
            padding-left: 20px;
        }

        nav > ul > li > a {
            text-decoration: none;
            color: #454d67;
            font-size: 20px;
            transition: .3s;
        }

        nav > ul > li > a:hover {
            color: #ffffff;
            transition: .5s;
        }

        .clear {
            clear: both;
        }

        section {
            width: 960px;
            margin: 0 auto;
        }

        .adhead {
            background: #FFF;
            height: 50px;
            line-height: 50px;
            border-bottom: 1px solid #AAA;
            color: #fa2800;
            padding-left: 20px;
            margin-top: 30px;
            font-weight: 600;
        }

        .ads {
            height: 160px;
            border: 1px solid #ebebeb;
            box-sizing: border-box;
            background: #FFF;
        }

        .ad {
            position: relative;
            float: left;
        }

        .adinfo {
            position: absolute;
            bottom: 0px;
            background: #00adff;
            width: 220px;
            color: #FFF;
            padding: 10px;
            font-size: 14px;
            opacity: 0;
            transition: .3s;
        }

        .adinfo:hover {
            opacity: 1;
            transition: .3s;
        }

        .adinfo > div:nth-child(1) {
            font-size: 16px;
            font-weight: 700;
        }

        .adinfo > div {
            padding-bottom: 10px;
        }

        .ad > img {
            width: 232px;
            height: 160px;
            padding: 10px;
            box-sizing: border-box;
        }

        .merchant {
            width: 208px;
            background: #FFF;
            padding: 16px;
            padding-top: 26px;
            float: left;
            border-bottom: 1px solid #999;
            transition: .3s;
        }

        .merchant:hover {
            background: #F5F5F5;
            transition: .5s;
        }

        .merchant > a {
            color: #000;
        }

        .merchant > a > span:nth-child(1) {
            display: block;
        }

        .merchant > a > img {
            width: 208px;
            height: 156px;
        }

        .merchant > a > div {
            color: #313131;
        }

        .mname {
            font-size: 23px;
            padding-top: 10px;
            padding-bottom: 5px;
        }

        .numrank {
            padding-top: 20px;
            border-bottom: 1px solid #CCC;
            padding-bottom: 20px;
        }

        .numrank > div {
            font-size: 15px;

        }

        .numrank > div:nth-child(1) {
            float: left;
        }

        .numrank > div:nth-child(2) {
            float: right;
            color: #fa9700;
        }

        .mhead {
            background: #FFF;
            height: 50px;
            line-height: 50px;
            border-bottom: 1px solid #AAA;
            color: #fa9700;
            padding-left: 20px;
            margin-top: 30px;
            font-weight: 600;
        }

    </style>
</head>
<body>
<header>
    <nav>
        <ul id="afterLoginNavbar" hidden>
            <li><a href="/SuperCell-ELM-C">首页</a></li>
            <li><a href="/SuperCell-ELM-C/customerOrder/getOrders.do/1">查看订单</a></li>
            <li><a href="/SuperCell-ELM-C/customerOrder/viewComplaints.do">我的投诉</a></li>
            <li><label>Welcome, <span id="username"></span></label></li>
            <li><a href="/SuperCell-ELM-C/customer/userInfo">修改信息</a></li>
            <li><a id="logout" href="#">注销</a></li>
        </ul>
        <ul id="loginNavbar">
            <li><a href="customer/login">登录</a></li>
            <li><a href="customer/register">注册</a></li>
        </ul>
        <div class="clear">
        </div>
    </nav>
</header>
<section>
    <div class="adhead">菜品推荐
    </div>
    <div id="advertisementBar" class="ads">
        <!-- 在这里放推荐菜品列表 -->
    </div>
    <div class="clear"></div>
    <div id="merchantsDiv" class="main">
        <div class="mhead">商家列表</div>
        <!-- 这里放商家信息 -->
    </div>
</section>

</body>
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/mainPage.js"></script>
</html>