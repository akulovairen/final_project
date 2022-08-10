<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.login"/>
<html>
<head>
    <title>Registration</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">

    <style>
        .divider-text {
            position: relative;
            text-align: center;
            margin-top: 15px;
            margin-bottom: 15px;
        }
        .divider-text span {
            padding: 7px;
            font-size: 12px;
            position: relative;
            z-index: 2;
        }
        .divider-text:after {
            content: "";
            position: absolute;
            width: 100%;
            border-bottom: 1px solid #ddd;
            top: 55%;
            left: 0;
            z-index: 1;
        }

        .btn-facebook {
            background-color: #405D9D;
            color: #fff;
        }
        .btn-twitter {
            background-color: #42AEEC;
            color: #fff;
        }
    </style>
</head>
<body>
<div class="bg-nav bg-dark">
    <div class="container">
        <nav class="navbar navbar-expand-sm  navbar-dark">
            <!-- Brand -->
            <a class="navbar-brand" href="#">HelpInWartime</a>
            <%--                <img src="bird.jpg" alt="Logo" style="width:40px;">--%>

            <!-- Toggler/collapsibe Button -->
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <!-- Navbar links -->
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="/coursesListGuest"><fmt:message key="login.allCourse"/> <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/login"><fmt:message key="login.login"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link " href="/registration"><fmt:message key="login.register"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="?lang=EN"><img src="/images?filename=british-flag-2.png"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="?lang=UA"><img src="/images?filename=flag-3d-UA 2.png"/></a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</div>
<div class="container">
    <br>  <p class="text-center"><fmt:message key="login.slavaUkraine"/></p>
    <br>  <p class="text-center"><fmt:message key="login.hello"/></p>
    <hr>
    <div class="card bg-light">
        <article class="card-body mx-auto" style="max-width: 400px;">
            <h4 class="card-title mt-3 text-center"><fmt:message key="login.creatAccount"/> </h4>
<%--            <p class="text-center">Get started with your free account</p>--%>

            <form  action="/registration" method="post">
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input name="name" class="form-control" placeholder="<fmt:message key="login.enterName"/>" type="text" value="<c:if test="${not empty restoredValues.name}">${restoredValues.name}</c:if>" required/>
                    <c:if test="${not empty messagesMap.name}"><div>${messagesMap.name}</div></c:if>
                </div> <!-- form-group// -->
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input name="surname" class="form-control" placeholder="<fmt:message key="login.enterSurname"/>" value="<c:if test="${not empty restoredValues.surname}">${restoredValues.surname}</c:if>" required/>
                    <c:if test="${not empty messagesMap.surname}"><div>${messagesMap.surname}</div></c:if>
                </div> <!-- form-group// -->
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
                    </div>
                    <input name="email" class="form-control" placeholder="<fmt:message key="login.enterEmail"/>" type="email" value="<c:if test="${not empty restoredValues.email}">${restoredValues.email}</c:if>" required/>
                    <c:if test="${not empty messagesMap.email}"><div>${messagesMap.email}</div></c:if>
                </div> <!-- form-group// -->
                <div class="form-group input-group">
                    <div class="input-group-prepend" >
                        <span class="input-group-text"> <i title="<fmt:message key="login.enterBirthday"/>" class="fa fa-user"></i> </span>
                    </div>
                    <input name="birthday" class="form-control" type="date"
                           value="<c:if test="${not empty restoredValues.birthday}">${restoredValues.birthday}</c:if>" required/>
                    <c:if test="${not empty messagesMap.birthday}"><div>${messagesMap.birthday}</div></c:if>
                </div> <!-- form-group// -->
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                    </div>
                    <input class="form-control" name="password" placeholder="<fmt:message key="login.enterPassword"/>" type="password">
                </div> <!-- form-group// -->
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                    </div>
                    <input class="form-control" name="password_repeat" placeholder="<fmt:message key="login.repeatPassword"/>" type="password" required/>
                    <c:if test="${not empty messagesMap.password_repeat}"><div>${messagesMap.password_repeat}</div></c:if>
                </div> <!-- form-group// -->
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block"> <fmt:message key="login.creatAccount"/>  </button>
                </div> <!-- form-group// -->
                <p class="text-center"><fmt:message key="login.haveAccount?"/> <a href="/login"><fmt:message key="login.login"/></a> </p>
            </form>
        </article>
    </div> <!-- card.// -->
</div>

</body>
</html>
