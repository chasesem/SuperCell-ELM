<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/nav.css" type="text/css" />
<link rel="stylesheet" href="../css/infoUpdate.css" type="text/css" />
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/merchantInfoManage.js"></script>
<title>商家信息修改页面</title>
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
        <form action="../merchant/updateMerchant.do" method="post" enctype="multipart/form-data">
        	<div class="head">
        		商家信息
        		<a href="../merchant/getMerchantInfoByPhoneNumber.do?phoneNumber=${sessionScope.merchant}">返回上一级</a>
        	</div>
        	<div class="uinfo">
        		<label>手机号：</label><input type="text" name="phoneNumber" value="${merchantInfo.phoneNumber}" id="phoneNumber">
        		<span id="phoneNumberTip" style="color:red;"></span>
        	</div>
        	<div class="uinfo">
        		<label>商店名称：</label><input type="text" name="shopName" value="${merchantInfo.shopName}" id="shopName">
        		<span id="shopNameTip" style="color:red;"></span>
        	</div>
        	<div class="uinfo">
        		<label>商店地址：</label><input type="text" name="address" value="${merchantInfo.address}" id="address">
        		<span id="addressTip" style="color:red;"></span>
        	</div>
        	<div class="uinfo">
        		<label>商店图片：</label>
        		<div class="uplogo">
        			<img src="../pic/pic.do/${merchantInfo.shopPicPath}" alt="" id="picu">
        			<input type="file" name="files" id="logo"/>
        		</div>
        	</div>
        	<div class="uinfo">
        		<label>营业执照：</label><img src="../pic/pic.do/${merchantInfo.licensePicPath}" alt="">
        	</div>
        	<div class="uinfo">
        		<label>身份证证件：</label><img src="../pic/pic.do/${merchantInfo.idCardPicPath}" alt="">
        	</div>
        	<div class="uinfo">
        		<label style="color:red;">${tips}</label>
        	</div>
        	<div class="subtn">
        		<input type="submit" id="infoUpdateBtn" value="更新信息">
        	</div>
        </form>
    </section>
</body>

<script>
	var logo = document.getElementById("logo");
    logo.onchange = function(){
    	var picu = document.getElementById("picu");
    	
        var objUrl = getObjectURL(this.files[0]) ;
        picu.src = objUrl;
    };
    //建立一個可存取到該file的url
    function getObjectURL(file) {
        var url = null ; 
        if (window.createObjectURL!=undefined) { // basic
            url = window.createObjectURL(file) ;
        } else if (window.URL!=undefined) { // mozilla(firefox)
            url = window.URL.createObjectURL(file) ;
        } else if (window.webkitURL!=undefined) { // webkit or chrome
            url = window.webkitURL.createObjectURL(file) ;
        }
        return url ;
    }
</script>
</html>