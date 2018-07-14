<%--
  Created by IntelliJ IDEA.
  User: YJH
  Date: 2018. 7. 6.
  Time: PM 9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>login</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/login.css" />
</head>
<body>
<div class="wrap">
    Login
    <form>
        <input type="text" id="id" placeholder="Id">
        <input type="password" id="password" placeholder="Password">
    </form>
    <button class="new-account" id="new-account-button">New Account</button>
    <button class="login" id="login-button">LOG IN</button>
</div>
<script src="/resources/js/node_modules/jquery/dist/jquery.js"></script>
<script>
    var login = (function() {
        var login = $("#login-button");
        var id = $("#id");
        var password = $("#password");

        var hadName = false;
        var hadPassword = false;


        function validLoginInput() {
            validId();
            validPassword();
            tryLogin();
        }

        function validId() {
            id.on("input", function() {
                if ( $(this).val().length > 0) {
                    hadName = true;
                } else {
                    hadName = false;
                }
                if (hadName && hadPassword) {
                    login.css("background", "#14a03d");
                    login.addClass("buttonafter");
                } else {
                    login.css("background", "#a0a0a0");
                    login.removeClass("buttonafter");
                }
            });
        }

        function validPassword() {
            password.on("input", function() {
                if ( $(this).val().length > 0){
                    hadPassword = true;
                } else {
                    hadPassword = false;
                }
                if (hadName && hadPassword) {
                    login.css("background", "#14a03d");
                    login.addClass("buttonafter");
                } else {
                    login.css("background", "#a0a0a0");
                    login.removeClass("buttonafter");
                }
            });
        }

        function tryLogin() {
            $("#login-button").on("click", function() {
                console.log("id : " + id.val() + " pw : " + password.val());
                var data = {
                    id : id.val(),
                    password : password.val()
                };

                $.ajax({
                    type : "POST",
                    url : "/java/login",
                    data : JSON.stringify(data),
                    contentType : "application/json"
                }).done(function() {
                    alert("login success!");
                    window.location.replace("/java/posts")
                }).fail(function(data) {
                    console.log(data);
                    alert("login failed!");
                })

            });
        }

        return {
            validLoginInput : validLoginInput
        }
    })();

    login.validLoginInput();

</script>
</body>
</html>
