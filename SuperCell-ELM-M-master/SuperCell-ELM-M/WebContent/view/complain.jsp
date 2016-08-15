<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../../css/nav.css" type="text/css" />
<link rel="stylesheet" href="../../css/complaint.css" type="text/css" />
<script src="../../js/jquery.min.js"></script>
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
    <section>
    
   	 	<div class="head">
    		投诉信息
    	</div>
		<div class="mtable">

			<div class="mtheader">
				<div>用户</div><div>投诉内容</div><div>下单时间</div><div>处理结果</div><div>查看</div>
			</div>
			<c:if test="${complaintInfos==null || fn:length(complaintInfos)==0}">
				<p>暂无投诉信息.....</p>
			</c:if>
			<c:forEach var="currentComplaintInfo" items="${complaintInfos}">
				<c:set var="complaint" value="${currentComplaintInfo.complaint}"></c:set>
				<div class="mtr">
					<div>
						${complaint.phoneNumber}
						
					</div>
					<div style="line-hight:70px;">
						${complaint.complainMessage}
					</div>
					<div>
						${currentComplaintInfo.orderOfDate}
					</div>
					<div>
					<c:choose>
						<c:when test="${currentComplaintInfo.state==1}">
							<label class="untreated">未处理</label>
						</c:when>
						<c:when test="${currentComplaintInfo.state==2}">
							<label class="reject">驳回投诉</label>
						</c:when>
						<c:when test="${currentComplaintInfo.state==3}">
							<label class="deduct">扣除押金</label>
						</c:when>
						<c:otherwise>
							<label class="untreated">已拉黑</label>
						</c:otherwise>
					</c:choose>
						<!-- <label class="untreated">未处理</label> -->
						<!-- <label class="reject">驳回</label> -->
						<!-- <label class="deduct">扣除押金</label> -->
					</div>
					<div>
						<button class="obtn" onclick="showComplaint(this)">订单详情</button>
					</div>
				</div>
				<div class="ctab" hidden="hidden">
					<table cellpadding="5px">
						<thead>
							<tr><th>菜品名</th><th>单价</th><th>数量</th></tr>
						</thead>
						<tbody>
							<c:forEach var="currentItem" items="${currentComplaintInfo.itemDetails}">
								<tr>
									<td>${currentItem.dishesName}</td>
									<td>${currentItem.price/100}</td>
									<!-- <td>${currentItem.price}</td> -->
									<td>${currentItem.count}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:forEach>
		</div>
    </section>
</body>
<script>
	function showComplaint(obj){
		$(obj).parent().parent().next().stop(true, false).slideToggle(500);
	}
</script>
</html>