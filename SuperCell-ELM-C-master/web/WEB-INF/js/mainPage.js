(function () {
    function loadUser() {
        var user = JSON.parse(sessionStorage.getItem("user"));
        if (user) {
            setUserInfo(user);
        } else {
            $.get("customer", function (response) {
                user = JSON.parse(response);
                if (user) {
                    setUserInfo(user);
                }
            })
        }
    }

    function setUserInfo(user) {
        $("#afterLoginNavbar").removeAttr("hidden");
        $("#loginNavbar").attr("hidden", "hidden");
        $("#username").html(user["phoneNumber"]);
    }

    function logout() {
        $.get("customer/logout", function (response) {
            if (JSON.parse(response)) {
                sessionStorage.removeItem("user");
                sessionStorage.removeItem("merchantId");
                $("#afterLoginNavbar").attr("hidden", "hidden");
                $("#loginNavbar").removeAttr("hidden");
                $("#username").html("");
            }
        })
    }

    function loadRecommendedDishes() {
        $.get("dishes/recommended", function (response) {
            var recommendedDishes = response;
            if (recommendedDishes) {
                var advertisementBar = $("#advertisementBar");
                for (var i = 0; i < recommendedDishes.length; i++) {
                    var anchor = $("<a href='#'></a>");
                    var recommendedDishDiv = $("<div class='ad'></div>");
                    var dishImg = $("<img src='http://10.222.232.34:8080/SuperCell-ELM-M/pic/pic.do/" + recommendedDishes[i]["dishesImgPath"] + "' alt='' />");
                    var dishInfoDiv = $("<div class='adinfo'></div>");
                    var dishName = $("<div>" + recommendedDishes[i]["dishName"] + "</div>");
                    var merchantName = $("<div>" + recommendedDishes[i]["merchantName"] + "</div>");
                    anchor.click(cacheMerchantIdAndClearCart(recommendedDishes[i]["merchantId"]));
                    dishInfoDiv.append(dishName);
                    dishInfoDiv.append(merchantName);
                    recommendedDishDiv.append(dishImg);
                    recommendedDishDiv.append(dishInfoDiv);
                    anchor.append(recommendedDishDiv);
                    advertisementBar.append(anchor);
                }
            }
        })
    }

    function loadMerchant() {
        $.get("merchant", function (response) {
            var merchantList = response;
            if (merchantList) {
                var merchantsDiv = $("#merchantsDiv");
                for (var i = 0; i < merchantList.length; i++) {
                    var merchantDiv = $("<div class='merchant'></div>");
                    var merchantAnchor = $("<a href='#'></a>");
                    var merchantImg = $("<img src='http://10.222.232.34:8080/SuperCell-ELM-M/pic/pic.do/" + merchantList[i]["shopPicPath"] + "' alt=''/>");
                    var merchantName = $("<div class='mname'>" + merchantList[i]["shopName"] + "</div>");
                    var merchantAddr = $("<div>" + merchantList[i]["address"] + "</div>");
                    var merchantRatingDiv = $("<div class='numrank'></div>");
                    var merchantRating = $("<div>总评分：<span>" + parseFloat(merchantList[i]["rating"]).toFixed(2) + "</span></div>");
                    var merchantOrderCount = $("<div>总订单量:<span>" + merchantList[i]["numberOfOrders"] + "</span>单</div>");
                    var merchantRatingClearDiv = $("<div class='clear'></div>");
                    merchantAnchor.click(cacheMerchantIdAndClearCart(merchantList[i]["id"]));
                    merchantRatingDiv.append(merchantRating);
                    merchantRatingDiv.append(merchantOrderCount);
                    merchantRatingDiv.append(merchantRatingClearDiv);
                    merchantAnchor.append(merchantImg);
                    merchantAnchor.append(merchantName);
                    merchantAnchor.append(merchantAddr);
                    merchantAnchor.append(merchantRatingDiv);
                    merchantDiv.append(merchantAnchor);
                    merchantDiv.append(merchantDiv);
                    merchantsDiv.append(merchantDiv);
                }
            }
        })
    }

    function cacheMerchantIdAndClearCart(merchantId) {
        return function () {
            // 判断是否清除购物车
            if (merchantId != sessionStorage.getItem("merchantId")) {
                $.get("/SuperCell-ELM-C/cart/clear");
            }
            // 设置需要查询的菜品的商家id
            sessionStorage.setItem("merchantId", merchantId);
            location.href = "dishes/viewDishes";
        }
    }

    (function () {
        $("#logout").click(logout);
        // 加载用户信息
        loadUser();
        // 加载推荐菜品信息
        loadRecommendedDishes();
        // 加载商家信息
        loadMerchant();
    })();
})();