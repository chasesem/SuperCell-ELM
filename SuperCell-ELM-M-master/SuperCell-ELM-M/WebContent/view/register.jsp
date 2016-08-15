<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
<title>Register</title>
    <style>
        body{
            background: #68ceff;
        }
        .main{
            width: 50%;
            margin:0 auto;
            margin-top: 5%;
            background: #4db8eb;
            border: 5px #EEE solid;
            padding:60px 10px;
            border-radius: 10px;
            text-align: center;
        }
        .input{
            display: block;
            width: 400px;
            margin: 0 auto;
            /*margin-bottom: 30px;*/
            height: 30px;
            font-size: 18px;
            padding-left: 40px;
            border: none;
            border-radius: 7px;
            transition: .4s;
        }

        .input:focus{
            transition: .4s;
            box-shadow: 1px 1px 15px #008dd1;
        }
        .main>p{
            width:400px;
            margin:0 auto;
            height: 40px;
            font-size: 18px;
            text-align: left;
            line-height: 40px;
            color:#E00;
        }
        .main>p>span{
            color:#E00;
        }
        .regis{
            display: block;
            width: 300px;
            margin: 0 auto;
            height: 40px;
            line-height: 40px;
            font-size: 20px;
            color:#454d67;
            border: none;
            background-color: #ffffff;
            border: 2px dashed #ff7474;
            margin-bottom: 20px;
            transition: .5s;
        }
        .regis:hover{
            border: none;
            background: #008dd1;
            color:#FFF;
            box-shadow: 1px 1px 15px #008dd1;
            transition: .3s;
        }
        .goreg{
            text-decoration: none;
            color: #ffffff;
            font-weight: 600;
            transition: .3s
        }
        .goreg:hover{
            color:#ff5e96;
            transition: .4s
        }
        .logo{
            font-size: 80px;
            color:#ffffff;
        }
.ffile{
    position: relative;
    display: block;
    text-decoration: none;
    height: 30px;
    background: #fff;
    width: 300px;
    height: 30px;
    margin:10px auto;
    font-size: 18px;
    line-height: 30px;
    border-radius: 7px;
    color: #696969;
    transition: .4s;
}
.tfile{
    position: absolute;
    width:300px;
    height: 30px;
    left: 0;
    top: 0;
    opacity: 0;
    filter: alpha(opacity=0);
    cursor: pointer
}
    </style>
</head>
<body>
<form action="../merchant/register.do" method="post" enctype="multipart/form-data">
   <div class="main">
    <!-- <img src="#" alt="logo"> -->
    <!-- <label class="logo" for="">ALOGO</label> -->
    <img src="/SuperCell-ELM-M/images/bear.png" alt="logo">
    <input type="text" class="input" autocomplete="on" placeholder="请输入手机号" name="phoneNumber" required="required" id="phoneNumber"/>
    <p><span id="phoneNumberTip"></span></p>
    <input type="password" class="input" placeholder="请输入密码" name="password" required="required" id="password"/>
    <p><span id="passwordTip"></span></p>
    <input type="password" class="input" placeholder="请再次输入密码" name="comfirmPassword" required="required" id="comfirmPassword"/>
    <p><span id="comfirmPasswordTip"></span></p>
    <input type="text" class="input" placeholder="请输入商店名称" name="shopName" required="required"/>
    <p><span></span></p>
    <input type="text" class="input" placeholder="请输入商店地点" name="address" required="required"/>
    <p><span></span></p>
    
    <a href="javascript:void(0);" class="ffile">
        点击上传商店图标
        <input type="file" class="tfile" name="files" id="mpic" required="required"/>
    </a>
    <img id="mpicv" style="margin-bottom: 10px;">
    
    <a href="javascript:void(0);" class="ffile">
        点击上传营业执照
        <input type="file" class="tfile" name="files" id="lpic" required="required"/>
    </a>
    <img id="lpicv" style="margin-bottom: 10px;">

    <a href="javascript:void(0);" class="ffile">
        点击上传身份证
        <input type="file" class="tfile" name="files" id="ipic" required="required"/>
    </a>
    <img id="ipicv" style="margin-bottom: 10px;">

    <input type="submit" class="regis" value="注  册" id="submitBtn">   
    <a href="login.jsp" class="goreg">已有账号，立即登录</a>
    <p><span>${register_fail_tip}</span></p>
   </div>
 </form>
</body>
    <script type="text/javascript" src="../js/Register.js"></script>
</html>