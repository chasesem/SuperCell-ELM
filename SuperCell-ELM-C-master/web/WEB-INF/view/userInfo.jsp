<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Info</title>
</head>
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
        margin-top: 10%;
    }

    .head {
        background: #FFF;
        height: 50px;
        line-height: 50px;
        border-bottom: 1px solid #AAA;
        color: #fa9700;
        padding-left: 20px;
        margin-top: 30px;
        font-weight: 600;
    }

    .hhead {
        background: #FFF;
        height: 50px;
        line-height: 50px;
        border-bottom: 1px solid #AAA;
        color: #fa9700;
        padding-left: 20px;
        font-weight: 600;
    }

    .uinfo {
        background: #FFF;
        height: 50px;
        line-height: 50px;
        padding: 15px 0;
        padding-left: 300px;
        font-size: 15px;
        border-bottom: 1px dotted #c3c3c3;
    }

    .uinfo > label:nth-child(1) {
        font-size: 18px;
        font-weight: 600;
        color: #666;
    }

    .uinfo > a {
        padding-left: 40px;
        text-decoration: underline;
        transition: .5s;
    }

    .uinfo > a:hover {
        color: #ff0087;
        transition: .3s;
    }

    .htable {
        display: table;
        margin: 0 auto;
    }

    .htr {
        display: table-row;
        margin: 0 auto;

    }

    .htr > label {
        display: table-cell;
        width: 150px;
        padding: 20px 0;
        text-align: center;
    }

    .htr > input {
        width: 200px;
    }

    .htr > label {

    }

    .herror {
        text-align: center;
        color: #E00;
        font-size: 17px;
    }

    .hbtn {
        padding: 10px 0;
        text-align: center;
    }

    .hbtn > input {
        width: 120px;
        height: 30px;
        color: #FFF;
        background: #f74342;
        border: none;
        font-size: 16px;
    }

    .cpwd {
        width: 600px;
        margin: 0px auto;
        background: #FFF;
        position: fixed;
        top: 30%;
        right: 35%;
    }

    .cads {
        width: 600px;
        margin: 0px auto;
        background: #FFF;
        position: fixed;
        top: 30%;
        right: 35%;
    }

    .hxbtn {
        width: 20px;
        height: 20px;
        border-radius: 50%;
        border: 1px solid #EEE;
        background: #64cdff;
        float: right;
        margin-top: 15px;
        margin-right: 50px;
        position: relative;
    }

    .hxbtn:before {
        content: '';
        display: block;
        width: 3px;
        height: 15px;
        background: #000;
        position: absolute;
        left: 8px;
        top: 2px;
        transform: rotate(45deg);
    }

    .hxbtn:after {
        content: '';
        display: block;
        width: 3px;
        height: 15px;
        background: #000;
        position: absolute;
        left: 8px;
        top: 2px;
        transform: rotate(-45deg);
    }

    .cover {
        position: fixed;
        width: 100%;
        height: 100%;
        background: rgba(0, 0, 0, 0.5);
    }
</style>
<body>
<header>
    <nav>
        <ul>
            <li><a href="/SuperCell-ELM-C">首页</a></li>
            <li><a href="/SuperCell-ELM-C/customerOrder/getOrders.do/1">查看订单</a></li>
            <li><a href="/SuperCell-ELM-C/customerOrder/viewComplaints.do">我的投诉</a></li>
            <li><label>Welcome, <span id="username"></span></label></li>
            <li><a id="logout" href="#">注销</a></li>
        </ul>
        <div class="clear">
        </div>
    </nav>
</header>

<div class="cover" id="cover" hidden="hidden"></div>

<section>
    <div class="head">
        个人信息
    </div>
    <div class="uinfo">
        <label>手机号：</label><label id="phoneNumber"></label>
    </div>
    <div class="uinfo">
        <label>密码：</label><label>......</label><a id="changePwd" href="#">修改密码</a>
    </div>
    <div class="uinfo">
        <label>地址：</label><label id="address"></label><a id="changeAddr" href="#">修改地址</a>
    </div>


    <div class="cpwd" id="cpwd" hidden="hidden">
        <div class="hhead">
            修改密码
            <span id="changePwdPopup" class="hxbtn"></span>
        </div>
        <form>
            <div class="htable">
                <div class="htr">
                    <label>原密码</label><input id="originalPassword" type="password"/>
                </div>
                <div class="htr">
                    <label>新密码</label><input id="newPassword" type="password"/>
                </div>
                <div class="htr">
                    <label>确认密码</label><input id="confirmedPassword" type="password"/>
                </div>
            </div>
            <div id="incorrectPassword" class="herror" hidden="hidden">
                原密码有误
            </div>
            <div id="notIdenticalPassword" class="herror" hidden="hidden">
                两次输入的密码不一致
            </div>
            <div class="hbtn">
                <input id="submitPswUpdate" type="button" value="提交"/>
            </div>
        </form>
    </div>

    <div class="cads" id="cads" hidden="hidden">
        <div class="hhead">
            修改地址
            <span id="changeAddrPopup" class="hxbtn"></span>
        </div>
        <form action="" method="post">
            <div class="htable">
                <div class="htr">
                    <label>原地址</label><label id="originalAddress"></label>
                </div>
                <div class="htr">
                    <label>新地址</label><input id="newAddress" type="text"/>
                </div>
            </div>
            <div class="hbtn">
                <input id="submitAddressUpdate" type="button" value="提交"/>
            </div>
        </form>
    </div>

</section>
</body>
<script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="../js/userInfo.js"></script>
</html>