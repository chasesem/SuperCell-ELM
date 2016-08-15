//$(document).ready(function(){
	var merchantPhonenumer = $("#merchantPhonenumer");
	getNotAckOrder();

	$("#notAckBtn").on("click",function(){
		changeAColor(this);
		getNotAckOrder();
	});

	$("#ackBtn").on("click",function(){
		changeAColor(this);
		getAckOrder();
	});

	$("#refusedBtn").on("click",function(){
		changeAColor(this);
		getRefusedOrder();
	});
	function changeAColor(a){
		$("#headNav a").removeClass("active");
		$(a).addClass("active");
	}
	
	function getNotAckOrder(){
	 $.ajax({
            url:"../orderController/queryNewOrderByMerchant/"+merchantPhonenumer.val(),
            type:"GET",
            dataType:"json",
            success:function(data){
                var orderPanel = new OrderPanel(data);
                orderPanel.clear();
                orderPanel.showNotAckOrder();
            }
        });
	}

	function getAckOrder(){
	 $.ajax({
            url:"../orderController/queryRecevicedOrderByMerchant/"+merchantPhonenumer.val(),
            type:"GET",
            dataType:"json",
            success:function(data){
                var orderPanel = new OrderPanel(data);
                orderPanel.clear();
                orderPanel.showAckOrder();
            }
        });
	}

	function getRefusedOrder(){
	 $.ajax({
            url:"../orderController/queryRefusedOrderByMerchant/"+merchantPhonenumer.val(),
            type:"GET",
            dataType:"json",
            success:function(data){
                var orderPanel = new OrderPanel(data);
                orderPanel.clear();
                orderPanel.showRefusedOrder();
            }
        });
	}

	var tpl='<div class="mtd">--phoneNumber--</div>'
					+'<div class="mtd">--total--</div>'
					+'<div class="mtd">--dateOfOrder--</div>'
					+'<div class="mtd" id="1">'
						+'<a href="--itemDetails--" class="obtn" style="--itemDetailsDisplay--">订单详情</a>'
						+'<a id="--receiveId--" href="#" class="obtn" style="--receiveDisplay--">确认接单</a>'
						+'<a id="--refuseId--" href="#" class="obtn" style="--refuseDisplay--">拒绝接单</a>'
					+'</div>';
	function getTpl(summary){
		var myTpl = "";
		myTpl = tpl.replace(/--phoneNumber--/,summary.customerPhoneNumber);
		myTpl = myTpl.replace(/--total--/,summary.order.total/100);
		var orderDate = formatDate(summary.order.dateOfOrder);
		myTpl = myTpl.replace(/--dateOfOrder--/,orderDate);
		myTpl = myTpl.replace(/--itemDetails--/,"../orderController/findOrderDetailsById?id="+summary.order.id);
		return myTpl;
	}

	function OrderPanel(orderData){
		this.orderData = orderData;
	}

	OrderPanel.prototype.showNotAckOrder=function(){
		
		if (this.orderData.length==0) {
			setNoResultTip("暂无未确认订单...");
		}else{
			this.orderData.forEach(function(item,index,array){
				var summary = item.orderSummary;
				var divNode = $("<div class='mtr'></div>");
				var myTpl = getTpl(summary);
				var receiveId = "receive"+summary.order.id;
				var refuseId = "refuse"+summary.order.id;
				myTpl = myTpl.replace(/--receiveId--/,receiveId);
				myTpl = myTpl.replace(/--refuseId--/,refuseId);
				divNode.html(myTpl);
				$("#orderDiv").append(divNode);

				$("#"+receiveId).on("click",function(){
						receiveOrder(summary.order.id);
				});
				$("#"+refuseId).on("click",function(){
					refuseOrder(summary.order.id);
				});
			});
		}
	}
	OrderPanel.prototype.showAckOrder=function(){
		if (this.orderData.length==0) {
			setNoResultTip("暂无已确认订单...");
		}else{
			this.orderData.forEach(function(item,index,array){
				var summary = item.orderSummary;
				var divNode = $("<div class='mtr'></div>");
				var myTpl = getTpl(summary);
				myTpl = myTpl.replace(/--receiveDisplay--/,"display:none;");
				myTpl = myTpl.replace(/--refuseDisplay--/,"display:none;");
				divNode.html(myTpl);
				$("#orderDiv").append(divNode);
			});
		}
	}

	OrderPanel.prototype.showRefusedOrder=function(){
		if (this.orderData.length==0) {
			setNoResultTip("暂无已拒绝订单...");
		}else{
			this.orderData.forEach(function(item,index,array){
				var summary = item.orderSummary;
				var divNode = $("<div class='mtr'></div>");
				var myTpl = getTpl(summary);
				myTpl = myTpl.replace(/--receiveDisplay--/,"display:none;");
				myTpl = myTpl.replace(/--refuseDisplay--/,"display:none;");
				divNode.html(myTpl);
				$("#orderDiv").append(divNode);
			});
		}
	}

	OrderPanel.prototype.clear=function(){
		$("#orderDiv").html("<div class='mtheader'><div>用户</div><div>总价（元）</div><div>下单时间</div><div>操作</div></div>");
	}

	function setNoResultTip(str){
		var divNode = $("<div class='mtr'></div>");
		var p = $("<p></p>");
		p.html(str);
		divNode.append(p);
		$("#orderDiv").append(divNode);
	}

	function formatDate(seconds){
		var date = new Date(seconds).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ")
		return date;
	}

	function receiveOrder(orderId){
		var url = "../orderController/receiveOrder?id="+orderId;
		sendRequest(url);
	}

	function refuseOrder(orderId){
		var url = "../orderController/refuseOrder?id="+orderId;
		sendRequest(url);
	}

	function sendRequest(requestUrl){
		$.ajax({
            url:requestUrl,
            type:"GET",
            dataType:"json",
            success:function(data){
//                window.location.reload();
            	getNotAckOrder();
            }
        });
	}

	
//});