<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录失败</title>
<link rel="stylesheet" href="/SuperCell-ELM-M/css/nav.css" type="text/css" />
<link rel="stylesheet" href="/SuperCell-ELM-M/css/homepage.css" type="text/css" />
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

nav>ul>li {
	list-style: none;
	float: left;
	font-size: 20px;
	color: #FFF;
	padding-left: 20px;
}

nav>ul>li>a {
	text-decoration: none;
	color: #454d67;
	font-size: 20px;
	transition: .3s;
}

nav>ul>li>a:hover {
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

.head {
	background: #FFF;
	height: 50px;
	line-height: 50px;
	border-bottom: 1px solid #AAA;
	color: #fa9700;
	margin-top: 30px;
	font-weight: 600;
	padding-left: 20px;
}

.head>input {
	float: right;
	border: none;
	background: #00adff;
	color: #FFF;
	width: 90px;
	height: 30px;
	font-size: 14px;
	margin-top: 10px;
	margin-right: 20px;
}

.clear {
	clear: both;
}
.dmange {
	width: 500px;
	position: fixed;
	top: 30%;
	left: 37%;
	z-index: 1000;
}

.dmbody {
	background: #fff;
	width: 100%;
}

.dmbody>table {
	width: 480px;
	margin: 0 auto;
}

.dmbody>table>tr {
	padding: 10px 0;
}

.addimg {
	width: 100px;
	height: 75px;
}

.osbtn {
	background: #fff;
	text-align: center;
	padding: 10px 0;
}

.osbtn>input {
	background: #fe4d3d;
	border: none;
	color: #FFF;
	width: 100px;
	height: 30px;
	font-size: 16px;
	font-weight: 600;
}

.cover {
	position: fixed;
	width: 100%;
	height: 100%;
	background: rgba(0, 0, 0, 0.5);
	z-index: 500;
}


        .mybtn{
            width: 100px;
            margin: 0 auto;
            display: block;
            background: #64cdff;
            border: none;
            height: 40px;
            font-size: 15px;
            font-weight: 700;
            color: #FFF;
        }
        .myp{
            font-family:Microsoft YaHei;
            font-size:28px;
            color:#607D8B;
        }
    </style>
</head>
<body>
	<header>
        <nav>
            
            <div class="clear">
            </div>
        </nav>
        </nav>
    </header>
    <div style="text-align:center;">
        
        <img   src="/SuperCell-ELM-M/images/22222.png" style="width: 30%; height: 30%;"/>
        <p class="myp">${loginFailTips}</p>
        <a href="/SuperCell-ELM-M/view/login.jsp"><button class="mybtn" >返回登录</button></a>
    </div>
</body>
</html>