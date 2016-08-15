var mydishes = [];
var mycarts = [];


function plusDish(obj) {
    var cdid = obj.parentNode.getElementsByTagName("input")[0].id.split("_")[1];
    var cdnum = parseInt(obj.parentNode.getElementsByTagName("input")[0].value);
    cdnum++;
    setDishes(cdid, cdnum);
}
function reduDish(obj) {
    var cdid = obj.parentNode.getElementsByTagName("input")[0].id.split("_")[1];
    var cdnum = parseInt(obj.parentNode.getElementsByTagName("input")[0].value);
    cdnum--;
    setDishes(cdid, cdnum);
}

function adddish(obj) {
    var newid = obj.parentNode.parentNode.id;
    if (isExist(newid)) {//已存在于购物车
        var cit = document.getElementById("cartItemCount_" + newid).value;
        var ncit = parseInt(cit) + 1;
        setDishes(newid, ncit);
    } else {//不在
        setDishes(newid, 1);
    }
}

function setDishes(did, dcount) {

    $.ajax({
        url: "../cart/getUpdate/" + did + "/" + dcount,
        type: "put",
        success: function (response) {
            if (isExist(response.dishesId)) {
                updateMycart(response);
                updateCart(response);
                computeTheTotal();
            } else {
                addNewCart(response);
                computeTheTotal();
            }
        }
    });

}


function updateMycart(cdish) {
    if (parseInt(cdish.count) == 0) {//移除该cart
        for (var i = 0; i < mycarts.length; i++) {
            if (mycarts[i].cdid == cdish.dishesId) {
                mycarts.splice(i, 1);
                break;
            }
        }
    } else {
        for (var i = 0; i < mycarts.length; i++) {
            if (mycarts[i].cdid == cdish.dishesId) {
                mycarts[i].cdnum = cdish.count;
                break;
            }
        }
    }
}

function updateCart(cdish) {
    if (parseInt(cdish.count) == 0) {//删除该cart
        var test = "cartItem_" + cdish.dishesId;
        var theCart = document.getElementById(test);
        theCart.parentNode.removeChild(theCart);
    } else {
        $("#cartItemCount_" + cdish.dishesId).val(cdish.count);
    }

}

function isExist(newid) {
    for (var i = 0; i < mycarts.length; i++) {
        if (newid == mycarts[i].cdid) {
            return true;
        }
    }
    return false;
}
function addNewCart(response) {
    var cdid = response.dishesId;
    var cdnum = response.count;
    var cartItem = {};
    cartItem.cdid = cdid;
    cartItem.cdnum = cdnum;
    mycarts.push(cartItem);

    for (var i = 0; i < mydishes.length; i++) {
        if (mydishes[i].did == cdid) {
            var cdname = mydishes[i].dname;
            var cdprice = mydishes[i].dprice;
            break;
        }
    }
    var cartDiv = $("#cart");
    var cartItemDiv = $("<div id='cartItem_" + cdid + "' class='cart'></div>");
    var cdnameDiv = $("<div class='cdname'>" + cdname + "</div>");
    var cdnumDiv = $(
        "<div class='cdnum'>" +
        "<a href='javascript:void(0);' onclick='reduDish(this)' class='cbtn'>-</a>" +
        "<input name='inputCount' id='cartItemCount_" + cdid + "' type='text' value='1'>" +
        "<a href='javascript:void(0);' onclick='plusDish(this)' class='cbtn'>+</a>" +
        "</div>"
    );
    var cdpriceDiv = "<div class='cdprice'>￥<span>" + cdprice + "</span>/份</div>";
    var clearDiv = "<div class='clear'></div>";
    cartItemDiv.append(cdnameDiv);
    cartItemDiv.append(cdnumDiv);
    cartItemDiv.append(cdpriceDiv);
    cartItemDiv.append(clearDiv);
    cartDiv.append(cartItemDiv);
}
function computeTheTotal() {
    var result = 0;
    for (var i = 0; i < mycarts.length; i++) {
        var tempid = mycarts[i].cdid;
        var tempnum = mycarts[i].cdnum;
        for (var j = 0; j < mydishes.length; j++) {
            var tempdid = mydishes[j].did;
            var temppri = mydishes[j].dprice;
            if (tempid == tempdid) {
                result += tempnum * temppri;
                break;
            }
        }
    }
    $("#total").html(result);
}

