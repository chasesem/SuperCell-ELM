<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/SuperCell-ELM-M/css/nav.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="/SuperCell-ELM-M/css/showDishes.css">
<script src="/SuperCell-ELM-M/js/jquery.min.js"></script>

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
    	<div class="head">菜品管理 <input type="button" value="添加菜品" onclick="showManage('add')" /></div>
    	
    	<div class="dishes">
    	<c:if test="${dList==null || fn:length(dList)==0}">
				<p>暂无菜品信息.....</p>
			</c:if>
    	<c:forEach items="${dList}" var="msg">
	    		<div class="dish" id="${msg.id}">
	    		<c:if test="${reDishesMsg eq msg.id}">
    				<div class="rmd"><label>热门推荐</label></div>
    			</c:if>
					<img src="/SuperCell-ELM-M/dishesController/picshow.do/${msg.dishesPicPath}" alt="">
					<div class="dname">
						${msg.dishesName}
					</div>
					<div class="dprice">
						￥<span>${msg.price/100}</span>/份
					</div>
					<div class="options">
						<input type="button" value="荐" onclick="ajRecommend('${msg.id}')"/>
						<input type="button"  onclick="showManage('update');setUpdate(this);" value="改"/>
						<!-- <input type="button" value="删" onclick="deleteDishes('${msg.id}')"/> -->
						<input type="button" onclick="delComfirm(this);showManage('comfirm');" value="删"/>
					</div>
					<div class="clear"></div>
	    		</div>
    	</c:forEach>
    		<div class="clear"></div>
    	</div>
    </section>
	<div class="dmange" id="update" hidden="hidden">
    	<div class="head">更新菜品
    		<span class="hxbtn" onclick="disInfo(this)"></span>
    	</div>
    	<form action="/SuperCell-ELM-M/dishesController/updatedishes.do" method="post" enctype="multipart/form-data">
	    	<div class="dmbody">
	    	  <label id="updatelabelid"></label>
	    	   <input type="text" hidden="hidden" id="dishsIdid" name="dishesId"/>
	    		<table cellpadding="10px">
	    		<tr><td>菜品名：</td><td><input type="text" id="dnameu" name="dishesName" required/></td><td rowspan="3"><span id="picu"></span></td></tr>
					<tr><td>价格：</td><td><input type="text" id="dpriceu" name="dishesPrice" onblur="inputText('dpriceu','updatelabelid','updatebuttonid')" required/></td></tr>
					<tr><td>图片：</td><td><input type="file" id="dpicu" onchange="setNewPic('dpicu','picu')" name="ufiles"/></td></tr>
	    		</table>
	    	</div>
	    	<div class="osbtn">
	    		<input type="submit" id="updatebuttonid" name="" value="修改" >
	    	</div>
	    </form>
    </div>
    
    <div class="dmange" id="add" hidden="hidden">
    	<div class="head">添加菜品
    		<span class="hxbtn" onclick="disInfo(this)"></span>
    	</div>
    	<form action="/SuperCell-ELM-M/dishesController/adddishes.do" method="post" enctype="multipart/form-data">
	    	<div class="dmbody">
	    	<label id="addlabelid"></label>
	    		<table cellpadding="10px">
	    		<tr><td>菜品名：</td><td><input type="text" name="dishesName" required/></td><td rowspan="3"><span id="picv"></span></td></tr>
					<tr><td>价格：</td><td><input type="text" name="price" id="addinputid" onblur="inputText('addinputid','addlabelid','addbuttonid')" required/></td></tr>
					<tr><td>图片：</td><td><input type="file" id="dpica" onchange="setNewPic('dpica','picv')" name="files" required="required"/></td></tr>
	    		</table>
	    	</div>
	    	<div class="osbtn">
	    		<input type="submit" id="addbuttonid"name="" value="添加" />
	    	</div>
	    </form>
    </div>
    
	
	<div class="dmange" id="comfirm" hidden="hidden">
	     <div class="head">删除菜品
	     <span class="hxbtn" onclick="disInfo(this)"></span>
	     </div>
	     <div class="comfirmbody">
	     确认删除：<span>菜品菜品菜品</span>?
	</div> 
	     <div class="osbtn">
	     <input type="button" name="" value="确认">
	     <input type="button" onclick="hidecomfirm()" style="background:#64cdff;" name="" value="取消">
	 </div>
	</div>
    
</body>
<script src="/SuperCell-ELM-M/js/showDishes.js"></script>
</html>