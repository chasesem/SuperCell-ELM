/**
 * @author LIANGSW2
 */
$(document).ready(function(){
	var merchantPhonenumer = $("#merchantPhonenumer");
	getMerchantInfo();
	function getMerchantInfo(){
	 $.ajax({
            url:"../merchant/getHomePageMerchant.do/"+merchantPhonenumer.val(),
            type:"GET",
            dataType:"json",
            success:function(data){
                renderMerchantInfo(data);
            }
        });
	}

	function renderMerchantInfo(merchantInfo){
		$("#merchantShopPic").attr("src","../pic/pic.do/"+merchantInfo.shopPicPath);
		$("#merchantShopName").html(merchantInfo.shopName);
		$("#merchantAddress").html("地址："+merchantInfo.address);
		var rating = merchantInfo.rating+"";
		console.log(rating.length);
		if (rating.length>5) {
			rating=rating.substring(0,4);
		}
		$("#merchantRating").html(rating);
	}
	
	function refreshHomePageNewOrder(){
		getNotAckOrder();
	}
	setInterval(refreshHomePageNewOrder, 5000);
});