$(document).ready(function () {
    $("#login").click(function () {
        var username = $("#username").val();
        var password = $("#password").val();
        $.post(username + "/" + password, function (response) {
            var user = JSON.parse(response)[0];
            if (user) {
                // 在sessionStorage设置用户信息
                sessionStorage.setItem("user", JSON.stringify(user));
                // 根据sessionStorage的信息决定跳转的页面
                if (sessionStorage.getItem("merchantId")) {
                    location.href = "/SuperCell-ELM-C/dishes/viewDishes";
                } else {
                    location.href = "/SuperCell-ELM-C";
                }
            } else {
                $("#passwordTip").html("用户名或密码错误");
            }
        })
    })
});
