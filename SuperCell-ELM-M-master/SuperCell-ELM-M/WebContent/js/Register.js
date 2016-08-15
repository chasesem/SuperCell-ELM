$(document).ready(function(){
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
    var phoneNumber = document.getElementById("phoneNumber");
    phoneNumber.onkeyup = function(){
        $.ajax({
            url:"../merchant/isExistPhoneNumber.do/"+phoneNumber.value,
            // data:
            type:"GET",
            dataType:"text",
            success:function(data){
                phoneNumberTip.innerHTML = data;
            }
        });
	}
    
    var submitBtn= document.getElementById("submitBtn");
    submitBtn.onclick=function(){
    	if(checkMobile()&&checkPassword()&&comparePassword()){
    		return true;
    	}
    	return false;
    }
    
    function checkMobile() {
    	var reg = /^1\d{10}$/;
    	var validator = new Validator(reg,phoneNumber.value);
    	var result = validator.check();
    	var phoneNumberTip = document.getElementById("phoneNumberTip");
    	if(result){
    		phoneNumberTip.innerHTML="";
    		return true;
    	}else{
    		phoneNumberTip.innerHTML="sorry,手机号格式应为11位，以1开头...";
    		phoneNumber.focus();
    		return false;
    	}
	}
	
	function checkPassword() {
		var password = document.getElementById("password");
    	var reg = /^(\w){5,20}$/;
    	var validator = new Validator(reg,password.value);
    	var pwdResult = validator.check();
    	var passwordTip = document.getElementById("passwordTip");
    	if(pwdResult){
    		passwordTip.innerHTML="";
    		return true;
    	}else{
    		passwordTip.innerHTML="sorry，密码只能输入5-20个字母、数字、下划线";
    		password.focus();
    		return false;
    	}
	}
	
	
        var mpic = document.getElementById("mpic");
        mpic.onchange = function(){
            var objUrl = getObjectURL(this.files[0]) ;
            var picv = document.getElementById("mpicv");
            mpicv.setAttribute("src", objUrl);
            mpicv.setAttribute("width", "300px");
            mpicv.setAttribute("height", "150px");
        };

        var lpic = document.getElementById("lpic");
        lpic.onchange = function(){
            var objUrl = getObjectURL(this.files[0]) ;
            var picv = document.getElementById("lpicv");
            lpicv.setAttribute("src", objUrl);
            lpicv.setAttribute("width", "300px");
            lpicv.setAttribute("height", "150px");
        };

        var ipic = document.getElementById("ipic");
        ipic.onchange = function(){
            var objUrl = getObjectURL(this.files[0]) ;
            var picv = document.getElementById("ipicv");
            ipicv.setAttribute("src", objUrl);
            ipicv.setAttribute("width", "300px");
            ipicv.setAttribute("height", "150px");
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
        
        
        var password = $("#password");
    	var confirmPassword =$("#comfirmPassword");
    	confirmPassword.on("keyup",function(){
    		comparePassword();
    	});
    	password.on("keyup",function(){
    		if(password.val()!="" && confirmPassword.val()!=""){
    			comparePassword();
    		}
    	});
    	
    	function comparePassword(){
    		if(password.val()!=confirmPassword.val()){
    			$("#comfirmPasswordTip").html("两次输入密码应保持一致...");
    			confirmPassword.focus();
    			return false;
    		}else{
    			$("#comfirmPasswordTip").html("");
    			return true;
    		}
    	}
     });