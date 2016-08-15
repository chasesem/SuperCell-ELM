$(document).ready(function(){
	var newPassword = $("#newPassword");
	var confirmPassword =$("#confirmPassword");
	confirmPassword.on("keyup",function(){
		comparePassword();
	});
	newPassword.on("keyup",function(){
		checkPassword();
		if(newPassword.val()!="" && confirmPassword.val()!=""){
			comparePassword();
		}
	});
	
	function comparePassword(){
		if(newPassword.val()!=confirmPassword.val()){
			$("#confirmPasswordTip").html("两次输入密码应保持一致...");
			return false;
		}else{
			$("#confirmPasswordTip").html("");
			return true;
		}
	}

	$("#oldPassword").on("keyup",function(){
		$.ajax({
			url:"../merchant/checkPassword.do",
            type:"POST",
            data:{
            	phoneNumber:$("#merchantPhonenumer").val(),
            	oldPassword:$("#oldPassword").val()
            },
            dataType:"json",
            success:function(data){
            	data = $.parseJSON(data);
                if (data) {
                	$("#oldPasswordTip").html("");
                }else{
                	$("#oldPasswordTip").html("原密码有误...");
                }
            }
		});
	});

	var merchantPhonenumer = $("#merchantPhonenumer");
	$("#psdUpdateBtn").on("click",function(){
		if ($("#oldPassword").val()=="" || $("#newPassword").val()=="" || $("#confirmPassword").val()=="") {
			alert("sorry,密码框不能为空....");
			return;
		}
		if(!checkPassword()){
			alert("sorry，密码格式错误");
			return;
		}
		
		if (comparePassword()) {
			$.ajax({
				url:"../merchant/updatePassword.do",
	            type:"POST",
	            data:{
	            	phoneNumber:$("#merchantPhonenumer").val(),
	            	password:$("#newPassword").val()
	            },
	            dataType:"json",
	            success:function(data){
	            	data = $.parseJSON(data);
	                if (data) {
	                	$("#cpwd").attr("hidden","hidden");
						$("#cover").attr("hidden","hidden");
	                	alert("密码修改成功...");
	                }else{
	                	alert("sorry,密码修改失败...");
	                }
	            }
			});
		}else{
			alert("两次输入密码应保持一致...");
		}
		
	});


	function checkPassword() {
		var password = $("#newPassword");
    	var reg = /^(\w){5,20}$/;
    	var validator = new Validator(reg,password.val());
    	var pwdResult = validator.check();
    	var passwordTip = $("#passwordTip");
    	if(pwdResult){
    		passwordTip.html("");
    		return true;
    	}else{
    		passwordTip.html("sorry，密码只能输入5-20个字母、数字、下划线");
    		return false;
    	}
	}

	function Validator(reg,str){
    	this.reg = reg;
    	this.str = str;
    }
    
    Validator.prototype.check=function(){
    	if (this.reg.test(this.str)) {
	        return true;
	    } else {
	        return false;
	    }
    }

    //update merchant info
    var currentPhoneNumber = $("#phoneNumber");
     function checkMobile() {
    	var reg = /^1\d{10}$/;
    	var validator = new Validator(reg,currentPhoneNumber.val());
    	var result = validator.check();
    	var phoneNumberTip = $("#phoneNumberTip");
    	if(result){
    		phoneNumberTip.html("");
    		return true;
    	}else{
    		phoneNumberTip.html("sorry,手机号格式应为11位，以1开头...");
    		return false;
    	}
	}
	function checkAddress(){
		var addressTip = $("#addressTip");
		if ($("#address").val()=="") {
			addressTip.html("地址栏不能为空....");
			return false;
		}else{
			addressTip.html("");
			return true;
		}
	}
	function checkShopName(){
		var shopNameTip = $("#shopNameTip");
		if ($("#shopName").val()=="") {
			shopNameTip.html("商店名不能为空....");
			return false;
		}else{
			shopNameTip.html("");
			return true;
		}
	}
	
	currentPhoneNumber.on("keyup",function(){
		if (currentPhoneNumber.val()!=$("#merchantPhonenumer").val()) {
	        $.ajax({
	            url:"../merchant/isExistPhoneNumber.do/"+currentPhoneNumber.val(),
	            type:"GET",
	            dataType:"text",
	            success:function(data){
	                $("#phoneNumberTip").html(data);
	            }
	        });
	    }else{
	    	$("#phoneNumberTip").html("");
	    }
	});

	$("#infoUpdateBtn").on("click",function(){
		if(checkMobile()&&checkAddress()&&checkShopName()){
    		return true;
    	}
    	return false;
	});
});