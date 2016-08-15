(function () {
    function changeInfo(ctype) {
        return function () {
            var type = document.getElementById(ctype);
            var cover = document.getElementById("cover");
            type.removeAttribute("hidden");
            cover.removeAttribute("hidden");
        }
    }

    function disInfo(obj) {
        return function () {
            obj.parentNode.parentNode.setAttribute("hidden", "hidden");
            var cover = document.getElementById("cover");
            cover.setAttribute("hidden", "hidden");
        }
    }

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
        $("#username").html(user["phoneNumber"]);
    }

    function logout() {
        $.get("../customer/logout", function (response) {
            if (JSON.parse(response)) {
                sessionStorage.removeItem("merchantId");
                sessionStorage.removeItem("user");
                $("#username").html("");
                location.href = "..";
            }
        })
    }

    function loadUserInfo() {
        var phoneNumberLabel = document.getElementById("phoneNumber");
        var addressLabel = document.getElementById("address");
        var originalAddress = document.getElementById("originalAddress");
        var xhr = new XMLHttpRequest();
        xhr.open("get", ".");
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var customer = JSON.parse(xhr.responseText);
                if (customer != null) {
                    phoneNumberLabel.innerHTML = customer["phoneNumber"];
                    addressLabel.innerHTML = customer["address"];
                    originalAddress.innerHTML = customer["address"];
                }
            }
        };
        xhr.send();
    }

    function updatePassword() {
        var originalPassword = document.getElementById("originalPassword").value;
        var newPassword = document.getElementById("newPassword").value;
        var confirmedPassword = document.getElementById("confirmedPassword").value;
        var incorrectPasswordPrompt = document.getElementById("incorrectPassword");
        var type = document.getElementById("cpwd");
        var cover = document.getElementById("cover");
        var xhr = new XMLHttpRequest();
        xhr.open("post", "updatePassword?originalPassword=" + originalPassword + "&newPassword=" + newPassword);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                if (JSON.parse(xhr.responseText)) {
                    type.removeAttribute("hidden");
                    incorrectPasswordPrompt.setAttribute("hidden", "hidden");
                    type.setAttribute("hidden", "hidden");
                    cover.setAttribute("hidden", "hidden");
                } else {
                    incorrectPasswordPrompt.removeAttribute("hidden");
                }
            }
        };
        xhr.send();
    }

    function checkIdentity() {
        var newPassword = document.getElementById("newPassword").value;
        var confirmedPassword = document.getElementById("confirmedPassword").value;
        var notIdenticalPasswordPrompt = document.getElementById("notIdenticalPassword");
        if (newPassword != confirmedPassword) {
            notIdenticalPasswordPrompt.removeAttribute("hidden");
            return false;
        } else {
            notIdenticalPasswordPrompt.setAttribute("hidden", "hidden");
            return true;
        }
    }

    function updateAddress() {
        var newAddress = document.getElementById("newAddress").value;
        var addressLabel = document.getElementById("address");
        var originalAddress = document.getElementById("originalAddress");
        var cads = document.getElementById("cads");
        var cover = document.getElementById("cover");
        var xhr = new XMLHttpRequest();
        xhr.open("post", "updateAddress?newAddress=" + newAddress);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                if (JSON.parse(xhr.responseText)) {
                    addressLabel.innerHTML = newAddress;
                    originalAddress.innerHTML = newAddress;
                    cads.setAttribute("hidden", "hidden");
                    cover.setAttribute("hidden", "hidden");
                }
            }
        };
        xhr.send();
    }

    (function () {
        document.getElementById("changePwd").onclick = changeInfo("cpwd");
        document.getElementById("submitPswUpdate").onclick = updatePassword;
        document.getElementById("confirmedPassword").onkeyup = checkIdentity;
        document.getElementById("newPassword").onkeyup = checkIdentity;
        document.getElementById("changeAddr").onclick = changeInfo("cads");
        document.getElementById("submitAddressUpdate").onclick = updateAddress;
        var changePwdPopup = document.getElementById("changePwdPopup");
        var changeAddrPopup = document.getElementById("changeAddrPopup");
        changePwdPopup.onclick = disInfo(changePwdPopup);
        changeAddrPopup.onclick = disInfo(changeAddrPopup);
        // 读取用户信息
        loadUserInfo();
        loadUser();
        // 设置按钮点击事件
        $("#logout").click(logout);
    })();
})();