<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <title>Login</title>
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
            width: 500px;
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

        .login {
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

        .login:hover {
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
    </style>
</head>
<body>
<div class="main">
    <%--<label class="logo">ALOGO</label>--%>
    <img src="../images/bear.png" alt="logo">
    <input type="text" class="input" autocomplete="on" placeholder="请输入手机号" id="username"/>
    <span id="usernameTip"></span>
    <input type="password" class="input" placeholder="请输入密码" id="password"/>
    <span id="passwordTip"></span>
    <input type="button" class="login" value="登  录" id="login">
    <a href="../customer/register" class="goreg">立即注册</a>
</div>

</body>
<script src="../js/jquery-1.11.3.min.js"></script>
<script src="../js/customerLogin.js"></script>
</html>