(function () {
    var show = 0;

    function showCart() {
        var maincart = document.getElementById("mcart");
        if (show == 0) {
            maincart.style.right = 0;
            show = 1;
        } else {
            maincart.style.right = "-300px";
            show = 0;
        }
    }

    function loadUser() {
        var user = JSON.parse(sessionStorage.getItem("user"));
        if (user) {
            setUserInfo(user);
        } else {
            $.get("../customer", function (response) {
                user = JSON.parse(response);
                if (user) {
                    setUserInfo(user);
                }
            })
        }
    }

    function setUserInfo(user) {
        $("#username").html(user["phoneNumber"]);
        $("#afterLoginNavbar").removeAttr("hidden");
        $("#loginNavbar").attr("hidden", "hidden");
    }

    function logout() {
        $.get("/SuperCell-ELM-C/customer/logout", function (response) {
            if (JSON.parse(response)) {
                sessionStorage.removeItem("merchantId");
                sessionStorage.removeItem("user");
                $("#username").html("");
                location.href = "/SuperCell-ELM-C";
            }
        })
    }

    function getMerchantDishes() {
        var merchantId = sessionStorage.getItem("merchantId");
        $.get("../dishes/" + merchantId, function (response) {
            var dishesList = JSON.parse(response)[0];
            if (dishesList) {
                var dishesShowcase = $("#dishesShowcase");
                for (var i = 0; i < dishesList.length; i++) {
                    var dishesDiv = $("<div class='dish' id='" + dishesList[i]["id"] + "'></div>");
                    var dishesImage = $("<img src='http://10.222.232.34:8080/SuperCell-ELM-M/pic/pic.do/" + dishesList[i]["dishesPicPath"] + "' alt=''/>");
                    var dishesName = $("<div class='dname'>" + dishesList[i]["dishesName"] + "</div>");
                    var price = $("<div class='dprice'>￥<span>" + dishesList[i]["price"] / 100 + "</span>/份</div>");
                    var plustButton = $("<div class='plus'><a href='javascript:void(0);' type='button' onclick='adddish(this)'><i class='pbtn'></i></a></div>");
                    var clearDiv = $("<div class='clear'></div>");
                    dishesDiv.append(dishesImage);
                    dishesDiv.append(dishesName);
                    dishesDiv.append(price);
                    dishesDiv.append(plustButton);
                    dishesDiv.append(clearDiv);
                    dishesShowcase.append(dishesDiv);

                    var mydish = {};
                    mydish.did = dishesList[i]["id"];
                    mydish.dname = dishesList[i]["dishesName"];
                    mydish.dprice = dishesList[i]["price"] / 100;
                    mydishes.push(mydish);
                }
            }
        })
    }

    function loadMerchantInfo() {
        var merchantId = sessionStorage.getItem("merchantId");
        if (merchantId) {
            $.get("../merchant/" + merchantId, function (response) {
                var merchant = response;
                if (merchant) {
                    var merchantInfo = $("#merchantInfo");
                    var shopImage = $("<div class='mpic'><img src='http://10.222.232.34:8080/SuperCell-ELM-M/pic/pic.do/" + merchant["shopPicPath"] + "' alt=''></div>");
                    var nameAndAddr = $("<div class='mnl'><div>" + merchant["shopName"] + "</div><div>" + merchant["address"] + "</div></div>");
                    var rating = $("<div class='mrank'><div><span>" + parseFloat(merchant["rating"]).toFixed(2) + "</span>分</div><div>商家评分</div></div>");
                    var phoneNumber = $("<div class='mtel'><div>" + merchant["phoneNumber"] + "</div><div>联系电话</div></div>");
                    merchantInfo.append(shopImage);
                    merchantInfo.append(nameAndAddr);
                    merchantInfo.append(rating);
                    merchantInfo.append(phoneNumber);
                }
            })
        }
    }


    function loadCartInfo() {
        $.get("../cart", function (outerResponse) {
            var cartItems = outerResponse;
            if (outerResponse) {
                var cart = $("#cart");
                for (var i = 0; i < cartItems.length; i++) {
                    sessionStorage.setItem("count", cartItems[i]["count"]);
                    $.ajax({
                        url: "../dishes/get/" + cartItems[i]["dishesId"],
                        async: false,
                        success: function (innerResponse) {
                            var dishes = innerResponse;
                            if (dishes) {
                                var cartDiv = $("<div id='cartItem_" + dishes["id"] + "' class='cart'></div>");
                                var itemName = $("<div class='cdname'>" + dishes["dishesName"] + "</div>");
                                var itemStatusBar = $("<div class='cdnum'></div>");
                                var minusAnchor = $("<a href='javascript:void(0);' onclick='reduDish(this)' class='cbtn'>-</a>");
                                var countInput = $("<input name='inputCount' id='cartItemCount_" + dishes["id"] + "' type='text' value='" + sessionStorage.getItem("count") + "' readonly/>");
                                var plusAnchor = $("<a href='javascript:void(0);' onclick='plusDish(this)' class='cbtn'>+</a>");
                                var price = $("<div class='cdprice'>￥<span>" + dishes["price"] / 100 + "</span>/份</div>");
                                var clearDiv = $("<div class='clear'></div>");

                                countInput.data("count", sessionStorage.getItem("count"));
                                cartDiv.append(itemName);
                                itemStatusBar.append(minusAnchor);
                                itemStatusBar.append(countInput);
                                itemStatusBar.append(plusAnchor);
                                cartDiv.append(itemStatusBar);
                                cartDiv.append(price);
                                cartDiv.append(clearDiv);
                                cart.append(cartDiv);

                                var mycart = {};
                                mycart.cdid = dishes["id"];
                                mycart.cdnum = parseInt(sessionStorage.getItem("count"));
                                mycarts.push(mycart);
                            }
                        }
                    });
                }
            }
        })
    }

    function makeOrder() {
        if (sessionStorage.getItem("user") != null) {
            location.href = "/SuperCell-ELM-C/customerOrder/previewOrder.do";
        } else {
            alert("请先登录");
        }
    }

    (function () {
        // 注册事件
        $("#cartSlideBar").click(showCart);
        $("#logout").click(logout);
        $("#makeOrder").click(makeOrder);
        // 加载商户信息
        loadMerchantInfo();
        // 加载用户信息
        loadUser();
        // 加载商户菜品信息
        getMerchantDishes();
        // 加载购物车信息
        loadCartInfo();
    })();

})();
