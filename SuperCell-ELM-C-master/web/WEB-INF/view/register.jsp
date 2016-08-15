<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <style>
        body {
            background: #68ceff;
        }

        .main {
            width: 60%;
            margin: 0 auto;
            margin-top: 15%;
            text-align: center;
        }

        .input {
            display: block;
            width: 400px;
            margin: 0 auto;
            margin-bottom: 30px;
            height: 30px;
            font-size: 18px;
            padding-left: 40px;
            border: none;
            transition: .4s;
        }

        .input:focus {
            transition: .4s;
            box-shadow: 1px 1px 15px #008dd1;
        }

        .regis {
            display: block;
            width: 300px;
            margin: 0 auto;
            height: 40px;
            line-height: 40px;
            font-size: 20px;
            color: #454d67;
            border: none;
            background-color: #ffffff;
            border: 2px dashed #ff7474;
            margin-bottom: 20px;
            transition: .5s;
        }

        .regis:hover {
            border: none;
            background: #008dd1;
            color: #FFF;
            box-shadow: 1px 1px 15px #008dd1;
            transition: .3s;
        }

        .goreg {
            text-decoration: none;
            color: #ffffff;
            font-weight: 600;
            transition: .3s
        }

        .goreg:hover {
            color: #ff5e96;
            transition: .4s
        }

        .logo {
            font-size: 80px;
            color: #ffffff;
        }
        .main>span{
            color:#E00;
        }
    </style>
</head>
<body>
<div class="main">
    <img src="../images/bear.png" alt="logo">
    <%--<label class="logo">ALOGO</label>--%>
    <input type="text" class="input" autocomplete="on" placeholder="请输入手机号" name="username" id="username"/>
    <span id="usernameTip"></span>
    <input type="password" class="input" placeholder="请输入密码" name="password" id="password"/>
    <span id="passwordTip"></span>
    <input type="password" class="input" placeholder="请再次输入密码" name="password" id="confirmedPassword"/>
    <span id="confirmedPasswordTip"></span>
    <input type="text" class="input" placeholder="请输入收货地点" name="address" id="address"/>
    <span id="registerTip"></span>
    <input type="button" class="regis" value="注  册" id="register">
    <a href="../customer/login" class="goreg">已有账号，立即登录</a>
</div>
<script src="../js/jquery-1.11.3.min.js"></script>
<script src="../js/customerRegister.js"></script>
</body>
</html>