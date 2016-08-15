<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/SuperCell-ELM-M/js/jquery.min.js"></script>
<title>Login</title>
    <style>
        body{
            background: #68ceff;
        }
        .main{
            width: 60%;
            margin:0 auto;
            margin-top: 15%;
            text-align: center;
        }
        .input{
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
        .input:focus{
            transition: .4s;
            box-shadow: 1px 1px 15px #008dd1;
        }
        .login{
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
        .login:hover{
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
        .fontRedColor{
        	color:red;
        }
    </style>
</head>
<body>
<div class="main">
   <!-- <label class="logo" for="">ALOGO</label> -->
 	<img src="/SuperCell-ELM-M/images/bear.png" alt="logo">
	<form action="/SuperCell-ELM-M/merchant/login.do" method="post">
	    <input type="text" class="input" autocomplete="on" placeholder="请输入手机号" name="phoneNumber" id="phoneNumber"/>
	    <input type="password" class="input" placeholder="请输入密码" name="password" id="password"/>
	    <span class="fontRedColor">${login_fail_tip}</span>
	    <input type="submit" class="login" value="登  录" id="loginBtn">   
    </form>
    <a href="/SuperCell-ELM-M/view/register.jsp" class="goreg">立即注册</a>
   </div>
</body>

<script type="text/javascript">
	$("#loginBtn").on("click",function(){
		var phoneNumber = $("#phoneNumber");
		var password = $("#password");
		if(phoneNumber.val()!="" && password.val()!=""){
			return true;
		}else{
			alert("sorry，账号或密码不能为空...");
			return false;
		}
	});
</script>
</html>