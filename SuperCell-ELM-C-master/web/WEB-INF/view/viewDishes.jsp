<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Merchant</title>
    <style>
        body {
            padding: 0;
            margin: 0;
            background-color: #F5F5F5;
        }

        a {
            text-decoration: none;
            color:#000;
        }

        header {
            background-color: #64cdff;
            width: 100%;
            height: 50px;
            position: relative;
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

        .dishes {
            overflow: hidden;
        }

        .dish {
            float: left;
            width: 238px;
            height: 270px;
            border-right: 1px dashed #DDD;
            border-bottom: 1px dashed #DDD;
            padding-right: 1px;
            overflow: hidden;
            transition: .3s;
            background: #FFF;
            position: relative;
            color: #313131;
        }

        .dish:nth-child(4n+0) {
            border-right: none;
        }

        .dish:hover {
            background: #F5F5F5;
            transition: .5s;
        }

        .dish > img {
            width: 210px;
            height: 158px;
            margin: 0 auto;
            display: block;
            padding-top: 20px;
        }

        .dprice {
            position: absolute;
            bottom: 15px;
            left: 5px;
            color: #fe4d3d;
        }

        .plus {
            position: absolute;
            bottom: 5px;
            right: 10px;
        }

        .dname {
            display: inline-block;
            text-align: center;
            height: 16px;
            width: 200px;
            padding-left: 5px;
            font-size: 14px;
            padding-top: 10px;
            color: #313131;
            white-space: nowrap;
            text-overflow: ellipsis;
            overflow: hidden;
        }

        .pbtn {
            display: inline-block;
            width: 30px;
            height: 30px;
            border-radius: 5px;
            background: #00adff;
            position: relative;
        }

        .pbtn:before {
            content: "";
            width: 2px;
            height: 20px;
            background: #6b450a;
            position: absolute;
            right: 14px;
            top: 5px;
        }

        .pbtn:after {
            content: "";
            width: 2px;
            height: 20px;
            background: #6b450a;
            position: absolute;
            right: 14px;
            top: 5px;
            transform: rotate(90deg);
        }

        .mcart {
            position: fixed;
            top: 0px;
            width: 330px;
            right: -300px;
            background: #e6e6e6;
            height: 100%;
            transition: .5s;
        }

        .cartbtn {
            position: absolute;
            background: #504d53;
            height: 100%;
            width: 30px;
            cursor: pointer;
        }

        .cartbtn > div {
            color: #ccc;
            line-height: 16px;
            padding: 7px 8px 10px;
            font-size: 14px;
            font-weight: 700;
            position: absolute;
            top: 50%;
        }

        .cartbtn > div:before {
            content: "";
            display: block;
            width: 20px;
            height: 2px;
            background: #CCC;
            position: absolute;
            top: -3px;
            right: 5px;
        }

        .cartbtn > div:after {
            content: "";
            display: block;
            width: 20px;
            height: 2px;
            background: #CCC;
            position: absolute;
            bottom: 0px;
            right: 5px;
        }

        .cartarea {
            width: 300px;
            height: 100%;
            z-index: 1000;
            position: absolute;
            right: 0px;
        }

        .cartarea > label {
            position: absolute;
            font-weight: 400;
            font-size: 18px;
            right: 92px;
            top: 100px;
            z-index: -1;
        }

        .carttop {
            width: 300px;
            height: 40px;
            line-height: 40px;
            text-align: center;
            background: #fff;
            margin-bottom: 30px;
        }

        .cart {
            width: 300px;
            height: 50px;
            line-height: 50px;
            background: #fff;
            border-bottom: 1px solid #cecece;
            z-index: 10;
            padding: 0 10px;
        }

        .cart > div {
            float: left;
        }

        .cdname {
            font-size: 14px;
            width: 95px;
            color: #666;
            white-space: nowrap;
            text-overflow: ellipsis;
            overflow: hidden;
        }

        .cdnum {
            width: 60px;
            margin-left: 20px;
            margin-top: 15px;
            line-height: 20px;
        }

        .cdnum > input {
            width: 20px;
            height: 20px;
            border: 1px solid #ccc;
            border-left: none;
            border-right: none;
            box-sizing: border-box;
            text-align: center;
            float: left;
        }

        .cbtn {
            width: 20px;
            height: 20px;
            border: 1px solid #ccc;
            background: #EEE;
            box-sizing: border-box;
            display: block;
            text-align: center;
            float: left;
        }

        .cdprice {
            margin-left: 30px;
            color: #f17530;
            font-weight: 600;
        }

        .mdetals {
            width: 920px;
            height: 93px;
            margin: 20px 0;
            background: #FFF;
            border: 1px solid #ebebeb;
            padding: 15px 20px;
        }

        .mdetals > div {
            float: left;
        }

        .mpic > img {
            width: 120px;
            height: 90px;
        }

        .mnl {
            width: 160px;
            /* margin-left: 60px; */
            margin-top: 15.5px;
            text-align: center;
        }

        .mnl > div:nth-child(1) {
            font-weight: 600;
            font-size: 25px;
            color: #573500;
        }

        .mnl > div:nth-child(2) {
            padding-top: 13px;
            font-size: 15px;

        }

        .mnl, .mrank, .mtel {
            margin-left: 100px;
        }

        .mrank {
            color: #898989;
            margin-top: 18px;
            position: relative;
        }

        .mrank:before {
            content: '';
            display: block;
            position: absolute;
            right: -60px;
            width: 3px;
            height: 60px;
            background: #898989;
        }

        .mrank > div {
            font-size: 14px;
            text-align: center;
        }

        .mrank > div:nth-child(1) > span {
            font-size: 34px;
            font-weight: 600;
        }

        .mrank > div:nth-child(2) {
            margin-top: 5px;
            font-size: 14px;
        }

        .mtel {
            color: #898989;
            margin-top: 18px;
            position: relative;
            text-align: center;
        }

        .mtel > div:nth-child(1) {
            font-size: 34px;
        }

        .mtel > div:nth-child(2) {
            margin-top: 5px;
            font-size: 14px;
        }

        .summary {
            position: absolute;
            bottom: 0;
            height: 110px;
            width: 100%;
            background: #FFF;
        }

        .summary > div {
            margin-top: 16px;
            margin-right: 20px;
            float: right;
            font-size: 14px;
        }

        .summary > div > span {
            color: #f17530;
        }

        .summary > a {
            clear: both;
            display: block;
            margin: 0 auto;
            margin-top: 50px;
            width: 260px;
            height: 30px;
            line-height: 30px;
            background: #fa5858;
            text-align: center;
            color: #FFF;
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
            <li><a href="../customer/login">登录</a></li>
            <li><a href="../customer/register">注册</a></li>
        </ul>
        <div class="clear">
        </div>
    </nav>
</header>
<section>
    <div id="merchantInfo" class="mdetals">
        <!-- 这里存放商家信息 -->
    </div>
    <div class="clear"></div>
    <div id="dishesShowcase" class="dishes">
        <!-- 这里放商品信息 -->
        <div class="clear"></div>
    </div>
</section>
<div class="mcart" id="mcart">
    <div id="cartSlideBar" class="cartbtn" onclick="computeTheTotal()">
        <div>购物车</div>
    </div>
    <div class="cartarea">
        <label>购物车空空如也</label>
        <div class="thead"></div>
        <div class="carttop">
            购物车
        </div>
        <div id="cart">

        </div>
        <div class="summary">
            <div>总计:<span id="total">0</span>元</div>
            <a id="makeOrder" href="#">去结算</a>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="/SuperCell-ELM-C/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/SuperCell-ELM-C/js/viewDishes.js"></script>
<script>
</script>
</html>