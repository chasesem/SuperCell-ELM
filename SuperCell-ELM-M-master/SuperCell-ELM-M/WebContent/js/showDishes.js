
	$("#addbuttonid").on("click",function(){
		setPrice("#addinputid");
	});
	$("#updatebuttonid").on("click",function(){
		setPrice("#dpriceu");
	});
	function setPrice(priceId){
		var price = $(priceId).val();
		$(priceId).val(price*100);
	}
	function setNewPic(dpic,pic) {
		var dpic = document.getElementById(dpic);
		var objUrl = getObjectURL(dpic.files[0]);
	    var pic = document.getElementById(pic);
	    pic.innerHTML = "";
	    var img = document.createElement("img");
	    img.setAttribute("src", objUrl);
	    img.setAttribute("width", "100px");
	    img.setAttribute("height", "75px");
	    pic.appendChild(img);
	}
	//建立一個可存取到該file的url
	function getObjectURL(file) {
		var url = null;
		if (window.createObjectURL != undefined) { // basic
			url = window.createObjectURL(file);
		} else if (window.URL != undefined) { // mozilla(firefox)
			url = window.URL.createObjectURL(file);
		} else if (window.webkitURL != undefined) { // webkit or chrome
			url = window.webkitURL.createObjectURL(file);
		}
		return url;
	}

	function disInfo(obj) {
		obj.parentNode.parentNode.setAttribute("hidden", "hidden");
		var cover = document.getElementById("cover");
		cover.setAttribute("hidden", "hidden");
		var picv = document.getElementById("picv");
		picv.innerHTML = "";
		var dpica = document.getElementById("dpica");
		dpica.value="";
	}

	function showManage(stype) {
		var stype = document.getElementById(stype);
		
		var cover = document.getElementById("cover");
		stype.removeAttribute("hidden");
		cover.removeAttribute("hidden");
	}

	function setUpdate(obj) {
		var dpicu = document.getElementById("dpicu");
		dpicu.value = "";
		var parent = obj.parentNode.parentNode;
		var dname = parent.getElementsByClassName("dname")[0].innerHTML.replace(
				/(^\s*)|(\s*$)/g, '');
		var dprice = parent.getElementsByClassName("dprice")[0]
				.getElementsByTagName("span")[0].innerHTML;
		var dimg = parent.getElementsByTagName("img")[0].src;

		var picu = document.getElementById("picu");
		picu.innerHTML = "";
		var img = document.createElement("img");
		img.setAttribute("src", dimg);
		img.setAttribute("width", "100px");
		img.setAttribute("height", "75px");
		picu.appendChild(img);
		document.getElementById("dnameu").value = dname;
		document.getElementById("dpriceu").value = dprice;
		document.getElementById("dishsIdid").value = parent.id;
	}
	
	 function test(dishesId,dishesName,dishesPrice){
		   var name=document.getElementById("dishesNameid");
		   var price=document.getElementById("dishesPriceid");
		   var Id=document.getElementById("dishsIdid");
		   Id.value=dishesId;
		   name.value=dishesName;
		   price.value=dishesPrice;
		    }
			function AjaxRequester() {

			}
			AjaxRequester.prototype.sendToCache = function(url, param, type,id) {
				var xhr = new XMLHttpRequest();
				xhr.onreadystatechange = function() {
					if (this.readyState == 4 && this.status == 200) {
						var result = xhr.responseText;				
						if (result=="1") {
							console.log("delete成功");
							var chilNode = document.getElementById(id);
			                var pNode = chilNode.parentNode;
			                pNode.removeChild(chilNode);        
							
						} else {
							console.log("delete失败");
						}

					}
				}

				if (type == "GET") {
				   console.log("this the get method");
				   console.log(url);
					xhr.open("GET", url, true);
					xhr.send();
				} else if (type == "POST") {
					xhr.open("POST", url, true)
					xhr.setRequestHeader("Content-Type",
							"application/x-www-form-urlencoded");
					xhr.send(param);
				}
			}

			function deleteDishes(id) {
		        console.log("deletefunctionstart...."+id);
				var ajax1 = new AjaxRequester();
				var url = "/SuperCell-ELM-M/dishesController/deletedishes.do/"+id;
				var result=ajax1.sendToCache(url, "", "GET",id);
			}
			
			function ajRecommend(id){
				$.ajax({
		             type: "GET",
		             url: "/SuperCell-ELM-M/dishesController/recommenddishes.do/"+id,
		             data: "",
		             success: function(data){
//		            	 alert("推荐成功");
		                      },
		             error: function(data){
//		            	 alert("推荐失败");
		            	 var datas = JSON.stringify(data);
		            	 console.log("!!!!"+datas);
		             }
		         });
			}
		    function inputText(id,labelid,buttonsub){
		        var input=document.getElementById(id).value;
		        var settext=document.getElementById(labelid);
		        var buttonid=document.getElementById(buttonsub);
		        if((/\d{1,10}(\.\d{1,2})?$/.test(input))){
		        	if(input<0){
		        		console.log("!!!!"+buttonid);
				        settext.innerHTML="请输入大于0的数值"  
				        buttonid.setAttribute("disabled", "disabled")   
		        	}else{
				        settext.innerHTML=""  
				        buttonid.removeAttribute("disabled");  
		        	}
		         
		        }else{
		           settext.innerHTML="请正确输入价格"   
		           buttonid.setAttribute("disabled", "disabled")  
		        }
		      
		      }

		    
		    function delComfirm(obj){
		    	var dname = obj.parentNode.parentNode.getElementsByClassName("dname")[0].innerHTML.replace(/(^\s*)|(\s*$)/g,'');
		    	var did = obj.parentNode.parentNode.id;
		    	document.getElementsByClassName("comfirmbody")[0].getElementsByTagName("span")[0].innerHTML = dname;
		    	var comfirmbtn = document.getElementById("comfirm").getElementsByClassName("osbtn")[0].getElementsByTagName("input")[0];
		    	comfirmbtn.removeAttribute("onclick");
		    	comfirmbtn.setAttribute("onclick", "deleteDishes('"+did+"');hidecomfirm();");
		    	
		    	}
		    function hidecomfirm(){
		    	var cover = document.getElementById("cover");
		    	cover.setAttribute("hidden","hidden");
		    	var comfirm = document.getElementById("comfirm");
		    	comfirm.setAttribute("hidden","hidden");
		    }