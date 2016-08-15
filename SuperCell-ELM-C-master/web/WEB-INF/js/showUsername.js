/**
 * Created by SHAZA on 8/14/2016.
 */
(function(){
    function loadUser() {
        var user = JSON.parse(sessionStorage.getItem("user"));
        if (user) {
            setUserInfo(user);
        } else {
            $.get("/SuperCell-ELM-C/customer", function (response) {
                user = JSON.parse(response);
                if (user) {
                    setUserInfo(user);
                }
            })
        }
    }

    function setUserInfo(user) {
        $("#username").html(user["phoneNumber"]);
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

    (function(){
        // 读取用户信息
        loadUser();
        // 设置按钮点击事件
        $("#logout").click(logout);

    })();

})();
