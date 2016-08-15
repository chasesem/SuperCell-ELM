/**
 * Created by ZHENGNE2 on 8/11/2016.
 */
$(document).ready(function () {
    function Validator(reg, str) {
        this.reg = reg;
        this.str = str;
    }

    Validator.prototype.check = function () {
        return !!this.reg.test(this.str);
    };

    $("#username").on("keyup", function () {
        checkPhoneNum();
    });

    $("#password").on("keyup", function () {
        checkPassword();
    });

    $("#confirmedPassword").on("keyup", function () {
        checkConfirmedPassword();
    });

    $("#register").click(function () {
        var username = $("#username").val();
        var password = $("#password").val();
        var address = $("#address").val();
        if (checkPhoneNum() && checkPassword() && checkConfirmedPassword()) {
            $.post(username + "/" + password + "/" + address, function (response) {
                var user = JSON.parse(response)[0];
                if (user) {
                    sessionStorage.setItem("user", JSON.stringify(user));
                    location.href = "..";
                } else {
                    $("#registerTip").html("注册失败，请检查手机号是否正确");
                }
            })
        }
    });

    function checkConfirmedPassword() {
        var password = $("#password").val();
        var confirmedPassword = $("#confirmedPassword").val();
        var confirmedPasswordTip = $("#confirmedPasswordTip");
        if (password != confirmedPassword) {
            confirmedPasswordTip.html("两次输入的密码不一致");
            return false;
        } else {
            confirmedPasswordTip.html("");
            return true;
        }
    }

    function checkPassword() {
        var password = document.getElementById("password");
        var passwordTip = document.getElementById("passwordTip");
        var reg = /^(\w){5,20}$/;
        var validator = new Validator(reg, password.value);
        var result = validator.check();
        if (result) {
            passwordTip.innerHTML = "";
            return true;
        } else {
            passwordTip.innerHTML = "密码必须为5—20位";
            password.focus();
            return false;
        }
    }

    function checkPhoneNum() {
        var username = document.getElementById("username");
        var usernameTip = document.getElementById("usernameTip");
        var reg = /^1\d{10}$/;
        var validator = new Validator(reg, username.value);
        var result = validator.check();
        if (result) {
            usernameTip.innerHTML = "";
            return true;
        } else {
            usernameTip.innerHTML = "用户名是手机号，应为11位，且以1开头";
            username.focus();
            return false;
        }
    }
});
