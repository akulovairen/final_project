<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <title>Course description guest</title>

    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

    <link rel="icon" href="https://maxcdn.icons8.com/Share/icon/color/Gaming/pokecoin1600.png" />
    <!---- Icon link local ----->

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
    <!-------    font awesome online plug --------------->

    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

    <style>
        @import 'https://fonts.googleapis.com/css?family=Raleway:300,400';


        body {
            font-family: 'Raleway:300', sans-serif
        }

        .admin-sidenav {
            width: auto;
            height: auto;
            margin-left: 0px;
        }
        .admin-sidenav a {
            text-decoration: none;
        }
        .admin-sidenav li {
            text-align: justify;
            padding: .5rem;
            padding-left: 1rem;
            -webkit-transition: all .2s linear;
            transition: all .2s linear;
            background-color: #000;
            border: 1px solid #333;
        }
        .admin-sidenav li a {
            color: #fff;
        }

        .admin-sidenav li a:active {
            border-color: #02d3f5;
        }

        .admin-sidenav li:hover {
            border-radius: 0 .5rem .5rem 0;
            border-color: #02d3f5;
            -webkit-transform: translate(30px, 0px);
            transform: translate(30px, 0px);
            background: -webkit-linear-gradient(left, #006a7b, #002340);
            background: linear-gradient(to right, #006a7b, #002340);
        }

        .admin-sidenav li:active {
            border-color: #02d3f5;
        }
    </style>
    <style>
        .how-section1{
            margin-top:-15%;
            padding: 10%;
        }
        .how-section1 h4{
            color: #ffa500;
            font-weight: bold;
            font-size: 30px;
        }
        .how-section1 .subheading{
            color: #3931af;
            font-size: 20px;
        }
        .how-section1 .row
        {
            margin-top: 10%;
        }
        .how-img
        {
            text-align: center;
        }
        .how-img img{
            width: 40%;
        }
    </style>
</head>
<body>

<div class="bg-nav bg-dark">
    <div class="container">
        <nav class="navbar navbar-expand-sm  navbar-dark">
            <!-- Brand -->
            <a class="navbar-brand" href="coursesListGuest">Help In Wartime</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="/coursesListGuest"><i class="fa fa-home"></i> <fmt:message key="login.allCourse"/> <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/login"><i class="fa fa-arrow-right"></i> <fmt:message key="login.login"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link " href="/registration"><i class="fa fa-user"></i> <fmt:message key="login.register"/></a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</div>
        <div class="row">
            <div id="admin-sidebar" class="col-md-2 p-x-0 p-y-3">
                <ul class="sidenav admin-sidenav list-unstyled">
                    <c:forEach var="topic" items="${allTopic}">
                        <li><a href="/courseByTopicGuest?topic_id=${topic.id}"><c:out value="${topic.name}"/></a></li>
                    </c:forEach>

                </ul>
            </div>
        </div>
<div class="how-section1">
<div class="row">
    <div class="col-md-6">
        <h4>${course.name}</h4>
        <h4 class="subheading"><em><fmt:message key="login.dataStart"/></em>: <c:out value="${course.dateStart}"/><br>
            <em><fmt:message key="login.durationInWeeks"/></em>: <c:out value="${course.duration}"/><br>
            <em><fmt:message key="login.teacher"/></em>: <c:out value="${course.teacher.surname} ${course.teacher.name}"/><br></h4>
        <p class="text-muted">
            ${course.description} <br></p>
    </div>
    <div class="col-md-6 how-img">
        <img src="/images?filename=Разом ми сила.jpg" />
    </div>
</div>
    <p><a class="btn btn-primary" href="/registration"><fmt:message key="login.register"/></a></p>
</div>
</div>
</body>
</html>
