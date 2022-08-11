<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="localization" />
<html>
<head>
    <title>Login</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">

    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/all.js"></script>
    <link href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" rel="stylesheet">

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css"
          integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous">

    <style>
        body {
            font-family: "Lato", sans-serif;
        }


        .main-head {
            height: 150px;
            background: #FFF;

        }

        .sidenav {
            height: 100%;
            background-color: #000;
            overflow-x: hidden;
            padding-top: 20px;
        }


        .main {
            padding: 0px 10px;
        }

        @media screen and (max-height: 450px) {
            .sidenav {
                padding-top: 15px;
            }
        }

        @media screen and (max-width: 450px) {
            .login-form {
                margin-top: 10%;
            }

            .register-form {
                margin-top: 10%;
            }
        }

        @media screen and (min-width: 768px) {
            .main {
                margin-left: 40%;
            }

            .sidenav {
                width: 40%;
                position: fixed;
                z-index: 1;
                top: 0;
                left: 0;
            }

            .login-form {
                margin-top: 80%;
            }

            .register-form {
                margin-top: 20%;
            }
        }


        .login-main-text {
            margin-top: 20%;
            padding: 60px;
            color: #fff;
        }

        .login-main-text h2 {
            font-weight: 300;
        }

        .btn-black {
            background-color: #000 !important;
            color: #fff;
        }
    </style>
</head>
<body>

<div class="sidenav">
    <div class="login-main-text">
        <h2>Help In Wartime<br> Stop War</h2>
        <p>Все буде Україна</p>
        <a href="/coursesListGuest">
            <button class="btn btn-outline-dark"><fmt:message key="login.more"/></button>
        </a><br/>
        <p><fmt:message key="profile.lang.choose"/></p>
        <a href="?lang=EN"><img src="/images?filename=british-flag-2.png"/></a>
        <a href="?lang=UA"><img src="/images?filename=flag-3d-UA 2.png"/></a><br/>
    </div>
</div>
<div class="main">
    <div class="col-md-6 col-sm-12">
        <div class="login-form">
            <form action="/login" name="email" method="post">
                <p>
                    <input type="text" class="form-control" name="email" placeholder="<fmt:message key="login.enterLogin"/>" value="<c:if test="${not empty restoredValues.email}">${restoredValues.email}</c:if>" required/>
                    <c:if test="${not empty messagesMap.email}"><div style="color: red">${messagesMap.email}</div></c:if>
                </p>
                <p>
                    <input type="password" class="form-control" name="password" placeholder="<fmt:message key="login.enterPassword"/>" required/>
                </p>
                <c:if test="${not empty messagesMap.authenticationError}"><div style="color: red">${messagesMap.authenticationError}</div></c:if>
                <input type="submit" class="btn btn-black" value="<fmt:message key="login.login"/>" />
            </form>

            <form action="/registration" method="get">
                <input type="submit" class="btn btn-secondary" value="<fmt:message key="login.register"/>">
            </form>

        </div>
    </div>
</div>

</body>
</html>
