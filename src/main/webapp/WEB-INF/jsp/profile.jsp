<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization"/>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Profile</title>
<%--    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/MaterialDesign-Webfont/3.6.95/css/materialdesignicons.css">--%>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

    <link rel="icon" href="https://maxcdn.icons8.com/Share/icon/color/Gaming/pokecoin1600.png" />
    <!---- Icon link local ----->

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
    <!-------    font awesome online plug --------------->

<%--    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">--%>

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">

    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/all.js"></script>
    <link href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" rel="stylesheet">

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css"
          integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">

    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/all.js"></script>
    <link href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" rel="stylesheet">

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css"
          integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous">
    <style>
        body {
            background-color: #f9f9fa
        }

        .padding {
            padding: 3rem !important
        }

        .user-card-full {
            overflow: hidden;
        }

        .card {
            border-radius: 5px;
            -webkit-box-shadow: 0 1px 20px 0 rgba(69,90,100,0.08);
            box-shadow: 0 1px 20px 0 rgba(69,90,100,0.08);
            border: none;
            margin-bottom: 30px;
        }

        .m-r-0 {
            margin-right: 0px;
        }

        .m-l-0 {
            margin-left: 0px;
        }

        .user-card-full .user-profile {
            border-radius: 5px 0 0 5px;
        }

        .bg-c-lite-green {
            background: -webkit-gradient(linear, left top, right top, from(#f2ce63), to(#565eec));
            background: linear-gradient(to right, #5a6eee, #f2da63);
        }

        .user-profile {
            padding: 20px 0;
        }

        .card-block {
            padding: 1.25rem;
        }

        .m-b-25 {
            margin-bottom: 25px;
        }

        .img-radius {
            border-radius: 5px;
        }



        h6 {
            font-size: 14px;
        }

        .card .card-block p {
            line-height: 25px;
        }

        @media only screen and (min-width: 1400px){
            p {
                font-size: 14px;
            }
        }

        .card-block {
            padding: 1.25rem;
        }

        .b-b-default {
            border-bottom: 1px solid #e0e0e0;
        }

        .m-b-20 {
            margin-bottom: 20px;
        }

        .p-b-5 {
            padding-bottom: 5px !important;
        }

        .card .card-block p {
            line-height: 25px;
        }

        .m-b-10 {
            margin-bottom: 10px;
        }

        .text-muted {
            color: #919aa3 !important;
        }

        .b-b-default {
            border-bottom: 1px solid #e0e0e0;
        }

        .f-w-600 {
            font-weight: 600;
        }

        .m-b-20 {
            margin-bottom: 20px;
        }

        .m-t-40 {
            margin-top: 20px;
        }

        .p-b-5 {
            padding-bottom: 5px !important;
        }

        .m-b-10 {
            margin-bottom: 10px;
        }

        .m-t-40 {
            margin-top: 20px;
        }

        .user-card-full .social-link li {
            display: inline-block;
        }

        .user-card-full .social-link li a {
            font-size: 20px;
            margin: 0 10px 0 0;
            -webkit-transition: all 0.3s ease-in-out;
            transition: all 0.3s ease-in-out;
        }
    </style>
</head>
<body>

<c:choose>
    <c:when test="${role eq 'admin'}">
<navbar:NavAdmin/>
    </c:when>
    <c:when test="${role eq 'teacher'}">
        <div class="bg-nav bg-dark">
            <div class="container">
                <nav class="navbar navbar-expand-sm  navbar-dark">
                    <!-- Brand -->
                    <a class="navbar-brand" href="/teacherPage">Help In Wartime</a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <!-- Navbar links -->
                    <div class="collapse navbar-collapse" id="navbarNav">
                        <ul class="navbar-nav ml-auto">
                            <li class="nav-item active">
                                <a class="nav-link" href="/teacherPage"><i class="fas fa-solid fa-home"></i> <fmt:message key="login.toHomePage"/> <span class="sr-only">(current)</span></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/profile"><i class="fas fa-solid fa-user"></i> <fmt:message key="login.profile"/></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" onclick="return confirmLogout();" href="/logout"><i class="fas fa-solid fa-arrow-right"></i> <fmt:message key="login.logout"/></a>
                                <script>
                                    function confirmLogout() {
                                        if (confirm("Ви впевнені,що хочете вийти?")) {
                                            location.href = '/logout';
                                        } else {
                                            return false;
                                        }
                                    }
                                </script>
                        </ul>
                    </div>
                </nav>
            </div>
        </div><br/>
    </c:when>
    <c:otherwise>
<navbar:NavStudent/>
    </c:otherwise>
</c:choose>

<div class="page-content page-container" id="page-content">
    <div class="padding">
        <div class="row container d-flex justify-content-center">
            <div class="col-xl-6 col-md-12">
                <div class="card user-card-full">
                    <div class="row m-l-0 m-r-0">
                        <div class="col-sm-4 bg-c-lite-green user-profile">
                            <div class="card-block text-center text-white">
                                <h6 class="f-w-600">${userFound.surname} ${userFound.name}</h6>
                                <p>${userFound.role}</p>
                                <i class=" mdi mdi-square-edit-outline feather icon-edit m-t-10 f-16"></i>
                            </div>
                        </div>
                        <div class="col-sm-8">
                            <div class="card-block">
                                <h6 class="m-b-20 p-b-5 b-b-default f-w-600"><fmt:message key="profile.info"/></h6>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <p class="m-b-10 f-w-600"><fmt:message key="login.birthday"/></p>
                                        <h6 class="text-muted f-w-400">${userFound.birthday}</h6>
                                    </div>
                                    <div class="col-sm-6">
                                        <p class="m-b-10 f-w-600"><fmt:message key="login.email"/></p>
                                        <h6 class="text-muted f-w-400">${userFound.email}</h6>
                                    </div>
                                </div>
                                <h6 class="m-b-20 m-t-40 p-b-5 b-b-default f-w-600"><fmt:message key="profile.lang.choose"/> </h6>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <p class="m-b-10 f-w-600"><fmt:message key="lang.en"/> </p>
                                        <h6 class="text-muted f-w-400"><a href="?lang=EN"><img src="/images?filename=british-flag-2.png"/></a></h6>
                                    </div>
                                    <div class="col-sm-6">
                                        <p class="m-b-10 f-w-600"><fmt:message key="lang.uk"/></p>
                                        <h6 class="text-muted f-w-400">
                                            <a href="?lang=UA"><img src="/images?filename=flag-3d-UA 2.png"/></a></h6>
                                    </div>
                                </div>
                                <ul class="social-link list-unstyled m-t-40 m-b-10">
                                    <li><fmt:message key="login.edit"/> <a href="/editProfile" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="twitter" data-abc="true"><i class="fa fa-edit" aria-hidden="true"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
