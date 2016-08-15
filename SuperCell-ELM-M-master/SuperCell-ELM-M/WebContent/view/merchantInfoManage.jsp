<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/nav.css" type="text/css" />
<link rel="stylesheet" href="../css/merchantInfo.css" type="text/css" />
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/merchantInfoManage.js"></script>
<title>商家信息管理</title>
</head>
<body>
<header>
        <input type="hidden" id="merchantPhonenumer" value="${sessionScope.merchant}">
		<div class="mynav">
			<ul>
				<li><a href="/SuperCell-ELM-M/view/homePage.jsp">首页</a></li>
				<li><a href="/SuperCell-ELM-M/merchant/getMerchantInfoByPhoneNumber.do?phoneNumber=${sessionScope.merchant}">商家管理</a></li>
				<li><a href="/SuperCell-ELM-M/dishesController/showdishes.do">菜品管理</a></li>
				<li><a href="/SuperCell-ELM-M/view/order.jsp">订单管理</a></li>
				<li><a href="/SuperCell-ELM-M/complaint/getComplaintInfos/${sessionScope.merchant}">投诉管理</a></li>
			</ul>
		<div class="clear"></div>
		</div>
        <nav>
            <ul>
                <li><label>Welcome,<span>${sessionScope.merchant}</span></label></li>
                <li><a href="/SuperCell-ELM-M/merchant/logOff.do">注销</a></li>
                
            </ul>
            <div class="clear">
            </div>
        </nav>
    </header>

	<div class="cover" id="cover" hidden="hidden"></div>

    <section>
    	<div class="head">
    		商家信息
    		<a href="../merchant/getUpdateMerchantInfo.do?phoneNumber=${sessionScope.merchant}">信息修改</a>
    	</div>
    	<div class="uinfo">
    		<label>手机号：</label><label>${merchantInfo.phoneNumber}</label>
    	</div>
    	<div class="uinfo">
    		<label>密码：</label><label>*****</label><a href="#" onclick="changeInfo('cpwd')">修改密码</a>
    	</div>
    	<div class="uinfo">
    		<label>商店名称：</label><label>${merchantInfo.shopName}</label>
    	</div>
    	<div class="uinfo">
    		<label>商店地址：</label><label>${merchantInfo.address}</label>
    	</div>
    	<div class="uinfo">
    		<label>商店图片：</label><img src="../pic/pic.do/${merchantInfo.shopPicPath}" alt="">
    	</div>
    	<div class="uinfo">
    		<label>营业执照：</label><img src="../pic/pic.do/${merchantInfo.licensePicPath}" alt="">
    	</div>
    	<div class="uinfo">
    		<label>身份证证件：</label><img src="../pic/pic.do/${merchantInfo.idCardPicPath}" alt="">
    	</div>
    </section>

    <div class="cpwd" id="cpwd" hidden="hidden">
    		<div class="hhead">
    		 修改密码
    		 	<span class="hxbtn" onclick="disInfo(this)"></span>
    		</div>
    		<form action="" method="post">
	    		<div class="htable">
		    		<div class="htr">
		    			<label>原密码</label><input type="password" id="oldPassword" name="oldPassword"/>
		    		</div>
		    		<div class="htr">
		    			<label>新密码</label><input type="password" id="newPassword" name="newPassword"/>
		    			<p id="passwordTip" style="color:red;"></p>
		    		</div>
		    		<div class="htr">
		    			<label>确认密码</label><input type="password" id="confirmPassword"/>
		    			<p id="confirmPasswordTip" style="color:red;"></p>
		    		</div>	
	    		</div>
	    		<div class="herror" id="oldPasswordTip">
	    			
	    		</div>
	    		<div class="hbtn">
	    			<input type="button" value="提交" id="psdUpdateBtn"/>
	    		</div>
    		</form>
    	</div>
</body>
<script type="text/javascript">
	function changeInfo(ctype){
		var type = document.getElementById(ctype);
		var cover = document.getElementById("cover");
		type.removeAttribute("hidden");
		cover.removeAttribute("hidden");
	}
	function disInfo(obj){
		obj.parentNode.parentNode.setAttribute("hidden","hidden");
		var cover = document.getElementById("cover");
		cover.setAttribute("hidden","hidden");	
	}
</script>	
</html>